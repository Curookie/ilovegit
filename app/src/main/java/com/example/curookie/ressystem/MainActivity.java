package com.example.curookie.ressystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    Button btnSubmit, btnGoTime, btnTimeSub, btnGoPeo;
    Switch sw;
    Chronometer cro;
    CalendarView cv;
    TimePicker tp;
    EditText num1, num2, num3;
    RadioGroup rg, rg2;
    TextView tot, sale, price;
    FrameLayout bg;
    LinearLayout page1, page2;
    int year=-1;
    int mon=-1;
    int day=-1;
    int hour=-1;
    int min=-1;
    int flag =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("놀이동산 예약시스템");

        initId();
    }

    private void initId() {
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnGoTime = (Button)findViewById(R.id.btnGoTime);
        btnTimeSub = (Button)findViewById(R.id.btnTimeSub);
        btnGoPeo = (Button)findViewById(R.id.btnGoPeo);
        sw = (Switch)findViewById(R.id.switch1);
        cro = (Chronometer)findViewById(R.id.chronometer2);
        cv = (CalendarView)findViewById(R.id.calendarView);
        tp = (TimePicker)findViewById(R.id.timePicker);
        num1 = (EditText) findViewById(R.id.editText1);
        num2 = (EditText) findViewById(R.id.editText2);
        num3 = (EditText) findViewById(R.id.editText3);
        rg = (RadioGroup) findViewById(R.id.rg);
        rg2 = (RadioGroup) findViewById(R.id.rg2);
        tot = (TextView) findViewById(R.id.tot);
        sale = (TextView) findViewById(R.id.sale);
        price = (TextView) findViewById(R.id.price);
        bg = (FrameLayout) findViewById(R.id.bg);
        page1 = (LinearLayout)findViewById(R.id.page);
        page2 = (LinearLayout)findViewById(R.id.page2);
    }
}
