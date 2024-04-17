package org.example;

import java.util.ArrayList;
import java.io.*;
public class EncryptedTextExtractor {

    public ArrayList<String> encryptedText = new ArrayList<>();

    public EncryptedTextExtractor(String fileName){
        File data = new File(fileName);


        try {
            FileReader fileReader = new FileReader(data);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            if(line != null) {
                String[] lineArray = line.split(" ");
                for (int i = 0; i < lineArray.length; i++) {
                    encryptedText.add(lineArray[i].trim());
                }
            }

        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

