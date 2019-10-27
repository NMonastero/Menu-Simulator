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
    public Information green = new Information("Green",100,5,4,5,6, 5,
            222222111, 222111111,222222111,332222211, 222222111,
            0,3,5,4,5,2, 0,0);

    public Information red = new Information("Red",100,6,4,5,4, 6,
            332222211,222111111,222222111,222111111, 332222211,
            0,0,0,0,0,0, 0, 0);

    public Information blue = new Information("Blue",100,4,6,5,6, 4,
            222111111,332222211,222222111,332222211, 222111111,
            0,0,0,0,0,0, 0, 0);

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
        int lvl = 0;

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
                    int[][] maxStats = {MainActivity.goonDex[viewedGoon].maxInc(1),
                            MainActivity.goonDex[viewedGoon].maxInc(2),MainActivity.goonDex[viewedGoon].maxInc(3),
                            MainActivity.goonDex[viewedGoon].maxInc(4),MainActivity.goonDex[viewedGoon].maxInc(5),
                            MainActivity.goonDex[viewedGoon].maxInc(6),MainActivity.goonDex[viewedGoon].maxInc(7),
                            MainActivity.goonDex[viewedGoon].maxInc(8),MainActivity.goonDex[viewedGoon].maxInc(9),
                            MainActivity.goonDex[viewedGoon].maxInc(10)};

                    int[][] avgStats = {MainActivity.goonDex[viewedGoon].avgInc(1),
                            MainActivity.goonDex[viewedGoon].avgInc(2), MainActivity.goonDex[viewedGoon].avgInc(3),
                            MainActivity.goonDex[viewedGoon].avgInc(4), MainActivity.goonDex[viewedGoon].avgInc(5),
                            MainActivity.goonDex[viewedGoon].avgInc(6), MainActivity.goonDex[viewedGoon].avgInc(7),
                            MainActivity.goonDex[viewedGoon].avgInc(8), MainActivity.goonDex[viewedGoon].avgInc(9),
                            MainActivity.goonDex[viewedGoon].avgInc(10)};

                    int[][] minStats = {MainActivity.goonDex[viewedGoon].minInc(1),
                            MainActivity.goonDex[viewedGoon].minInc(2), MainActivity.goonDex[viewedGoon].minInc(3),
                            MainActivity.goonDex[viewedGoon].minInc(4), MainActivity.goonDex[viewedGoon].minInc(5),
                            MainActivity.goonDex[viewedGoon].minInc(6), MainActivity.goonDex[viewedGoon].minInc(7),
                            MainActivity.goonDex[viewedGoon].minInc(8), MainActivity.goonDex[viewedGoon].minInc(9),
                            MainActivity.goonDex[viewedGoon].minInc(10)};

                    paint.setColor(Color.argb(255, 0,0,0));
                    canvas.drawRect(150, 200, 400, 400, paint);
                    canvas.drawRect(0, 1400, 1250, 1600, paint);

                    canvas.drawRect(50, 920, 130, 1020, paint);
                    canvas.drawRect(150, 920, 230, 1020, paint);
                    canvas.drawRect(250, 920, 330, 1020, paint);
                    canvas.drawRect(350, 920, 430, 1020, paint);
                    canvas.drawRect(450, 920, 530, 1020, paint);
                    canvas.drawRect(550, 920, 630, 1020, paint);
                    canvas.drawRect(650, 920, 730, 1020, paint);
                    canvas.drawRect(750, 920, 830, 1020, paint);
                    canvas.drawRect(850, 920, 930, 1020, paint);
                    canvas.drawRect(950, 920, 1030, 1020, paint);

                    paint.setTextSize(75);
                    paint.setColor(Color.argb(255, 255, 255, 255));
                    canvas.drawText(MainActivity.goonDex[viewedGoon].name, 150, 150, paint);

                    canvas.drawText("1", 70, 970, paint);
                    canvas.drawText("2", 170, 970, paint);
                    canvas.drawText("3", 270, 970, paint);
                    canvas.drawText("4", 370, 970, paint);
                    canvas.drawText("5", 470, 970, paint);
                    canvas.drawText("6", 570, 970, paint);
                    canvas.drawText("7", 670, 970, paint);
                    canvas.drawText("8", 770, 970, paint);
                    canvas.drawText("9", 870, 970, paint);
                    canvas.drawText("10", 950, 970, paint);

                    paint.setTextSize(50);
                    canvas.drawText("HEALTH: " + stats[0], 600, 150, paint);
                    canvas.drawText("C-ATT: " + stats[1], 600, 210, paint);
                    canvas.drawText("C-DEF: " + stats[2], 600, 270, paint);
                    canvas.drawText("F-ATT: " + stats[3], 600, 330, paint);
                    canvas.drawText("F-DEF: " + stats[4], 600, 390, paint);
                    canvas.drawText("PRIO: " + stats[5], 600, 450, paint);

                    canvas.drawText("--MAXIMUM-- ", 50, 560, paint);
                    canvas.drawText(" C-ATT: " + maxStats[lvl][0], 50, 620, paint);
                    canvas.drawText(" C-DEF: " + maxStats[lvl][1], 50, 680, paint);
                    canvas.drawText(" F-ATT: " + maxStats[lvl][2], 50, 740, paint);
                    canvas.drawText(" F-DEF: " + maxStats[lvl][3], 50, 800, paint);
                    canvas.drawText("  PRIO: " + maxStats[lvl][4], 50, 860, paint);

                    canvas.drawText("--AVERAGE-- ", 400, 560, paint);
                    canvas.drawText(" C-ATT: " + avgStats[lvl][0], 400, 620, paint);
                    canvas.drawText(" C-DEF: " + avgStats[lvl][1], 400, 680, paint);
                    canvas.drawText(" F-ATT: " + avgStats[lvl][2], 400, 740, paint);
                    canvas.drawText(" F-DEF: " + avgStats[lvl][3], 400, 800, paint);
                    canvas.drawText("  PRIO: " + avgStats[lvl][4], 400, 860, paint);

                    canvas.drawText("--MINIMUM-- ", 750, 560, paint);
                    canvas.drawText(" C-ATT: " + minStats[lvl][0], 750, 620, paint);
                    canvas.drawText(" C-DEF: " + minStats[lvl][1], 750, 680, paint);
                    canvas.drawText(" F-ATT: " + minStats[lvl][2], 750, 740, paint);
                    canvas.drawText(" F-DEF: " + minStats[lvl][3], 750, 800, paint);
                    canvas.drawText("  PRIO: " + minStats[lvl][4], 750, 860, paint);
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
                if(state == 1) {
                    if (y <= 500) {
                        state = 2;
                        viewedGoon = page;
                    } else if (y > 500 && y <= 1000) {
                        state = 2;
                        viewedGoon = page + 1;
                    } else if (y > 1000 && y <= 1500) {
                        state = 2;
                        viewedGoon = page + 2;
                    }
                }
                else if(state == 2){
                    if(y <= 1020 && y >= 920){
                        if(x >= 50 && x <= 130)
                            lvl = 0;
                        else if(x >= 150 && x <= 230)
                            lvl = 1;
                        else if(x >= 250 && x <= 330)
                            lvl = 2;
                        else if(x >= 350 && x <= 430)
                            lvl = 3;
                        else if(x >= 450 && x <= 530)
                            lvl = 4;
                        else if(x >= 550 && x <= 630)
                            lvl = 5;
                        else if(x >= 650 && x <= 730)
                            lvl = 6;
                        else if(x >= 750 && x <= 830)
                            lvl = 7;
                        else if(x >= 850 && x <= 930)
                            lvl = 8;
                        else if(x >= 950 && x <= 1030)
                            lvl = 9;
                    }
                    if (y >= 1400){
                        lvl = 0;
                        state = 1;
                    }
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
