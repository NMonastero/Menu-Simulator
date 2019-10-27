package com.example.menusimulator.ui;

import java.util.Random;

//still needs image functions
public class Information {
    //to keep track of all of this better later, these arrays might work better in a matrix
    final int bhp, batt, bdef, bsatt, bsdef, bprio;
    final long atti, defi, satti, sdefi, prioi;
    final int mhp, matt, mdef, msatt, msdef, mprio;
    int hp, att, def, satt, sdef, prio;
    int[] moves = new int[3];
    int item;
    int type1;
    int type2;
    public String name;
    //image backSprite, frontSprite, menuSprite;

    //this can probably condensed into an array later but for now it's easier to visualize spread out
    public Information(String n, int h, int a, int d, int sa, int sd, int p,
                long ai, long di, long sai, long sdi, long pi,
                int mh, int ma, int md, int msa, int msd, int mp,
                int t1, int t2){
        name = n;

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

        type1 = t1;
        type2 = t2;
    }

    //these increases should be determined in class, not by a method call
    //additionally, it might make the game more dynamic to make these changes dynamic like fire emblem lvlups
    public void statInc(){
        //hp probably won't change upon level up so it shouldn't change here
        //new formula = ########## / 10^(Math.random() * 9) % 10
        att += atti / (Math.pow(10, (int)(Math.random() * 8))) % 10;
        def += defi / (Math.pow(10, (int)(Math.random() * 8))) % 10;
        satt += satti / (Math.pow(10, (int)(Math.random() * 8))) % 10;
        sdef += sdefi / (Math.pow(10, (int)(Math.random() * 8))) % 10;
        prio += prioi / (Math.pow(10, (int)(Math.random() * 8))) % 10;
    }

    public int[] maxInc(int lvl){
        //only size 5 because hp wont increase from lvlup
        int a[] = {batt, bdef, bsatt, bsdef, bprio};
        for(int i = 1; i < lvl; i++) {
            a[0] += atti/100000000%10;
            a[1] += defi/100000000%10;
            a[2] += satti/100000000%10;
            a[3] += sdefi/100000000%10;
            a[4] += prioi/100000000%10;
        }

        return a;
    }

    public int[] minInc(int lvl){
        int a[] = {batt, bdef, bsatt, bsdef, bprio};
        for(int i = 1; i < lvl; i++) {
            a[0] += atti%10;
            a[1] += defi%10;
            a[2] += satti%10;
            a[3] += sdefi%10;
            a[4] += prioi%10;
        }

        return a;
    }

    public int[] avgInc(int lvl){
        double a[] = {batt, bdef, bsatt, bsdef, bprio};
        for(int i = 1; i < lvl; i++) {
            a[0] += atti/(Math.pow(10, i))%10;
            a[1] += defi/( Math.pow(10, i))%10;
            a[2] += satti/(Math.pow(10, i))%10;
            a[3] += sdefi/(Math.pow(10, i))%10;
            a[4] += prioi/(Math.pow(10, i))%10;
        }
        int b[] = {(int) a[0], (int) a[1], (int) a[2], (int) a[3], (int) a[4]};

        return b;
    }

    public int[] getBaseStats(){
        int[] stats = new int[6];
        stats[0] = bhp;
        stats[1] = batt;
        stats[2] = bdef;
        stats[3] = bsatt;
        stats[4] = bsdef;
        stats[5] = bprio;

        return stats;
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

    public void setName(String newName) {
        name = newName;
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
