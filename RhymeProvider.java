package com.leo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lelily on 9/22/2017.
 */

//Only have the rhyme bank and the vowel bank
public class RhymeProvider {
    private PhonicDictionary phonDict;
    private HashMap<String, ArrayList<String>> rhymeBank;  /*k: last sounds, v: list of rhymed words*/


    //Constructor, constructs the phonic bank from .txt
    RhymeProvider(String file_path){
        PhonicDictionary pd = new PhonicDictionary(file_path);
        pd.initialize();
        this.phonDict = pd;
    }


    /*
    Initializes the rhyme bank, a hash map mapping each word(key) to a word list it rhymes with (value)
     */
    public void initialize(){
        initializeRhymeBank(phonDict);
    }


    /*
    Initializes the rhyming word bank (rb)
    This bank will use the last sounds as a key and a list of rhyme words as values
    The bank will be populated with entries after looking in the phonic dictionary
    */
    private void initializeRhymeBank(PhonicDictionary phonDict){
        this.rhymeBank = new HashMap<>();
        phonDict.populateRhymeBank(rhymeBank);
    }


    /*
    Finds the last sound of the input word, and use that to find the rhyming words
    The word in the input is then removed from the list of words and the list is returned
    */
    public ArrayList<String> findRhyme(String word){
        word = word.toUpperCase();
        if(phonDict.doesNotContain(word)){
            throw new NullPointerException("Sorry, word is not in the word bank");
        }
        String lastSound = phonDict.idLastSound(word);
        ArrayList<String> rhymeWords = rhymeBank.get(lastSound);
        rhymeWords.remove(word);
        return rhymeWords;
    }
}
