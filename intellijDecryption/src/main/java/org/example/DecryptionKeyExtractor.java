package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.*;
import java.io.BufferedReader;
import java.io.FileReader;
public class DecryptionKeyExtractor {
    //private ArrayList<String> encryptedText;

    //create an object that shows one key and value relationship
    //Mapping class with the encoded and the plain text and methods
    //each of the mapping objects would be one element of the ArrayList


    //private HashMap<String, String> decoderMap = new HashMap<>();
    private ArrayList<Mapping> mappings = new ArrayList<>(); //both the key and value (without the spaces and dashes)

    private File data;
    public DecryptionKeyExtractor(String fileName){ //ArrayList<String> encryptedText) throws Exception{
//        if(encryptedText!=null){
//            if(encryptedText.size()!=0) {
                //this.encryptedText = encryptedText;
        data = new File(fileName);
        extractKeyValues();
//            else{
//                throw new Exception("Please input valid encrypted text!");
//            }
//        }
//        else{
//            throw new Exception("Please input valid encrypted text!");
//        }
    }


//    public String decryptedText(){
//        StringBuilder stringBuilder = new StringBuilder();
//        for(int i=0; i< encryptedText.size(); i++){
//            String current = encryptedText.get(i);
//            //stringBuilder.append(decoderMap.get(current));
//            int j = 0;
//            for(j=0; j<mappings.size(); j++){
//                //System.out.println("progress");
//                if(mappings.get(j).getEncryptedText().equals(current)){
//                    break;
//                }
//            }
//            //int indexOfCurrent = j;
//            stringBuilder.append(mappings.get(j).getPlainText());
//        }
//        return stringBuilder.toString();
//    }

    public void extractKeyValues(){
        File data = this.data;
        try {
            FileReader fileReader = new FileReader(data);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null ){
                String[] lineArray = line.split("-");
                String key = lineArray[1].trim();
                String value = lineArray[0].trim();
                //decoderMap.put(key, value);
                Mapping mapping = new Mapping(value, key);
                //if(!mappings.contains(mapping)){
                    mappings.add(mapping);
                //}
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //
    public ArrayList<Mapping> getMappings() {
        return mappings;
    }
}
