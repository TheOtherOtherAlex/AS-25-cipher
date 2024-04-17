package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DecryptText {

    private static ArrayList<String> encryptedText;
    private static ArrayList<Mapping> mapping;

    public DecryptText(ArrayList<String> encryptedText, ArrayList<Mapping> mapping) {
        this.encryptedText = encryptedText;
        this.mapping = mapping;
    }


    public String decryptedText(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i< encryptedText.size(); i++){
            String current = encryptedText.get(i);
            //stringBuilder.append(decoderMap.get(current));
            int j = 0;
            for(j=0; j<mapping.size(); j++){
                //System.out.println("progress");
                if(mapping.get(j).getEncryptedText().equals(current)){
                    break;
                }
            }
            //int indexOfCurrent = j;
            stringBuilder.append(mapping.get(j).getPlainText());
        }
        return stringBuilder.toString();
    }
}
