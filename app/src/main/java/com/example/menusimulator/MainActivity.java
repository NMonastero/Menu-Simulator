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

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;


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
        int page = 0;
        int viewedGoon;

        private long thisTimeFrame;
        public Menu(Context context) {
            super(context);

            ourHolder = getHolder();
            paint = new Paint();
        }

        @Override
        public void run() {
            Random r = new Random();

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
            //The image representing each goon will be defined in Information so the circles drawn here are temporary
            if (ourHolder.getSurface().isValid()) {
                // Lock the canvas ready to draw
                canvas = ourHolder.lockCanvas();

                width = canvas.getWidth();
                height = canvas.getHeight();

                // Draw the background color
                canvas.drawColor(Color.argb(255, 80, 30, 30));

                // Choose the brush color for drawing
                if(state == 1) {
                    paint.setColor(GREEN);
                    canvas.drawCircle(400, 220, 100, paint);

                    paint.setColor(RED);
                    canvas.drawCircle(400, 720, 100, paint);

                    paint.setColor(BLUE);
                    canvas.drawCircle(400, 1220, 100, paint);

                    paint.setTextSize(100);
                    paint.setColor(Color.argb(255, 255, 255, 255));
                    canvas.drawText(MainActivity.goonDex[page].name, 400, 350, paint);
                    canvas.drawText(MainActivity.goonDex[page+1].name, 400, 850, paint);
                    canvas.drawText(MainActivity.goonDex[page+2].name, 400, 1350, paint);

                    paint.setColor(Color.argb(255, 0,0,0));
                    canvas.drawRect(0,490,1500, 510, paint);
                    canvas.drawRect(0,990,1500, 1010, paint);
                }

                if(state == 2){
                    int[] stats = MainActivity.goonDex[viewedGoon].getBaseStats();
                    paint.setColor(Color.argb(255, 0,0,0));
                    canvas.drawRect(150, 200, 400, 400, paint);
                    paint.setTextSize(75);
                    paint.setColor(Color.argb(255, 255, 255, 255));
                    canvas.drawText(MainActivity.goonDex[viewedGoon].name, 150, 150, paint);

                    paint.setTextSize(50);
                    canvas.drawText("HEALTH: " + stats[0], 600, 150, paint);
                    canvas.drawText("C-ATT: " + stats[1], 600, 210, paint);
                    canvas.drawText("C-DEF: " + stats[2], 600, 270, paint);
                    canvas.drawText("F-ATT: " + stats[3], 600, 330, paint);
                    canvas.drawText("F-DEF: " + stats[4], 600, 390, paint);
                    canvas.drawText("PRIO: " + stats[5], 600, 450, paint);
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
            if(motionEvent.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                if(y <= 500){
                    state = 2;
                    viewedGoon = page;
                }

                else if(y > 500 && y <= 1000){
                    state = 2;
                    viewedGoon = page + 1;
                }

                else if(y > 1000 && y <= 1500){
                    state = 2;
                    viewedGoon = page + 2;
                }
            }
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
