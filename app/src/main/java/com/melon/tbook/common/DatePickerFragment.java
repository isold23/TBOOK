package com.melon.tbook.common;
/**
 * @Project：TBOOK
 * @Author: Liwei Wang
 * @Date: 3/1/2024
 */
import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DatePickerDialog {

    public DatePickerFragment(Context context, OnDateSetListener listener,
                                  int year, int month, int dayOfMonth) {
        super(context, listener, year, month, dayOfMonth);
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int month, int dayOfMonth) {
        super.onDateChanged(view, year, month, dayOfMonth);

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        updateDate(currentYear, currentMonth, currentDay);
    }
}
