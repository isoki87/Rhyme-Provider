package com.leo;

import java.util.HashMap;

/**
 * Created by Lelily on 9/22/2017.
 */
public class PhonDict {
    private HashMap<String, String> phenDict;
    private String file_path;


    PhonDict(String file_path){
       this.phenDict = new HashMap<>();
       this.file_path = file_path;
    }


    public int size(){
        return phenDict.size();
    }


    public void initialize(){
        PhonFileProcessor processor = new PhonFileProcessor();
        this.phenDict = processor.pullWords(file_path);
    }


    public HashMap<String, String> obtain(){
        return phenDict;
    }
}
