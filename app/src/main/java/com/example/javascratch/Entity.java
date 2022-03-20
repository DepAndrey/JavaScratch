package com.example.javascratch;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;


public class Entity  {
    private final Resources res;
    private Bitmap skin;
    int messageTime = 0;
    String message = "";
    Paint textPaint;

    Point size = new Point(100, 100);
    int windowWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    int windowHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    Point position = new Point(windowWidth/2, windowHeight/2);
    private boolean isSayed = false;

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setSize(int width, int height) {
        this.size = new Point(width, height);
        skin = Bitmap.createScaledBitmap(skin, size.x, size.y, false);
    }

    public void setSkin(int path) {
        this.skin = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(res, path),
                size.x,
                size.y,
                false);

    }

    Entity(Resources res){
        this.res = res;
        skin = BitmapFactory.decodeResource(res, R.drawable.cat);
        textPaint = new Paint();
        textPaint.setColor(res.getColor(R.color.black));
        textPaint.setTextSize(45f);
    }

    public void draw(Canvas canvas){
        if(messageTime > 0){
            messageTime -= 1;
            canvas.drawText(message, position.x + size.x/2, position.y - size.y* 1/ 3, textPaint);
        }
        canvas.drawBitmap(skin, position.x - size.x/2, position.y - size.y/2, null);
    }


    //Сделать шаг
    public void step(Direction direction, int step){
        switch (direction){
            case UP:
                position.y -= step;
                break;
            case DOWN:
                position.y += step;
                break;
            case LEFT:
                position.x -= step;
                break;
            case RIGHT:
                position.x += step;
        }
    }

    //Сказать
    void say(String message, int messageTime){
        if(isSayed){
            return;
        }
        isSayed = true;
        this.message = message;
        this.messageTime = messageTime;

    }



    public void update() {
        toExecute();

//        if(position.y > 0) {
//            step(Direction.UP, 1);
//        }
//        say("Привет", 55);
    }

    int i = 0;

    private void toExecute() {
        if(i < 100){
            i++;
            step(Direction.UP, 5);
        }
    }

    enum Direction{
        UP, DOWN, LEFT, RIGHT
    }
}
