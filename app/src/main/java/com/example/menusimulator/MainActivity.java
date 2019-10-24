package com.example.menusimulator;

import android.os.Bundle;

import com.example.menusimulator.ui.Information;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Paint;

import java.util.Random;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {
    Menu menu;

    //still needs image variables and description string
    //it might be easier to consolidate these variables later but for now don't worry about it
    public Information green = new Information("Green",0,0,0,0,0,
            0,0,0,0,0,
            0,0,0,0,0,0,0, 0,0);

    public Information red = new Information("Red",0,0,0,0,0,
            0,0,0,0,0,
            0,0,0,0,0,0,0, 0, 0);

    public Information blue = new Information("Blue",0,0,0,0,0,
            0,0,0,0,0,
            0,0,0,0,0,0,0, 0, 0);

    static Information goonDex[] = new Information[3];

    public static Information getDexEntry(int i){
        return goonDex[i];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Information goonDex[] = new Information[3];
        goonDex[0] = green;
        goonDex[1] = red;
        goonDex[2] = blue;

        menu = new Menu(this);
        setContentView(menu);
    }

    class Menu extends SurfaceView implements Runnable {
        Thread gameThread = null;
        SurfaceHolder ourHolder;
        volatile boolean playing = true;
        boolean paused = false;
        boolean bump = false;
        Canvas canvas;
        Paint paint;
        int y;
        int posx, posy;
        int dx, dy;
        int height, width;
        int state = 1;


        private long thisTimeFrame;

        public Menu(Context context) {
            super(context);

            ourHolder = getHolder();
            paint = new Paint();
        }

        @Override
        public void run() {

            while (playing) {
                if (!paused) {
                    update();
                }
                draw();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {

                }
            }
        }

        //this only animates the goon
        public void update() {
            //animates in selection menu
            if (state == 1) {
            }

            //animates in specific menu
            else if (state == 2) {
            }

        }


        public void draw() {
            if (ourHolder.getSurface().isValid()) {
                // Lock the canvas ready to draw
                canvas = ourHolder.lockCanvas();

                width = canvas.getWidth();
                height = canvas.getHeight();

                // Draw the background color
                canvas.drawColor(Color.argb(255, 26, 80, 182));

                if (state == 1) {
                    paint.setColor(Color.argb(255, 255, 255, 255));
                    canvas.drawText(MainActivity.getDexEntry(0).name, 100, 100, paint);
                }

                else if (state == 2) {
                }

                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause() {
            playing = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("Error:", "joining thread");
            }

        }

        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        @Override
        public boolean onTouchEvent(MotionEvent motionEvent) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (motionEvent.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                //click detection on selection menu
                if (state == 1) {

                }
                //click detection on specific menu
                if (state == 2) {

                }
            }

            return true;
        }

    }

}
