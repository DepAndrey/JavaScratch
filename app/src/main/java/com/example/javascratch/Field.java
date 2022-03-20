package com.example.javascratch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class Field extends SurfaceView implements SurfaceHolder.Callback {

    MainThread mainThread;
    Entity amogus;

    public Field(Context context) {
        super(context);

        getHolder().addCallback(this);

        setBackgroundColor(getResources().getColor(R.color.white));
        amogus = new Entity(getResources());
        amogus.setSkin(R.drawable.minecraft);
        amogus.setSize(200, 200);

        mainThread = new MainThread(this);
        setFocusable(true);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(canvas != null){
            amogus.draw(canvas);
        }
    }

    public void update(){
        amogus.update();

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        mainThread.setRunning(true);
        mainThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while(retry){

            try{
                mainThread.setRunning(false);
                mainThread.join();
            }catch (Exception e){
                e.printStackTrace();
            }
            retry = false;
        }
    }
}
