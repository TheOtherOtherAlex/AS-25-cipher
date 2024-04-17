package org.example;

import java.io.*;
import java.util.ArrayList;

public class EncryptNewText {


    private String plainText = "";
    private String encryptedTextNoAdd = "";
    private ArrayList<Mapping> mappings = new ArrayList<>();
    private String[][] additiveBook = new String[104][26];

    private int startRow = (int) (Math.random()*103);
    private int startColumn = (int) (Math.random()*25);

    private String encryptedTextWithAdd = "";

    public EncryptNewText(String plainTextFileName, String keyFileName){

        extractPlainText(plainTextFileName);
        extractKey(keyFileName);

    }
    public void extractPlainText(String fileName){

        File data = new File(fileName);

        try {
            FileReader fileReader = new FileReader(data);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            if(line != null) {
                plainText = line.replaceAll("\\s","");
            }

        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void extractKey(String fileName){
        DecryptionKeyExtractor decryptionKeyExtractor = new DecryptionKeyExtractor(fileName);
        mappings = decryptionKeyExtractor.getMappings();
        for(int i = 0; i<plainText.length(); i++){
             encryptedTextNoAdd += mappings.get(plainText.charAt(i)-'a').getPlainText() + " ";
        }
        System.out.println(encryptedTextNoAdd);
    }

    public void extractAdditiveLayer(String fileName){
        File data = new File(fileName);

        try {
            FileReader fileReader = new FileReader(data);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //String line = bufferedReader.readLine();
            for(int i = 0; i<104; i++) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    additiveBook[i] = line.split(" ");
                }
            }

        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAdditiveLayer(){
        System.out.println(startRow+"_");
        System.out.println(startColumn+"/");
        int savedStartRow = startRow;
        int savedStartColumn = startColumn;

        int newChar = 0;

        while(startRow > 26){
            encryptedTextWithAdd += "Z";
            startRow -= 26;
        }
        encryptedTextWithAdd += (char)('a'+startRow-1);
        while(encryptedTextWithAdd.length() <4){
            encryptedTextWithAdd += "a";
        }
        encryptedTextWithAdd += (char)('a'+startColumn-1);
        encryptedTextWithAdd = encryptedTextWithAdd.toUpperCase() + " ";
        System.out.println(encryptedTextWithAdd);



        String[] encryptedTextNoAddArray = encryptedTextNoAdd.split(" ");
        for (int i = 0; i<encryptedTextNoAddArray.length; i++){
            for (int j = 0; j<encryptedTextNoAddArray[i].length(); j++){
                newChar = (encryptedTextNoAddArray[i].toLowerCase().charAt(j)-'a') + (additiveBook[savedStartRow][savedStartColumn].toLowerCase().charAt(j)-'a');
                while (newChar>25){
                    newChar-=25;
                }
                encryptedTextWithAdd += (char) ('a'+newChar);
            }
            encryptedTextWithAdd += " ";
            savedStartColumn++;
            if(savedStartColumn >25){
                savedStartColumn = 0;
                savedStartRow ++;
                if (savedStartRow > 104){
                    savedStartRow = 0;
                }
            }
        }

//        String tempString = "";
//
//        while(savedStartRow > 26){
//            tempString += "Z";
//            savedStartRow -= 26;
//        }
//        tempString += (char)('a'+savedStartRow-1);
//        while(tempString.length() <4){
//            tempString += "a";
//        }
//        tempString += (char)('a'+savedStartColumn-1);
//        tempString = tempString.toUpperCase();
//        encryptedTextWithAdd += tempString;
        encryptedTextWithAdd = encryptedTextWithAdd.toUpperCase();
        System.out.println(encryptedTextWithAdd);
    }


    public ArrayList<Mapping> getMappings(){
        return mappings;
    }
    public String[][] getAdditiveBook(){
        return additiveBook;
    }

    public String getFullyEncryptedText(){
        return encryptedTextWithAdd;
    }
}
