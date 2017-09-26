package com.leo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Lelily on 9/22/2017.
 */
public class FileProcessor {
    public HashSet<String> pullVowels(String file_path){
        HashSet<String> hashset = new HashSet<>();
        try{
            FileReader fr = new FileReader(file_path);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while((line = reader.readLine()) != null){
                hashset.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e){
            System.out.println("error");
        }
        return hashset;
    }


    public HashMap<String, String> pullWords(String file_path){
        HashMap<String, String> hashmap = new HashMap<>();
        try {
            FileReader fr = new FileReader(file_path);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while((line = reader.readLine()) != null){
                String[] pair = line.split("  ");
                hashmap.put(pair[1], pair[0]);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e){
            System.out.println("error");
        }
        return hashmap;
    }
}
