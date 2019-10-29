package com.example.menusimulator.ui;

import android.graphics.Color;

import com.example.menusimulator.MainActivity;

public class types{
    int color;
    String name;
    int[] eff, ineff;
    int[] neutE = {};
    int[] neutI ={};
    int[] plantE = {3, 6};
    int[] plantI = {1, 2, 11};
    int[] heatE = {1, 5, 8, 12};
    int[] heatI = {2, 3, 6, 11};
    int[] waterE = {2, 6, 8, 13};
    int[] waterI = {1, 3, 5, 7, 11};

    public types(int c, String n, int[] a, int[] b){
        color = c;
        name = n;
        int count = 0;
        eff = a;
        ineff = b;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
