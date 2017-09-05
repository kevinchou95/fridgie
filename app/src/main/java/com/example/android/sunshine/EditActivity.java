/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.sunshine.data.FoodContract;
import com.example.android.sunshine.data.FoodContract.FoodEntry;
import com.example.android.sunshine.utilities.CustomDateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends DialogFragment {

    private Cursor mCursor;
    private static String mFoodName;
    private static Long mDateLong;
    private static String mDateString;
    private static int mDaysDifferenceCount;

    private static EditText mNameView;
    private static TextView mDaysDifference;
    private static Button mDateAdded;

    public static EditActivity newInstance(String foodName) {

        EditActivity frag = new EditActivity();
        Bundle args = new Bundle();
        args.putString("foodName", foodName);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_edit, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().getWindow().setTitle("Edit Food Item");
        getDialog().setCanceledOnTouchOutside(true);

        mNameView = (EditText) view.findViewById(R.id.et_food_name);
        mDaysDifference = (TextView) view.findViewById(R.id.tv_days_difference);
        mDateAdded = (Button) view.findViewById(R.id.bt_show_calendar);
        mDateAdded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(mDateLong);
            }
        });

        Context context = getActivity().getApplicationContext();
//        mFoodName = getIntent().getStringExtra("foodName");
        mFoodName = getArguments().getString("foodName", "eh???");

        mCursor = context.getContentResolver().query(
                FoodEntry.CONTENT_URI,
                null,
                FoodEntry.COLUMN_NAME + " = ? ",
                new String[] { mFoodName },
                null
        );
        mCursor.moveToNext();
        mDateLong = mCursor.getLong(MainActivity.INDEX_DATE + 1);
        mDaysDifferenceCount = CustomDateUtils.getDaysDifference(mDateLong);
        mDateString = CustomDateUtils.getDayName(context, mDateLong);
        mNameView.setText(mFoodName);
        mDateAdded.setText(mDateString);

        String label = " days in fridge";
        if (mDaysDifferenceCount == 1) {
            label = " day in fridge";
        }
        mDaysDifference.setText(Integer.toString(mDaysDifferenceCount) + label);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        saveChanges(getActivity());
        super.onDismiss(dialog);
    }

    private void saveChanges(Context context) {

        context.getContentResolver().delete(FoodContract.FoodEntry.CONTENT_URI,
                FoodContract.FoodEntry.COLUMN_NAME + "=?", new String[]{ mFoodName });

        mFoodName = mNameView.getText().toString();
        ContentValues foodToAdd = new ContentValues();
        foodToAdd.put(FoodContract.FoodEntry.COLUMN_NAME, mFoodName);
        foodToAdd.put(FoodContract.FoodEntry.COLUMN_DATE, mDateLong);

        context.getContentResolver().bulkInsert(
                FoodContract.FoodEntry.CONTENT_URI,
                new ContentValues[]{ foodToAdd });
    }


    private void showDatePickerDialog(long dateInMillis) {
        DialogFragment newFragment = new DatePickerFragment();

        SimpleDateFormat format = new SimpleDateFormat("M/d/y");
        String formattedDate = format.format(dateInMillis);
        String[] split = formattedDate.split("/");
        int[] dateArgs = new int[3];

        for (int i = 0; i < dateArgs.length; i++) {
            dateArgs[i] = Integer.parseInt(split[i]);
        }

        Bundle date = new Bundle();
        date.putInt("month", dateArgs[0]);
        date.putInt("day", dateArgs[1]);
        date.putInt("year", dateArgs[2]);

        newFragment.setArguments(date);
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private static int mMonth;
        private static int mDay;
        private static int mYear;

        @Override
        public void setArguments(Bundle args) {
            super.setArguments(args);

            mMonth = args.getInt("month") - 1;
            mDay = args.getInt("day");
            mYear = args.getInt("year");
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, mYear, mMonth, mDay);
            dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            return dialog;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {

            month++;
            if (month >= 13) {
                month = 1;
                year++;
            }

            mDateString = month + "/" + day + "/" + year;

            SimpleDateFormat formatter = new SimpleDateFormat("M/d/y");
            Date d = null;
            try {
                d = formatter.parse(mDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mDateLong = CustomDateUtils.normalizeDate(d.getTime());
            mDateAdded.setText(CustomDateUtils.getDayName(getActivity(), mDateLong));

            mDaysDifferenceCount = CustomDateUtils.getDaysDifference(mDateLong);

            String label = " days in fridge";
            if (mDaysDifferenceCount == 1) {
                label = " day in fridge";
            }
            mDaysDifference.setText(Integer.toString(mDaysDifferenceCount) + label);
        }
    }
}