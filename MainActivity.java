package com.velsol.agecalci;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    EditText cday1,cmonth,cyear,userDay,userMonth,userYear;
    Button getDetails,clearBtn;
    TextView text_view,yeras,months,days,hours,minutes,weeks;
    int fc_month;
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // initViews();
        new Handler().postDelayed(new Runnable()
        {


            @Override
            public void run()
            {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(MainActivity.this, PageDisplay.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);


    }
    public void initViews()
    {
        Calendar cal=Calendar.getInstance();
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int month=cal.get(Calendar.MONTH);
        int year=cal.get(Calendar.YEAR);
        cday1=(EditText)findViewById(R.id.Current_day);
        cmonth=(EditText)findViewById(R.id.Current_month);
        cyear=(EditText)findViewById(R.id.Current_year);
        userDay=(EditText)findViewById(R.id.user_day);
        userMonth=(EditText)findViewById(R.id.user_month);
        userYear=(EditText)findViewById(R.id.user_year);
        getDetails=(Button) findViewById(R.id.submit_btn);
        clearBtn=(Button)findViewById(R.id.clear_btn);
        text_view=(TextView)findViewById(R.id.text_view);
        //displaying months
        yeras=(TextView)findViewById(R.id.years);
        months=(TextView)findViewById(R.id.months);
        days=(TextView)findViewById(R.id.days);

        hours=(TextView)findViewById(R.id.hours);
        minutes=(TextView)findViewById(R.id.minutes);
        weeks=(TextView)findViewById(R.id.weeks);

        cday1.setText(""+day);
        cmonth.setText(""+(month+1));
        cyear.setText(""+year);
        getDetails.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //performing the data
                //dataProcess();
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clearEdittexts();
            }
        });
    }
    public void dataProcess()
    {

        if (userDay.getText().toString().isEmpty())
        {
            Toast.makeText(this, "please provide details", Toast.LENGTH_SHORT).show();
            userDay.setError("enter value");
        }
        else if (userMonth.getText().toString().isEmpty())
        {
            userMonth.setError("enter value");
        }
        else if (userYear.getText().toString().isEmpty())
        {
            userYear.setError("enter value");

        }
        else if (!cday1.getText().toString().trim().isEmpty())
        {
            yeras.setVisibility(View.VISIBLE);
            months.setVisibility(View.VISIBLE);
            days.setVisibility(View.VISIBLE);
            int cday = Integer.parseInt(cday1.getText().toString().trim());
            int cmonths = Integer.parseInt(cmonth.getText().toString().trim());
            int cyears = Integer.parseInt(cyear.getText().toString().trim());
            int userDays = Integer.parseInt(userDay.getText().toString().trim());
            int userMonths = Integer.parseInt(userMonth.getText().toString().trim());
            int userYears = Integer.parseInt(userYear.getText().toString().trim());
            if (cday > userDays)
            {
                int fd1 = cday - userDays;
                if (cmonths > userMonths)
                {
                    //do direct operation
                    int fm1 = cmonths - userMonths;
                    int fyears = cyears - userYears;
                    int leapYear = fyears % 4;
                    int hrs=(fyears*365+fm1*30+fd1)*24;
                    int min=hrs*60;
                    // text_view.setText(""+fyears+"years completed "+fm1+" months"+fd1+" days , next birthday in "+(fm1-12));
                    yeras.setText("" + fyears);
                    months.setText("" + fm1);
                    days.setText("" + fd1);
                    hours.setText(""+hrs);
                    minutes.setText(""+min);
                    weeks.setText(""+(fyears*365+fm1*30+fd1)/7);

                } else
                    {
                    //int f_month=
                    int ffmon = cmonths + 12;
                    int ffmonths = ffmon - userMonths;
                    int fyearss = (cyears - 1) - userYears;
                    int llpyear = fyearss % 4;
                    int hrs=(fyearss*365+ffmonths*30+fd1)*24;
                    //text_view.setText(""+fyearss+"years completed"+ffmonths+" months"+fd1+" days next birthday in"+(userMonths-cmonths));
                    yeras.setText("" + fyearss);
                    months.setText("" + ffmonths);
                    days.setText("" + fd1);

                    hours.setText(""+hrs);
                    minutes.setText(""+(hrs)*60);
                    weeks.setText(""+(fyearss*365+ffmonths*30+fd1)/7);
                }
            } else if (cday < userDays) {
                int f_day = cday + 30;
                fc_month = cmonths - 1;
                if (fc_month < userMonths)
                {
                    fc_month = fc_month + 12;
                    int mon = fc_month - userMonths;
                    int dd = f_day - userDays;
                    int year = cyears - userYears-1;
                    int leapYe = year % 4;
                    //text_view.setText(""+year+"years completed"+mon+" months"+dd+" days ");
                    yeras.setText("" + year);
                    months.setText("" + mon);
                    days.setText("" + dd);

                    int hr=(year*365+mon*30+dd)*24;
                    hours.setText(""+hr);
                    minutes.setText(""+(hr*60));
                    weeks.setText(""+(year*365+mon*30+dd)/7);

                }
                else
                    {

                    int ffmonths = fc_month - userMonths;
                    int dd = f_day - userDays;
                    int year = cyears - userYears-1;
                    int leaps = year % 4;
                    //text_view.setText(""+year+"years completed "+ffmonths+" months"+dd+" days ");
                    yeras.setText("" + year);
                    months.setText("" + ffmonths);
                    days.setText("" + dd);

                    int hr=(year*365+ffmonths*30+dd)*24;
                    hours.setText(""+hr);
                    minutes.setText(""+(hr*60));
                    weeks.setText(""+(year*365+ffmonths*30+dd)/7);
                }
            } else if (cday == userDays) {
                int fdd = cday - userDays;
                if (cmonths > userMonths) {
                    //do direct operation
                    int fm1 = cmonths - userMonths;
                    int fyears = cyears - userYears;
                    int fleap = fyears % 4;
                    // text_view.setText(""+fyears+"years completed "+fm1+" months"+fdd+" days ");
                    yeras.setText("" + fyears);
                    months.setText("" + fm1);
                    days.setText("" + fdd);

                    int hr=(fyears*365+fm1*30+fdd)*24;
                    hours.setText(""+hr);
                    minutes.setText(""+(hr*60));
                    weeks.setText(""+(fyears*365+fm1*30+fdd)/7);
                } else if (cmonths < userMonths) {
                    //int f_month=
                    int ffmon = cmonths + 12;
                    int ffmonths = ffmon - userMonths;
                    int fyearss = (cyears - 1) - userYears;
                    int leapYe = fyearss % 4;
                    //text_view.setText(""+fyearss+"years completed "+ffmonths+" months"+fdd+" days ");
                    yeras.setText("" + fyearss);
                    months.setText("" + ffmonths);
                    days.setText("" + fdd);

                    int hr=(fyearss*365+ffmonths*30+fdd)*24;
                    hours.setText(""+hr);
                    minutes.setText(""+(hr*60));
                    weeks.setText(""+(fyearss*365+ffmonths*30+fdd)/7);
                } else if (cmonths == userMonths) {
                    int ffmonths = cmonths - userMonths;
                    int fyears = cyears - userYears;
                    int lprs = fyears % 4;
                    //text_view.setText(""+fyears+"years completed "+ffmonths+" months"+fdd+" days");
                    yeras.setText("" + fyears);
                    months.setText("" + ffmonths);
                    days.setText("" + fdd);

                    int hr=(fyears*365+ffmonths*30+fdd)*24;
                    hours.setText(""+hr);
                    minutes.setText(""+(hr*60));
                    weeks.setText(""+(fyears*365+ffmonths*30+fdd)/7);
                }
            }
        }
    }
    public void clearEdittexts()
    {
        userDay.setText("");
        userMonth.setText("");
        userYear.setText("");
        yeras.setText("");
        months.setText("");
        days.setText("");
        hours.setText("");
        minutes.setText("");
        weeks.setText("");
    }
}
