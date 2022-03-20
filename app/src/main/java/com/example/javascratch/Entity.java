package com.example.javascratch;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.util.Pair;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;


public class Entity  {
    private Bitmap skin;

    Point size = new Point(100, 100);
    int windowWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    int windowHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    Point position = new Point(windowWidth/2, windowHeight/2);

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setSize(int width, int height) {
        this.size = new Point(width, height);
        skin = Bitmap.createScaledBitmap(skin, size.x, size.y, false);
    }

    public void setSkin(int path) {
//        this.skin = BitmapFactory.decodeResource(Resources.getSystem(), path);
        this.skin = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(Resources.getSystem(), path),
                size.x,
                size.y,
                false);

    }

    Entity(){
        skin = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.cat);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(skin, position.x - size.x/2, position.y - size.y/2, null);
    }

    public void step(Direction direction, int step){
        switch (direction){
            case UP:
                position.y -= step;
                break;
            case DOWN:
                position.y += step;
                break;
            case LEFT:
                position.x += step;
                break;
            case RIGHT:
                position.x -= step;
        }
    }

    public void update() {
        if(position.y > 0) {
            step(Entity.Direction.UP, 1);
        }
    }

    enum Direction{
        UP, DOWN, LEFT, RIGHT
    }
}
