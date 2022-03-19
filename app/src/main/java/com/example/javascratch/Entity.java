package com.example.javascratch;

import android.graphics.Point;
import android.os.Build;
import android.util.Pair;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Entity extends androidx.appcompat.widget.AppCompatImageView {
    private ConstraintLayout field;

    private Pair<Integer, Integer> position;


    private int sizeWidth = 150;
    private int sizeHeight = 200;

    public Entity(ConstraintLayout field, Point windowSize){
        super(field.getContext());
        this.field = field;
        position = new Pair<>((windowSize.x - sizeWidth)/2, (windowSize.y - sizeHeight)/2);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(sizeWidth,sizeHeight);
        ConstraintLayout.LayoutParams paramPosition = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(position.first, position.second, 0, 0);
        params.startToStart = field.getId();
        params.topToTop = field.getId();
        setLayoutParams(params);
        setBackgroundResource(R.drawable.among_us);


        field.addView(this);
    }


    public void setPosition(int x, int y) {
        this.position = new Pair<>(x, y);
    }

    public void move(int x, int y){
        x = position.first + x;
        y = position.second + y;
        position = new Pair<>(x, y);
    }
}
