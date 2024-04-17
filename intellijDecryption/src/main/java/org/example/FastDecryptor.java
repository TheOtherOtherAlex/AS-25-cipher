package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FastDecryptor {
    private ArrayList<Mapping> sortedDecryptionKeyText = new ArrayList<>(); //an array of all mappings.
    private ArrayList<String> encryptedTextArray; //use the encrypted text extractor to fill this one out
    private String sortedDecryptionKeyFile; //set this one to fieldEncrypt

    //NOTE: It is very easy to get sortedDecryptionKeyText and sortedDecryptionKeyFile confused. PLEASE DO NOT.


    /**
     * paramaterized constructor for FastDecryptor class
     * @param encryptedTextArray an arrayList of encrypted text
     * @param sortedDecryptionKeyFile the name of the file where the already sorted decryption key is stored. this file should be "CodebookAgnesFieldEncrypt.txt"
     *
     * */
    public FastDecryptor(ArrayList<String> encryptedTextArray, String sortedDecryptionKeyFile){
        this.encryptedTextArray = encryptedTextArray;
        this.sortedDecryptionKeyFile = sortedDecryptionKeyFile;

        DecryptionKeyExtractor textExtractor = new DecryptionKeyExtractor(sortedDecryptionKeyFile);

        sortedDecryptionKeyText = textExtractor.getMappings();

        //extractFile(); //see extract file below.
    }

/**
 * method used for extracting the text out of the file given by sortDecryptionKeyFile.
 * Specifically, CodebookAgnesFieldEncrypt.txt.
 *
 **/
    private void extractFile(){
        try {
            System.out.println("started reading to file");

            //standard filereader + bufferedreader arrangement
            FileReader fileReader = new FileReader(sortedDecryptionKeyFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //line is the variable which will temporarily contain the lines from bufferedReader.
            String line = null;
            while ((line = bufferedReader.readLine()) != null && line.length() > 0 ){ //while a line is still present, do...
                String[] lineArray = line.split("-"); //splits the line into 2 based on the - in the middle.


                //removes spaces on both lineArrays.
                String value = lineArray[0].trim();
                String key = lineArray[1].trim();

                //sets the values of plaintext and encryptedText, as well as storing mapping in an arrayList.
                //noatbly, mapping functions off of the plaintext field being on the left, while the encryptedText field is on the right.
                Mapping mapping = new Mapping(key, value);
                sortedDecryptionKeyText.add(mapping);
            }

        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *method that rapidly decrypts encrypted files via the use of a binary search algorithm.
     * @return returns the decrypted text.
     **/
    public String decryptFile() {
        double intitialTime = System.currentTimeMillis();
        System.out.println("started decrypting");

        if (sortedDecryptionKeyText.size() != 0) {
            //stores the decrypted text.
            String sortedText = "";

            //a for loop that loops through the entierty of the arraylist containing the encrypted text
            for (int i = 0; i < encryptedTextArray.size(); i++) {

                int start = 0; //the index at which the sort algorithim starts
                int end = sortedDecryptionKeyText.size(); //the index at which the sort algorithim ends
                int middle = sortedDecryptionKeyText.size() / 2; //the middle index of the arraylist.

                //compares the i'th index of encrypted text against the middle index of decrypted text.
                //this comparison is to determine wether or not the encrypted text is numerically greater than,
                //or less than the middle decryption key index.
                int textComparison = encryptedTextArray.get(i).compareTo(sortedDecryptionKeyText.get(middle).getEncryptedText());

                while (textComparison != 0) {//while the textComparison is not equal to itself, do...

                    //if the encrypted text's value is more than the decryption key's middle index...
                    if (textComparison > 0) {

                        //the start of the index becomes the middle, as everything less than the middle is useless.
                        start = middle + 1;

                        //if the encrypted text's value is more than the decryption key's middle index...
                    } else if (textComparison < 0) {

                        //the end of the index becomes the middle, as everything more than the middle is useless.
                        end = middle - 1;
                    }

                    //resets the middle to the actual middle of both the start and end bounds.
                    middle = (start + end) / 2;

                    //System.out.println(sortedDecryptionKeyText.get(middle).getEncryptedText());
                    //resets the value of text comparison.
                    textComparison = encryptedTextArray.get(i).compareTo(sortedDecryptionKeyText.get(middle).getEncryptedText());
                }

                //adds on the value of a chunk of decrypted text onto the return value
                sortedText += sortedDecryptionKeyText.get(middle).getPlainText();
            }

            //returns sorted text
            System.out.println("final time: " + (System.currentTimeMillis()-intitialTime));
            return sortedText;
        }else{
            System.out.println("final time: " + (System.currentTimeMillis()-intitialTime));
            return "";
        }
    }

}
