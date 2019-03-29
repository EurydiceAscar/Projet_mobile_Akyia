package com.esiea.akyia.sharies;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class ParametersActivity extends AppCompatActivity {
    Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parameters);

        final Button eng_btn = findViewById(R.id.btn_english);

        final Button fr_btn = findViewById(R.id.btn_french);


        eng_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setLocale("en");
                Toast.makeText(getApplicationContext(), "English language selected", Toast.LENGTH_SHORT).show();
                Intent I1 = new Intent(ParametersActivity.this,MainActivity.class);
                startActivity(I1);

            }

        });

        fr_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setLocale("fr");
                Toast.makeText(getApplicationContext(),"Langue française selectionnée", Toast.LENGTH_SHORT).show();
                Intent I1 = new Intent(ParametersActivity.this,MainActivity.class);
                startActivity(I1);

            }

        });

    }

    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, ParametersActivity.class);
        startActivity(refresh);
    }
}
