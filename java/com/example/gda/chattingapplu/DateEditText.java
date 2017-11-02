package com.example.gda.chattingapplu;

/**
 * Created by gda on 10/26/2017.
 */

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DateEditText extends AppCompatEditText implements View.OnTouchListener {

    private Calendar _calendar;
    private DatePickerDialog.OnDateSetListener _dateListener;

    public DateEditText(Context context) {
        super(context);
        init();
    }

    public DateEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.setOnTouchListener(this);
        _calendar = Calendar.getInstance();
        _dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                _calendar.set(Calendar.YEAR, year);
                _calendar.set(Calendar.MONTH, monthOfYear);
                _calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        this.setText(sdf.format(_calendar.getTime()));
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_UP:
                new DatePickerDialog(getContext(), _dateListener, _calendar
                        .get(Calendar.YEAR), _calendar.get(Calendar.MONTH),
                        _calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            default:
                break;
        }
        return true;
    }
}

