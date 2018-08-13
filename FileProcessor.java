package com.leo.rhyme;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lelily on 9/22/2017.
 */
public class FileProcessor {
    private FileReader fr;
    private BufferedReader reader;
    private String file_path;
    private String vowelPath;
    private String wordPath;
    private static ArrayList<String> vowelBank;
    private static ArrayList<String> wordBank;

    FileProcessor(String file_path) throws IOException{
    	this.wordPath = "../resources/RhymeBank/WordBank.txt";
    	this.vowelPath = "../resources/RhymeBank/VowelBank.txt";
      this.file_path = file_path;
    }

    public ArrayList<String> returnLines() throws IOException {
        String line;
        if(file_path.equals(wordPath) && !(wordBank == null)){
        	return wordBank;
        } else if (file_path.equals(vowelBank) && !(vowelBank == null)){
        	return vowelBank;
        } else {
	        this.fr = new FileReader(this.file_path);
	        this.reader = new BufferedReader(fr);
	        ArrayList<String> allLines = new ArrayList<>();
	        while((line = reader.readLine()) != null){
	            allLines.add(line);
	        }
	        reader.close();
	        fr.close();
	        if(file_path.equals(wordPath)){
	        	FileProcessor.wordBank = allLines;
	        }
	        if(file_path.equals(vowelPath)){
	        	FileProcessor.vowelBank = allLines;
	        }
	        return allLines;
        }
    }
}
