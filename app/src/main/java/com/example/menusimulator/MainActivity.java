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
    //AsteroidView asteroidView;

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
//        asteroidView = new AsteroidView(this);
//        setContentView(asteroidView);
    }

    class Menu extends SurfaceView implements Runnable {
        Thread gameThread = null;
        SurfaceHolder ourHolder;
        volatile boolean playing;
        boolean paused = false;
        boolean bump = false;
        Canvas canvas;
        Paint paint;
        int y;
        int posx, posy;
        int dx, dy;
        int height, width;
        int state = 1;
        //boulder[] b;
        //paddle p;


        private long thisTimeFrame;
        public Menu(Context context) {
            super(context);

            ourHolder = getHolder();
            paint = new Paint();
        }

        @Override
        public void run() {
            Random r = new Random();

//            p = new paddle();
//            p.x =500;
//            p.y = 1250;
//            p.dx = 50;
//            p.dy = 0;
//
//            b = new boulder[1];
//
//            b[0] = new boulder();
////            b[0].x = r.nextInt(150)-50;
////            b[0].y = r.nextInt(150)-50;
////            b[0].dx = r.nextInt(150)-50;
////            b[0].dy = r.nextInt(150)-50;
//            b[0].x = 100;
//            b[0].y = 80;
//            b[0].dx = 50;
//            b[0].dy = 45;
//            b[0].diameter = 95;
//            for (int i = 0; i < 1; ++i) {
//                b[i] = new boulder();
//                b[i].x = r.nextInt(50);
//                b[i].y = r.nextInt(50);
//                b[i].dx = r.nextInt(30) - 15;
//                b[i].dy = r.nextInt(30) - 15;
//                b[i].diameter = 95;
//            }


            while (playing)
            {
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
        public void update() {
            y = y + 5;
            if (y > 200)
                y = 5;

            posx += dx;
            posy += dy;
//            if ((posx > width) || (posx < 0))
//                dx = -dx;
//            if ((posy > height) || (posy < 800))
//                dy = -dy;
//            if((posy) < 800)
//                dy = -dy;

            //&& (posx + b[0].diameter > p.x) && (posx + b[0].diameter < p.x + 300

//            if(bump == true){
//                p.update();
//            }
//
//            int inc = 3;
//            if(b[0].y+b[0].diameter >= p.y && b[0].y+b[0].diameter <= p.y+30){
//                if(b[0].x + 95 >= p.x  && b[0].x + 100 <= p.x + 500){
//                    if(b[0].dx > 0) {
//                        b[0].dx += inc;
//                    }
//                    if(b[0].dx < 0) {
//                        b[0].dx -= inc;
//                    }
//                    b[0].dy = -b[0].dy;
//                    score++;
//                    if(score > highScore){
//                        highScore++;
//                    }
//                }
//            }
//
//            if(b[0].y > 1250){
//                paused = !paused;
//            }
//
//            //for (int i = 0; i < 5; ++i)
//            b[0].update();


        }
        public void draw() {
            if (ourHolder.getSurface().isValid()) {
                // Lock the canvas ready to draw
                canvas = ourHolder.lockCanvas();

                width = canvas.getWidth();
                height = canvas.getHeight();

                // Draw the background color
                canvas.drawColor(Color.argb(255, 26, 80, 182));

                // Choose the brush color for drawing
                if(state == 1) {
                    paint.setTextSize(400);
                    paint.setColor(Color.argb(255, 255, 255, 255));
                    canvas.drawText(MainActivity.goonDex[0].name, 500, 300, paint);
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
        public boolean onTouchEvent(MotionEvent motionEvent){
            int x = (int)motionEvent.getX();
            int y = (int)motionEvent.getY();
//            if(motionEvent.getAction() == android.view.MotionEvent.ACTION_DOWN) {
//                if(x < 500 && p.dx > 0 || x >= 500 && p.dx < 0){
//                    p.dx = -p.dx;
//                }
//                bump = !bump;
//            }
//            if(paused){
//                if(x <= 800 && x >= 350 && y >= 650 && y <= 700) {
//                    score = 0;
//                    b[0].x = 100;
//                    b[0].y = 80;
//                    b[0].dx = 50;
//                    paused = !paused;
//                }
//            }
            if(motionEvent.getAction() == android.view.MotionEvent.ACTION_UP)
                bump = !bump;
            return true;
        }

    }


    // This method executes when the player starts the game
    @Override
    protected void onResume() {
        super.onResume();

        // Tell the gameView resume method to execute
        menu.resume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();

        // Tell the gameView pause method to execute
        menu.pause();
    }

}
