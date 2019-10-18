package com.example.menusimulator.ui;

public class Information {
    final int bhp, batt, bdef, bsatt, bsdef, bprio;
    int hp, att, def, satt, sdef, prio;

    Information(int h, int a, int d, int sa, int sd, int p){
        bhp = h;
        batt = a;
        bdef = d;
        bsatt = sa;
        bsdef = sd;
        bprio = p;
    }

    //these increases should be determined in class, not by a method call
    //additionally, it might make the game more dynamic to make these changes dynamic like fire emblem lvlups
    public void statInc(int h, int a, int d, int sa, int sd, int p){
        hp += h;
        att += a;
        def += d;
        satt += sa;
        sdef += sd;
        prio += p;
    }

    public int[] getStats(){
        int[] stats = new int[6];
        stats[0] = hp;
        stats[1] = att;
        stats[2] = def;
        stats[3] = satt;
        stats[4] = sdef;
        stats[5] = prio;

        return stats;
    }
}
