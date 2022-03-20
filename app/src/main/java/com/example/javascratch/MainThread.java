package com.example.javascratch;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread{

    private SurfaceHolder holder;
    private Field field;
    private boolean isRunning;
    private static Canvas canvas;
    private int targetFPS = 30;


    MainThread(Field field){
        super();
        this.field = field;
        holder = field.getHolder() ;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount =0;
        long targetTime = 1000/targetFPS;

        while(isRunning){
            startTime = System.nanoTime();
            canvas = null;
            try{
                canvas = holder.lockCanvas();
                synchronized (holder){
                    field.update();
                    field.draw(canvas);
                    field.postInvalidate();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                if(canvas != null){
                    try{
                        holder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime-timeMillis;

            try{
                this.sleep(waitTime);
            }catch(Exception e){}
        }
    }


    public void setRunning(boolean running) {
        isRunning = running;
    }
}
