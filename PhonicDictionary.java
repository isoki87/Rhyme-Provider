package com.leo.rhyme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Lelily on 9/22/2017.
 */

public class PhonicDictionary {
    private HashMap<String, String> wordBank;  /*k: phonics, v: word*/
    private HashMap<String, String> lastSoundsBank;  /*k: word, v: last sounds*/
    private HashSet<String> vowelBank; /*All possible vowel sounds*/
    private String file_path;


    //Constructor will initialize all variable fields
    PhonicDictionary(String file_path) {
        this.file_path = file_path;
        this.wordBank = new HashMap<>();
        this.lastSoundsBank = new HashMap<>();
        this.vowelBank = new HashSet<>();
    }


    //This will populate the 2 hash maps and the vowel bank hash set
    public void initialize() throws IOException {
        fillWordBank(file_path);
        fillVowelBank("../resources/RhymeBank/VowelBank.txt");
        fillLastSoundsBank(wordBank);
    }


    /*
    The word bank will be populated by words from file_path in the constructor
    The FileProcessor will return an arraylist of strings for each line
    The line will be split, and the full phonic will be stored as the key
    while the words will be stored as the values
     */
    private void fillWordBank(String p) throws IOException {
        FileProcessor fp = new FileProcessor(p);
        ArrayList<String> allLines = fp.returnLines();
        String line;
        for(int i = 0; i < allLines.size(); i++){
            line = allLines.get(i);
            String[] pair = line.split("  ");
            wordBank.put(pair[1], pair[0]);
        }
    }


    //Vowels will be populated with all available english vowel sounds
    private void fillVowelBank(String p) throws IOException{
        FileProcessor fp = new FileProcessor(p);
        ArrayList<String> allLines = fp.returnLines();
        for(int i = 0; i < allLines.size(); i++){
            vowelBank.add(allLines.get(i));
        }
    }


    /*
     Populate the Last Sounds Bank
     For every word in the full phonic word bank:
     - Find the last sound from the phonics
     - Add the word(k) with the last sound to the last sounds word bank
      */
    private void fillLastSoundsBank(HashMap<String, String> wordBank) {
        wordBank.forEach((String phonics, String word) -> {
            String lastSound = findLastSound(phonics);
            lastSoundsBank.put(word, lastSound);
        });
    }


    /*
    From a string representing the complete phonics of a word
    Split the phonics into individual phonic unit and store it in an array
    First find the last vowel in the word
    Then save that last vowel and any consonants that follows as a string
    with each phonic unit separated by a whitespace and concatenate them
     */
    private String findLastSound(String phonics) {
        String[] phonicsArr = phonics.split(" ");
        int lastVowel = findLastVowel(phonicsArr);
        String lastSound = putTogether(phonicsArr, lastVowel);
        return lastSound;
    }


    /*
    Given an array of phonic units, starting from the end, finds
    the last occurring vowel using the vowel bank hash set
     */
    private int findLastVowel(String[] arr) {
        int vowelInd = -1, last = arr.length - 1;
        for (int i = last; i >= 0; i--) {
            if ((vowelBank.contains(arr[i]))) {
                vowelInd = i;
                break;
            }
        }
        return vowelInd;
    }


    //Concatenates the array of phonic units into a single string
    private String putTogether(String[] arr, int index) {
        String lastSound = "";
        int end = arr.length - 1;
        for (int i = index; i < end; i++) {
            lastSound = lastSound.concat(arr[i]).concat(" ");
        }
        lastSound = lastSound.concat(arr[end]);
        return lastSound;
    }


    //If the Last Sounds Bank does not contain the word, returns true
    public boolean doesNotContain(String word) {
        return !(lastSoundsBank.containsKey(word));
    }


    //Finds the last sound for a word
    public String idLastSound(String word) {
        return lastSoundsBank.get(word);
    }


    /*
       Populates the hash map Rhyme Bank (rb)
       This method will go through every entry in the last sound word bank (lsb):
           -If the rb has the last sound in the lsb entry as a key, then add the word to
            the array list of words in the rb
           -If the rb does not have the last sound in the lsb entry as a key, then
            initialize a new array list of words, add the lsb's word into the list, and
            put the last sound and the list into rb
        */
    public void populateRhymeBank(HashMap<String, ArrayList<String>> rhymeBank){
        lastSoundsBank.forEach((String word, String lastSound) -> {
            if (rhymeBank.containsKey(lastSound)) {
                rhymeBank.get(lastSound).add(word);
            } else {
                ArrayList<String> rhymeWords = new ArrayList<>();
                rhymeWords.add(word);
                rhymeBank.put(lastSound, rhymeWords);
            }
        });
    }
}
