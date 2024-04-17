package org.example;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class TextSorter {
    public static ArrayList<Mapping> mappings;

    public TextSorter(DecryptionKeyExtractor keyExtractor) {
        mappings = keyExtractor.getMappings();
    }

//    private void sortByDecrypt() {
//        ArrayList<Mapping> mappings = keyExtractor.getMappings();
//        for (int i = 1; i < mappings.size(); i++) {
//            String currentText = mappings.get(i).getPlainText();
//            Mapping currentMap = mappings.get(i);
//            int j = i - 1;
//
//            while (j >= 0 && mappings.get(j).getPlainText().compareTo(currentText) > 0) {
//                mappings.set(j + 1, mappings.get(j));
//                j--;
//            }
//            mappings.set(j + 1, currentMap);
//        }
//    }
//
//    private void sortByEncrypt() {
//        ArrayList<Mapping> mappings = keyExtractor.getMappings();
//        for (int i = 1; i < mappings.size(); i++) {
//            String currentText = mappings.get(i).getEncryptedText();
//            Mapping currentMap = mappings.get(i);
//            int j = i - 1;
//
//            while (j >= 0 && mappings.get(j).getEncryptedText().compareTo(currentText) > 0) {
//                mappings.set(j + 1, mappings.get(j));
//                j--;
//            }
//            mappings.set(j + 1, currentMap);
//        }
//    }

    private void sort(boolean sortByEncrypt){
        if(sortByEncrypt == true){
            for (int i = 1; i < mappings.size(); i++) {
            String currentText = mappings.get(i).getEncryptedText();
            Mapping currentMap = mappings.get(i);
            int j = i - 1;

            while (j >= 0 && mappings.get(j).getEncryptedText().compareTo(currentText) > 0) {
                mappings.set(j + 1, mappings.get(j));
                j--;
            }
            mappings.set(j + 1, currentMap);
        }
        }else{
            for (int i = 1; i < mappings.size(); i++) {
                String currentText = mappings.get(i).getPlainText();
                Mapping currentMap = mappings.get(i);
                int j = i - 1;

                while (j >= 0 && mappings.get(j).getPlainText().compareTo(currentText) > 0) {
                    mappings.set(j + 1, mappings.get(j));
                    j--;
                }
                mappings.set(j + 1, currentMap);
            }
        }
    }

//    public void writeSortedDecryptToFile(String fileName) throws IOException {
//        //sortByDecrypt();
//        FileWriter fileWriter = null;
//        BufferedWriter bufferedWriter = null;
//        try {
//            File file = new File(fileName);
//            fileWriter = new FileWriter(file);
//            bufferedWriter = new BufferedWriter(fileWriter);
//            ArrayList<Mapping> mappings = keyExtractor.getMappings();
//            Collections.sort(mappings, new sortByPlainText());
//
//            for (int i = 0; i < mappings.size(); i++) {
//                String line = mappings.get(i).getPlainText() + " - " + mappings.get(i).getEncryptedText() + "\n";
//                //System.out.println(line);
//                bufferedWriter.write(line);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                bufferedWriter.close();
//                fileWriter.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    public void writeSortedEncryptToFile(String fileName) {
//        //sortByEncrypt();
//        FileWriter fileWriter = null;
//        BufferedWriter bufferedWriter = null;
//        try {
//            File file = new File(fileName);
//            fileWriter = new FileWriter(file);
//            bufferedWriter = new BufferedWriter(fileWriter);
//            ArrayList<Mapping> mappings = keyExtractor.getMappings();
//            Collections.sort(mappings, new sortByEncryptedText());
//            for (int i = 0; i < mappings.size(); i++) {
//                String line = mappings.get(i).getEncryptedText() + " - " + mappings.get(i).getPlainText() + "\n";
//                //System.out.println(line);
//                bufferedWriter.write(line);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                bufferedWriter.close();
//                fileWriter.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
    public void writeSortedToFile(String fileName, boolean sortByEncrypt){
        //sort(sortByEncrypt);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            if(sortByEncrypt == true) {
                Collections.sort(mappings, new sortByEncryptedText());
//                for (int i = 0; i < mappings.size(); i++) {
//                    String line = mappings.get(i).getEncryptedText() + " - " + mappings.get(i).getPlainText() + "\n";
//                    //System.out.println(line);
//                    bufferedWriter.write(line);
//                }
            }else{
                Collections.sort(mappings, new sortByPlainText());
//                for (int i = 0; i < mappings.size(); i++) {
//                    String line = mappings.get(i).getPlainText() + " - " + mappings.get(i).getEncryptedText() + "\n";
//                    //System.out.println(line);
//                    bufferedWriter.write(line);
//                }
            }

            for (int i = 0; i < mappings.size(); i++) {
                String line = mappings.get(i).getPlainText() + " - " + mappings.get(i).getEncryptedText() + "\n";
                //System.out.println(line);
                bufferedWriter.write(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
