package com.example.curookie.ressystem;

import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.media.Image;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSubmit, btnGoTime, btnTimeSub, btnGoPeo;
    Switch sw;
    Chronometer cro;
    CalendarView cv;
    TimePicker tp;
    EditText num1, num2, num3;
    RadioGroup rg, rg2;
    RadioButton rb1, rb4;
    ImageView iv;
    TextView tot, sale, price;
    FrameLayout fm1, fm2, bg;
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

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) { bg.setVisibility(View.VISIBLE); bg.setBackgroundColor(Color.parseColor("#FFEBCD"));
                    page1.setVisibility(View.VISIBLE);
                    page2.setVisibility(View.INVISIBLE);
                    cro.setBase(SystemClock.elapsedRealtime());
                    cro.start();
                    cro.setTextColor(Color.BLUE);
                }
                else { bg.setVisibility(View.INVISIBLE);
                    cro.setBase(SystemClock.elapsedRealtime());
                    cro.stop();
                    cro.setTextColor(Color.DKGRAY);
                    year=-1;
                    mon=-1;
                    day=-1;
                    hour=-1;
                    min=-1;
                    flag=0;
                    rb1.setChecked(true);
                    rb4.setChecked(true);
                    num1.setText(null);
                    num2.setText(null);
                    num3.setText(null);
                    tot.setText("총 명수 : ");
                    sale.setText("할인 금액 : ");
                    price.setText("결제 금액 : ");
                }
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton) iv.setImageResource(R.drawable.img1);
                else if(i==R.id.radioButton2) iv.setImageResource(R.drawable.img2);
                else if(i==R.id.radioButton3) iv.setImageResource(R.drawable.img3);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(!num1.getText().toString().matches("^[0-9]+$")||!num2.getText().toString().matches("^[0-9]+$")||!num3.getText().toString().matches("^[0-9]+$")) {
                    Toast.makeText(getApplicationContext(),"인원을 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                double s=0.05;
                if(rg.getCheckedRadioButtonId()==R.id.radioButton2) s=0.1;
                else if (rg.getCheckedRadioButtonId()==R.id.radioButton3) s=0.2;

                int n1 = Integer.parseInt(num1.getText().toString());
                int n2 = Integer.parseInt(num2.getText().toString());
                int n3 = Integer.parseInt(num3.getText().toString());

                DecimalFormat df = new DecimalFormat("#,##0");


                int sal = (int) Math.round((n1*15000+n2*12000+n3*8000)*s/10)*10;
                int real = (n1*15000+n2*12000+n3*8000)-sal;
                tot.setText("총 명수 : "+df.format((n1+n2+n3)));
                sale.setText("할인 금액 : "+df.format(sal));
                price.setText("결제 금액 : "+df.format(real));
                flag=1;
            }
        });

        btnGoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bg.setBackgroundColor(Color.parseColor("#E0FFFF"));
                page1.setVisibility(View.INVISIBLE);
                page2.setVisibility(View.VISIBLE);
            }
        });

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton4) {
                    cv.setVisibility(View.VISIBLE);
                    tp.setVisibility(View.INVISIBLE);
                } else if (i==R.id.radioButton5) {
                    cv.setVisibility(View.INVISIBLE);
                    tp.setVisibility(View.VISIBLE);
                }
            }
        });

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                year=i; mon=i1; day=i2;
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                hour=i; min=i1;
            }
        });

        btnGoPeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bg.setBackgroundColor(Color.parseColor("#FFEBCD"));
                page1.setVisibility(View.VISIBLE);
                page2.setVisibility(View.INVISIBLE);
            }
        });

        btnTimeSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==0) {
                    Toast.makeText(getApplicationContext(),"인원예약을 먼저 하세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(year==-1||min==-1) {
                    Toast.makeText(getApplicationContext(),"날짜와 시간을 입력하세요!",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(),year+"-"+mon+"-"+day+" "+hour+":"+min+" 예약완료되었습니다.",Toast.LENGTH_SHORT).show();
                cro.stop();
                cro.setTextColor(Color.DKGRAY);
            }
        });
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
        iv = (ImageView)findViewById(R.id.imageView);
        page1 = (LinearLayout)findViewById(R.id.page);
        page2 = (LinearLayout)findViewById(R.id.page2);
        rb1 =(RadioButton)findViewById(R.id.radioButton);
        rb4 =(RadioButton)findViewById(R.id.radioButton4);
        fm1 = (FrameLayout)findViewById(R.id.fm1);
        fm2 = (FrameLayout)findViewById(R.id.fm2);
        fm1.setBackgroundColor(Color.parseColor("#F9966B"));
        fm2.setBackgroundColor(Color.parseColor("#F9966B"));
    }
}
