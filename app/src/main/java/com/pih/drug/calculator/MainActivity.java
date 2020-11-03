package com.pih.drug.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final int MINIMUM_DAYS = 30;
    public static final int DIVIDER_MULTIPLIER = 2;
    private DatePicker drugDatePicker;
    private EditText numberOfDrugsEditText;
    private TextView nextAppointmentDateTextView;

    public void calculate(View view)
    {
        String input = numberOfDrugsEditText.getText().toString();
        if(input == null || input.equals("")) {
            Toast.makeText(this,"Number of Drugs cannot be blank",Toast.LENGTH_LONG).show();
        }
        else {
            int year = drugDatePicker.getYear();
            int month = drugDatePicker.getMonth();
            int day = drugDatePicker.getDayOfMonth();

            Calendar calendar = Calendar.getInstance();
            calendar.set(year,month,day);
            calendar = calculateNextAppointmentDate(calendar, Integer.parseInt(input));
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateFormatted = dateFormat.format(calendar.getTime());
            String message = "Next Appointment Date is "+ dateFormatted;
            nextAppointmentDateTextView.setText(message);
            Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        }
    }

    private Calendar calculateNextAppointmentDate(Calendar calendar,int numberOfDrugs)
    {
        int divider = numberOfDrugs / MINIMUM_DAYS;
        int daysToNextAppointment = numberOfDrugs - (divider * DIVIDER_MULTIPLIER);
        calendar.add(Calendar.DAY_OF_MONTH,daysToNextAppointment);
        return calendar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drugDatePicker = findViewById(R.id.datePicker_drug_calculator);
        numberOfDrugsEditText = findViewById(R.id.et_number_of_drugs);
        nextAppointmentDateTextView = findViewById(R.id.next_appointment_textView);
    }
}