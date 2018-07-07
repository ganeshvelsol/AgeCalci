package com.velsol.agecalci;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HeightConverter extends AppCompatActivity
{
    Spinner spinner1,spinner2;
    String sp1[]={"centimeter","meters","kilometers","feet","inches"};
    String sp2[]={"centimeter","meters","kilometers","feet","inches"};
    String spinner1Selected,spinner2Selected;
    EditText edittexts;
    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height_converter);
        edittexts=(EditText)findViewById(R.id.edittexts);
        display=(TextView)findViewById(R.id.display);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        ArrayAdapter aa=new ArrayAdapter(this,R.layout.spinner_data,sp1);
        spinner1.setAdapter(aa);
        ArrayAdapter a2=new ArrayAdapter(this,R.layout.spinner_data,sp2);
        spinner2.setAdapter(a2);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                spinner1Selected=sp1[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String edit_value=edittexts.getText().toString().trim();
                spinner2Selected=sp2[position];
               if (edit_value.isEmpty())
               {
                   Snackbar snackbar=Snackbar.make(HeightConverter.this.findViewById(android.R.id.content),"enter a value",Snackbar.LENGTH_LONG);
                   snackbar.show();
               }
               else
               {
                   int val=Integer.parseInt(edit_value);
                   if (spinner1Selected=="centimeter")
                   {
                       switch (spinner2Selected)
                       {
                           case "centimeter":
                               {
                               Snackbar snackbar = Snackbar.make(HeightConverter.this.findViewById(android.R.id.content), "not possible", Snackbar.LENGTH_LONG);
                               snackbar.show();
                               break;
                           }
                           case "meters": {
                               display.setText("" + (0.01 * val));
                               break;
                           }
                           case "kilometers": {

                               break;
                           }
                           case "feet": {
                               display.setText("" + (0.0328084 * val));
                               break;
                           }
                           case "inches": {
                               display.setText("" + (0.393701 * val));
                               break;
                           }
                           default:
                       }
                   }else if (spinner1Selected=="meters")
                   {
                       switch (spinner2Selected)
                       {
                           case "centimeter":
                           {
                               display.setText("" + (100 * val));
                               break;
                           }
                           case "meters": {
                               display.setText("not possible");
                               break;
                           }
                           case "kilometers":
                               {
                                   display.setText(""+(0.001*val));
                               break;
                           }
                           case "feet": {
                               display.setText("" + (3.28084 * val));
                               break;
                           }
                           case "inches": {
                               display.setText("" + (39.3701 * val));
                               break;
                           }
                           default:
                       }
                   }
                   else if (spinner1Selected=="kilometers")
                   {
                       switch (spinner2Selected)
                       {
                           case "centimeter":
                           {
                               display.setText("" + (100000 * val));
                               break;
                           }
                           case "meters": {
                               display.setText(""+(1000*val));
                               break;
                           }
                           case "kilometers":
                           {
                               display.setText("not possible");
                               break;
                           }
                           case "feet": {
                               display.setText("" + (3280.84 * val));
                               break;
                           }
                           case "inches": {
                               display.setText("" + (39370.1 * val));
                               break;
                           }
                           default:
                       }
                   }else if (spinner1Selected=="feet")
                   {
                       switch (spinner2Selected)
                       {
                           case "centimeter":
                           {
                               display.setText("" + (30.48 * val));
                               break;
                           }
                           case "meters": {
                               display.setText(""+(0.3048*val));
                               break;
                           }
                           case "kilometers":
                           {
                               display.setText(""+(0.0003048*val));
                               break;
                           }
                           case "feet": {
                               display.setText("Not possible");
                               break;
                           }
                           case "inches": {
                               display.setText("" +(12* val));
                               break;
                           }
                           default:
                       }
                   }else if (spinner1Selected=="inches")
                   {
                       switch (spinner2Selected)
                       {
                           case "centimeter":
                           {
                               display.setText("" + (2.54 * val));
                               break;
                           }
                           case "meters": {
                               display.setText(""+(0.0254*val));
                               break;
                           }
                           case "kilometers":
                           {
                               //display.setText(""+(0.0003048*val));
                               break;
                           }
                           case "feet":
                               {
                               display.setText(""+(0.0833333*val));
                               break;
                           }
                           case "inches":
                               {
                               //display.setText(""+(val*));
                               break;
                           }
                           default:
                       }
                   }


               }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });

    }
}
