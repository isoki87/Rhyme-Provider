package com.leo;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        RhymeProvider rp = new RhymeProvider("TestSounds.txt");
        rp.initialize();
        ArrayList<String> fats = rp.findRhyme("apple");
        System.out.println(fats.size());
        System.out.println(fats.get(0));
    }
}
