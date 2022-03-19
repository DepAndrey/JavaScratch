package com.example.javascratch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout field = findViewById(R.id.cl_field);

        Display display = getWindowManager().getDefaultDisplay();
        Point windowSize = new Point();
        display.getSize(windowSize);

        Entity entity = new Entity(field, windowSize);



    }
}