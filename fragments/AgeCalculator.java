package com.velsol.agecalci.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.velsol.agecalci.R;

import java.util.Calendar;


public class AgeCalculator extends Fragment
{
    EditText cday1,cmonth,cyear,userDay,userMonth,userYear;
    Button getDetails,clearBtn;
    TextView text_view,yeras,months,days,hours,minutes,weeks;
    int fc_month;
    int yrs_2,oddday1,dayss,yrs;
    String roju[]={"sunday","Monday","tuesday","wednesday","thursday","friday","saturday"};
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
         v= inflater.inflate(R.layout.fragment_age_calculator, container, false);
        initViews();
        return v;
    }
    public void initViews()
    {
        Calendar cal=Calendar.getInstance();
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int month=cal.get(Calendar.MONTH);
        int year=cal.get(Calendar.YEAR);
        cday1=(EditText)v.findViewById(R.id.Current_day);
        cmonth=(EditText)v.findViewById(R.id.Current_month);
        cyear=(EditText)v.findViewById(R.id.Current_year);
        userDay=(EditText)v.findViewById(R.id.user_day);
        userMonth=(EditText)v.findViewById(R.id.user_month);
        userYear=(EditText)v.findViewById(R.id.user_year);
        getDetails=(Button) v.findViewById(R.id.submit_btn);
        clearBtn=(Button)v.findViewById(R.id.clear_btn);
        text_view=(TextView)v.findViewById(R.id.text_view);
        //displaying months
        yeras=(TextView)v.findViewById(R.id.years);
        months=(TextView)v.findViewById(R.id.months);
        days=(TextView)v.findViewById(R.id.days);

        hours=(TextView)v.findViewById(R.id.hours);
        minutes=(TextView)v.findViewById(R.id.minutes);
        weeks=(TextView)v.findViewById(R.id.weeks);

        cday1.setText(""+day);
        cmonth.setText(""+(month+1));
        cyear.setText(""+year);
        getDetails.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //performing the data
                dataProcess();
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEdittexts();
            }
        });
    }
    public void dataProcess()
    {

        if (userDay.getText().toString().isEmpty())
        {
            Toast.makeText(getContext(), "please provide details", Toast.LENGTH_SHORT).show();
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
        else if (Integer.parseInt(userDay.getText().toString().trim())>31)
        {
            userDay.setError("enter accurate day");
        }
        else if (Integer.parseInt(userMonth.getText().toString().trim())>12)
        {
            userMonth.setError("enter accurate month");
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
                    forDayPurpose();
                } else {
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
                if (fc_month < userMonths) {
                    fc_month = fc_month + 12;
                    int mon = fc_month - userMonths;
                    int dd = f_day - userDays;
                    int year = cyears - userYears;
                    int leapYe = year % 4;
                    //text_view.setText(""+year+"years completed"+mon+" months"+dd+" days ");
                    yeras.setText("" + (year-1));
                    months.setText("" + mon);
                    days.setText("" + dd);

                    int hr=(year*365+mon*30+dd)*24;
                    hours.setText(""+hr);
                    minutes.setText(""+(hr*60));
                    weeks.setText(""+(year*365+mon*30+dd)/7);

                } else {

                    int ffmonths = fc_month - userMonths;
                    int dd = f_day - userDays;
                    int year = cyears - userYears;
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
    public void forDayPurpose()
    {
        yrs=Integer.parseInt(cyear.getText().toString().trim())-1;
        if (yrs<1600)
        {

        }
        else
        {
            int yrs_1=yrs-1600;
            switch ((yrs_1/100))
            {
                case 1:
                {
                    yrs_2=100-yrs_1;
                    oddday1=5;
                    processData();
                    break;
                }
                case 2:
                {
                    yrs_2=200-yrs_1;
                    oddday1=3;
                    processData();
                    break;
                }
                case 3:
                {
                    yrs_2=300-yrs_1;
                    oddday1=1;
                    processData();
                    break;
                }
                case 4:
                {
                    yrs_2=400-yrs_1;
                    oddday1=0;
                    processData();
                    break;
                }
                default:
                    break;
            }
        }
    }
    public void processData()
    {
        int quotient = yrs_2 / 4;
        int one=(quotient*2)+((yrs_2-quotient)*1);
        int ss=one/7;
        int odd2=one%7;
        int final_oddday2=-1*odd2;

        int uday=Integer.parseInt(userDay.getText().toString().trim());
        int umonth=Integer.parseInt(userMonth.getText().toString().trim());
        switch (umonth)
        {
            case 1:
            {
                dayss=uday;
                break;
            }case 2:
        {
            dayss=31+uday;
            break;
        }case 3:
        {
            dayss=31+28+uday;
            break;
        }case 4:
        {
            dayss=31+28+31+uday;
            break;
        }case 5:
        {
            dayss=31+28+31+30+uday;
            break;
        }case 6:
        {
            dayss=31+28+31+30+31+uday;
            break;
        }case 7:
        {
            dayss=31+28+31+30+31+30+uday;
            break;
        }case 8:
        {
            dayss=31+28+31+30+31+30+31+uday;
            break;
        }case 9:
        {
            dayss=31+28+31+30+31+30+31+31+uday;
            break;
        }case 10:
        {
            dayss=31+28+31+30+31+30+31+31+30+uday;
            break;
        }case 11:
        {
            dayss=31+28+31+30+31+30+31+31+30+31+uday;
            break;
        }case 12:
        {
            dayss=31+28+31+30+31+30+31+31+30+31+30+uday;
            break;
        }

        }
        //here convert days into weeks and odd days
        int oddday3=dayss%7;
        int finalodd=oddday1+final_oddday2+oddday3;
        Toast.makeText(getContext(), ""+roju[finalodd]+"odd number is"+finalodd, Toast.LENGTH_SHORT).show();
    }
}
