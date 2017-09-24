package com.leo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Lelily on 9/22/2017.
 */
public class RhymeProvider {
    private HashMap<String, String> wordBank;  //k: phonics, v: word
    private HashMap<String, String> lastSoundsBank;  //k: word, v: last sounds
    private HashMap<String, ArrayList<String>> rhymeBank;  //k: last sounds, v: list of rhymed words
    private static HashSet<String> vowelBank;  //vowel sounds


    //Constructor, constructs the phonic bank from .txt
    RhymeProvider(String file_path){
        PhonDict pd = new PhonDict(file_path);
        pd.initialize();
        this.wordBank = pd.obtain();
    }


    /*
    Initializes vowel bank, a hash set of all vowel pronunciations
    Initializes last sounds bank, a hash map mapping each word(key) to its last sound(value)
    Initializes the rhyme bank, a hash map mapping each word(key)
    to a word list it rhymes with (value)
     */
    public void initialize(){
        initializeVowels();
        initializeLastSounds(wordBank);
        initializeRhymeBank();
    }


    //Reads the .txt file containing all vowels and initializes the hash set of vowels
    private void initializeVowels(){
        this.vowelBank = new HashSet<>();
        PhonFileProcessor fp = new PhonFileProcessor();
        vowelBank = fp.pullVowels("VowelBank.txt");
    }


    /*
    Initializes the last sound word bank
    For every word in the full phonic word bank:
    - Find the last sound from the phonics
    - Add the word(k) with the last sound to the last sounds word bank
     */
    private void initializeLastSounds(HashMap<String, String> wordBank){
        this.lastSoundsBank = new HashMap<>();
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
    with each phonic unit separated by a whitespace
     */
    private String findLastSound(String phonics){
        String[] phonicsArr = phonics.split(" ");
        int lastVowel = findLastVowel(phonicsArr);
        String lastSound = putTogether(phonicsArr, lastVowel);
        return lastSound;
    }


    /*Given an array of phonic units, starting from the end, finds
    the last occurring vowel using the vowel bank hash set
     */
    private int findLastVowel(String[] arr){
        int vowelInd = -1, last = arr.length - 1;
        for(int i = last; i >= 0; i--){
            if((vowelBank.contains(arr[i]))){
                vowelInd = i;
                break;
            }
        }
        return vowelInd;
    }


    //Concatenates the array of phonic units into a single string
    private String putTogether(String[] arr, int index){
        String lastSound = "";
        int end = arr.length - 1;
        for(int i = index; i < end; i++){
            lastSound = lastSound.concat(arr[i]).concat(" ");
        }
        lastSound = lastSound.concat(arr[end]);
        return lastSound;
    }


    /*
    Initializes the rhyming word bank (rb)
    This bank will use the last sounds as a key and a list of rhyme words as values
    This method will go through every entry in the last sound word bank (lsb):
        -If the rb has the last sound in the lsb entry as a key, then add the word to
         the array list of words in the rb
        -If the rb does not have the last sound in the lsb entry as a key, then
         initialize a new array list of words, add the lsb's word into the list, and
         put the last sound and the list into rb
    */
    private void initializeRhymeBank(){
        this.rhymeBank = new HashMap<>();
        lastSoundsBank.forEach((String word, String lastSound) -> {
            if(rhymeBank.containsKey(lastSound)){
                rhymeBank.get(lastSound).add(word);
            } else {
                ArrayList<String> rhymeWords = new ArrayList<>();
                rhymeWords.add(word);
                rhymeBank.put(lastSound, rhymeWords);
            }
        });
    }


    /*
    Finds the last sound of the input word, and use that to find the rhyming words
    The word in the input is then removed from the list of words and the list is returned
    */
    public ArrayList<String> findRhyme(String word){
        word = word.toUpperCase();
        if(lastSoundsBank.get(word) == null){
            throw new NullPointerException("Sorry, word is not in the word bank");
        }
        String lastSound = lastSoundsBank.get(word);
        ArrayList<String> rhymeWords = rhymeBank.get(lastSound);
        rhymeWords.remove(word);
        return rhymeWords;
    }


    public int vbsize(){
        return vowelBank.size();
    }

    public int rbsize(){
        return rhymeBank.size();
    }

    public int lssize(){
        return lastSoundsBank.size();
    }

    public int wbsize(){
        return wordBank.size();
    }

}
