package com.example.android.sunshine;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.sunshine.utilities.FakeDataUtils;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import me.drozdzynski.library.steppers.SteppersView;

import static com.example.android.sunshine.RecipeAdapter.*;

public class RecipesActivity extends AppCompatActivity implements RecipeAdapterOnClickHandler {

    private static final String URL_BASE =
            "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?fillIngredients=false&ingredients=";
    private static final String URL_SUFFIX = "&ranking=2";

    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar mLoading;

    private String[] mFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_recipes);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecipeAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mLoading = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        mFoods = getIntent().getExtras().getStringArray("foods");

        showLoading();

        new SpoonacularSearchTask().execute(makeSearchQuery());
    }

    private URL makeSearchQuery() {

        String urlString = URL_BASE;

        if (mFoods.length >= 1) {
            urlString += mFoods[0];
        }

        for (int i = 1; i < mFoods.length; i++) {
            urlString += "%2C" +  mFoods[i];
        }
        urlString += URL_SUFFIX; // add option for 1=maximize used or 2=minimize missing

        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    @Override
    public void onClick(int id) {

        Intent instructionsIntent = new Intent(RecipesActivity.this, InstructionsActivity.class);
        instructionsIntent.putExtra("recipe_id", id);

        startActivity(instructionsIntent);
    }

    public class SpoonacularSearchTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {


//            if (getPreferences(MODE_PRIVATE).getString("test", "default").equals(true)) {
            // TODO: 9/2/2017 Get these test settings up
            if (true) {
                return FakeDataUtils.fakeRecipeSearchResults();
            } else {
                StringBuilder response = null;

                try {

                    URLConnection connection = urls[0].openConnection();
                    connection.setRequestProperty("X-Mashape-Key", "TyzoL5wDIGmshTDH7Jccy4e88NJEp15YcuYjsnFbzup4sC4INc");
                    connection.setRequestProperty("Accept", "application/json");

                    InputStream is = connection.getInputStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                    response = new StringBuilder(); // or StringBuffer if Java version 5+
                    String line;
                    while ((line = rd.readLine()) != null) {
                        response.append(line);
                        response.append('\r');
                    }
                    rd.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return response.toString();
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(String recipeResult) {

            JSONArray results = null;

            if (recipeResult != null && recipeResult != "") {

                try {
                    results = new JSONArray(recipeResult);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mAdapter.setData(results);
                showData();
            }
        }
    }

    private void showLoading() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mLoading.setVisibility(View.VISIBLE);
    }

    private void showData() {
        mLoading.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
