package com.example.menusimulator.ui;

public class types {
    type types []= new type[14];
    int[] neutE = {};
    int[] neutI ={};
    int[] plantE = {3, 6};
    int[] plantI = {1, 2, 11};
    int[] heatE = {1, 5, 8, 12};
    int[] heatI = {2, 3, 6, 11};
    int[] waterE = {2, 6, 8, 13};
    int[] waterI = {1, 3, 5, 7, 11};

    public types(){
        types[0] = new type(0, "Neutral", neutE, neutI);
        types[1] = new type(1, "Plant", plantE, plantI);
        types[2] = new type(2, "Heat", heatE, heatI);
        types[3] = new type(3, "Water", waterE, waterI);
    }

    public class type{
        int color;
        String name;
        int[] eff, ineff;
        public type(int c, String n, int[] a, int[] b){
            color = c;
            name = n;
            int count = 0;
            eff = a;
            ineff = b;
        }
    }
}
