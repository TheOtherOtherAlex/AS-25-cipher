package org.example;

import java.io.*;

public class CreateAdditiveArray {

    private int rows = 104;
    private int columns = 26;
    private String additiveText = "";
    public  CreateAdditiveArray(){}

    public void createArray(String fileName){
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            String randomTextColumn = "";


            for(int i = 0; i<rows; i++) {
                randomTextColumn = "";
                for (int j = 0; j < columns; j++) {
                    additiveText = "";
                    for (int k = 0; k < 5; k++) {
                        additiveText += (char) ('a'+ (int)(Math.random()*26 + 1)-1);
                    }
                    additiveText = additiveText.toUpperCase() + " ";
                    randomTextColumn += additiveText;
                }
                bufferedWriter.write(randomTextColumn+"\n");
            }

        }catch (IOException e) {
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
