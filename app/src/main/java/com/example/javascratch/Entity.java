package com.example.javascratch;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import java.util.ArrayDeque;
import java.util.Queue;


public class Entity  {
    private final Resources res;
    private Bitmap skin;
    int messageTime = 0;
    String message = null;
    Paint textPaint;
    ArrayDeque<EntityAction> queue = new ArrayDeque<>();

    Point size = new Point(100, 100);
    int windowWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    int windowHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    Point position = new Point(windowWidth/2, windowHeight/2);
    private boolean isSayed = false;

    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
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
        if(message != null){
            canvas.drawText(message, position.x + size.x / 2, position.y - size.y * 1 / 3, textPaint);
        }
        canvas.drawBitmap(skin, position.x - size.x/2, position.y - size.y/2, null);
    }


    //Сделать шаги
    public void stepFor(Direction direction, int steps, int speed){
        for(int i = 0; i < steps; i++){
            switch (direction) {
                case UP:
                    queue.add((e) -> {
                        e.position.y -= speed;
                    });
                    break;
                case DOWN:
                    queue.add((e) -> {
                        e.position.y += speed;
                    });

                    break;
                case LEFT:
                    queue.add((e) -> {
                        e.position.x -= speed;
                    });
                    break;
                case RIGHT:
                    queue.add((e) -> {
                        e.position.x += speed;
                    });
            }
        }
    }

    //Сказать
    void sayFor(String message, int messageTime){
        for (int i = 0; i < messageTime; i++){
            queue.add((e) -> {
                e.message = message;
            });
        }
        queue.add((entity -> entity.message = null));
    }



    public void update() {
        if(queue.peek() != null){
            queue.pop().callback(this);
        }
//        if(position.y > 0) {
//            step(Direction.UP, 1);
//        }
//        say("Привет", 55);
    }



    enum Direction{
        UP, DOWN, LEFT, RIGHT
    }
}

