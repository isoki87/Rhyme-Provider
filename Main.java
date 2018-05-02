package com.leo;

import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    RhymeProvider rp = new RhymeProvider("TestSounds.txt");
    rp.initialize();
    ArrayList<String> test = rp.findRhyme("apple");
    System.out.println(test.size());
    System.out.println(test.get(0));
  }

}
