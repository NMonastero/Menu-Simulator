package com.example.menusimulator.ui;

import java.util.Random;

public class Information {
    //to keep track of all of this better later, these arrays might work better in a matrix
    final int bhp, batt, bdef, bsatt, bsdef, bprio;
    final int[] atti, defi, satti, sdefi, prioi;
    final int mhp, matt, mdef, msatt, msdef, mprio;
    int hp, att, def, satt, sdef, prio;
    int[] moves = new int[3];
    int item;

    //this can probably condensed into an array later but for now it's easier to visualize spread out
    Information(int h, int a, int d, int sa, int sd, int p,
                int[] ai, int di[], int sai[], int sdi[], int pi[],
                int mh, int ma, int md, int msa, int msd, int mp){
        bhp = h;
        batt = a;
        bdef = d;
        bsatt = sa;
        bsdef = sd;
        bprio = p;

        atti = ai;
        defi = di;
        satti = sai;
        sdefi = sdi;
        prioi = pi;

        mhp = mh;
        matt = ma;
        mdef = md;
        msatt = msa;
        msdef = msd;
        mprio = mp;
    }

    //these increases should be determined in class, not by a method call
    //additionally, it might make the game more dynamic to make these changes dynamic like fire emblem lvlups
    public void statInc(){
        //hp probably won't change upon level up so it shouldn't change here
        att += atti[((int)(Math.random() * 9))];
        def += defi[((int)(Math.random() * 9))];
        satt += satti[((int)(Math.random() * 9))];
        sdef += sdefi[((int)(Math.random() * 9))];
        prio += prioi[((int)(Math.random() * 9))];
    }

    public int[] maxInc(int lvl){
        //only size 5 because hp wont increase from lvlup
        int a[] = new int[5];
        for(int i = 0; i <= lvl; i++) {
            a[0] += atti[9];
            a[1] += defi[9];
            a[2] += satti[9];
            a[3] += sdefi[9];
            a[4] += prioi[9];
        }

        return a;
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

    public int[] metaStats() {
        int[] metaStats = getStats();
        metaStats[0] += mhp;
        metaStats[1] += matt;
        metaStats[2] += mdef;
        metaStats[3] += msatt;
        metaStats[4] += msdef;
        metaStats[5] += mprio;

        return metaStats;
    }

    //this might actually need to be a constructor
    public int[][] learnableMoves(){
        //numbers are temp because list of moves is not finalized
        //max # of moves at a time == 3?
        //there also needs to be a system to facilitate a move learn tree
        int[][] moves = new int[9][3];
        moves[0][0] = 1;
        moves[0][1] = 2;
        moves[1][0] = 3;
        moves[1][1] = 4;

        return moves;
    }

    public void setMove(int replacedMove, int newMove){
        moves[replacedMove] = newMove;
    }

    public int[] getMoves(){
        return moves;
    }

    public void setItem(int i){
        item = i;
    }

    public int getItem(){
        return item;
    }

    //this should send information about the base goon to the next in line
    //including: stats after metamorphosis, current moves, hold item, etc. (probably all stored as ints)
    //this might mean making a class for all items and item lookups so they can be assigned ints
    public int[] meta(){
        int[] a = new int[10];
        int[] s = metaStats();

        //a[0-5] are stats
        for(int i = 0; i < 6; i++){
            a[i] = s[i];
        }
        for(int i = 6; i < 9; i++){
            a[i] = moves[i-6];
        }
        a[9] = getItem();
        return a;
    }
}
