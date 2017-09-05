package com.example.android.sunshine;

import android.os.AsyncTask;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.sunshine.data.AppPreferences;
import com.example.android.sunshine.utilities.FakeDataUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import me.drozdzynski.library.steppers.OnCancelAction;
import me.drozdzynski.library.steppers.OnChangeStepAction;
import me.drozdzynski.library.steppers.OnFinishAction;
import me.drozdzynski.library.steppers.SteppersItem;
import me.drozdzynski.library.steppers.SteppersView;

public class InstructionsActivity extends AppCompatActivity {

    private static final String URL_BASE =
            "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/";
    private static final String URL_SUFFIX_INSTRUCTIONS =
            "/analyzedInstructions?stepBreakdown=true";
    private static final String URL_SUFFIX_INGREDIENTS =
            "/information?includeNutrition=false";

    private int id;

    private FloatingActionButton mShowIngredients;
    private BottomSheetBehavior mBottomSheetBehavior;
    private TextView mIngredients;
    private SteppersView mStepperView;
    private ProgressBar mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        View bottomSheet = findViewById(R.id.bs_ingredients);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        mShowIngredients = (FloatingActionButton) findViewById(R.id.fab_show_ingredients);
        mShowIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                else {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        });

        mIngredients = (TextView) findViewById(R.id.tv_ingredients);
        mStepperView = (SteppersView) findViewById(R.id.steppersView);
        mLoading = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        id = getIntent().getExtras().getInt("recipe_id");

        showLoading();

        new IngredientsSearchTask().execute(makeIngredientsSearchQuery());
        new InstructionsSearchTask().execute(makeInstructionSearchQuery());
    }

    private URL makeInstructionSearchQuery() {

        URL url = null;
        try {
            url = new URL(URL_BASE + id + URL_SUFFIX_INSTRUCTIONS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private URL makeIngredientsSearchQuery() {

        URL url = null;
        try {
            url = new URL(URL_BASE + id + URL_SUFFIX_INGREDIENTS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public class InstructionsSearchTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {


            StringBuilder response = null;
            try {

                URLConnection connection = urls[0].openConnection();
                connection.setRequestProperty("X-Mashape-Key", "TyzoL5wDIGmshTDH7Jccy4e88NJEp15YcuYjsnFbzup4sC4INc");
                connection.setRequestProperty("Accept", "application/json");

                // TODO: 9/2/2017 timeout
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

        @Override
        protected void onPostExecute(String instructionResults) {

            JSONArray instructionSteps = null;

            if (instructionResults != null && instructionResults != "") {

                try {
                    JSONArray results = new JSONArray(instructionResults);
                    JSONObject firstResult = results.getJSONObject(0);
                    instructionSteps = firstResult.getJSONArray("steps");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                fillStepper(instructionSteps);
                showData();
            }
        }

        private void fillStepper(JSONArray instructionSteps) {

            ArrayList<SteppersItem> mSteps = new ArrayList<>();

            SteppersView.Config steppersViewConfig = new SteppersView.Config();
            steppersViewConfig.setOnFinishAction(new OnFinishAction() {
                @Override
                public void onFinish() {
                    // Action on last step Finish button
                }
            });

            steppersViewConfig.setOnCancelAction(new OnCancelAction() {
                @Override
                public void onCancel() {
                    finish(); // what to do about this?????????
                }
            });

            steppersViewConfig.setOnChangeStepAction(new OnChangeStepAction() {
                @Override
                public void onChangeStep(int position, SteppersItem activeStep) {
                    // Action when click continue on each step
                }
            });

            // Setup Support Fragment Manager for fragments in steps
            steppersViewConfig.setFragmentManager(getSupportFragmentManager());

            for (int i = 0; i < instructionSteps.length(); i++) {

                String instructions = null;

                try {
                    JSONObject step = instructionSteps.getJSONObject(i);
                    instructions = step.getString("step");

                } catch (Exception e) {
                    e.printStackTrace();
                }

                SteppersItem stepFirst = new SteppersItem();
                stepFirst.setLabel(instructions);
                stepFirst.setPositiveButtonEnable(true);

                mSteps.add(stepFirst);
            }

            SteppersView steppersView = (SteppersView) findViewById(R.id.steppersView);
            steppersView.setConfig(steppersViewConfig);
            steppersView.setItems(mSteps);
            steppersView.build();
        }
    }

    public class IngredientsSearchTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            return FakeDataUtils.fakeIngredientResults();
//            StringBuilder response = null;
//            try {
//
//                URLConnection connection = urls[0].openConnection();
//                connection.setRequestProperty("X-Mashape-Key", "TyzoL5wDIGmshTDH7Jccy4e88NJEp15YcuYjsnFbzup4sC4INc");
//                connection.setRequestProperty("Accept", "application/json");
//
//                InputStream is = connection.getInputStream();
//                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//                response = new StringBuilder(); // or StringBuffer if Java version 5+
//                String line;
//                while ((line = rd.readLine()) != null) {
//                    response.append(line);
//                    response.append('\r');
//                }
//                rd.close();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return response.toString();
        }

        @Override
        protected void onPostExecute(String recipeInformationResults) {

            String displayInfo = "";

            if (recipeInformationResults != null && recipeInformationResults != "") {

                try {
                    JSONObject recipeInformation = new JSONObject(recipeInformationResults);
                    JSONArray ingredients = recipeInformation.getJSONArray("extendedIngredients");

                    for (int i = 0; i < ingredients.length(); i++) {

                        JSONObject item = ingredients.getJSONObject(i);
                        displayInfo += "\n" +
                                formatDouble(item.getDouble("amount")) + " " +
                                item.getString("unitLong") +
                                item.getString("name");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            mIngredients.setText(displayInfo);
        }

        private String formatDouble(double d) {

            if(d == (long) d) {
                return String.format("%d", (long) d);
            } else {
                return String.format("%s", d);
            }
        }
    }

    private void showLoading() {
        mStepperView.setVisibility(View.INVISIBLE);
        mLoading.setVisibility(View.VISIBLE);
    }

    private void showData() {
        mLoading.setVisibility(View.INVISIBLE);
        mStepperView.setVisibility(View.VISIBLE);
    }
}

