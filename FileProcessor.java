package com.leo.rhyme;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lelily on 9/22/2017.
 */
public class FileProcessor {
	//private InputStream input;
	//private InputStreamReader isr;
    private FileReader fr;
    private BufferedReader reader;
    private String file_path;

    FileProcessor(String file_path) throws IOException{
        this.file_path = file_path;
        //this.input = this.getClass().getResourceAsStream(this.file_path);
        //this.isr = new InputStreamReader(input);
        //this.reader = new BufferedReader(isr);
        this.fr = new FileReader(this.file_path);
        this.reader = new BufferedReader(fr);       
    }

    public ArrayList<String> returnLines() throws IOException {
        String line;
        ArrayList<String> allLines = new ArrayList<>();
        while((line = reader.readLine()) != null){
            allLines.add(line);
        }
        reader.close();
        return allLines;
    }
}
