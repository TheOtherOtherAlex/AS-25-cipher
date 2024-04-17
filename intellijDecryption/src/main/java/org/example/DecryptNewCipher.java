package org.example;

import java.util.ArrayList;

public class DecryptNewCipher {

    String cipherToDecrypt = "";
    ArrayList<Mapping> decryptionKey = new ArrayList<>();
    String[][] additiveLayer = new String[0][0];

    public DecryptNewCipher(ArrayList<Mapping> decryptionKey,String[][]additiveLayer, String cipherToDecrypt){
        this.decryptionKey = decryptionKey;
        this.additiveLayer = additiveLayer;
        this.cipherToDecrypt = cipherToDecrypt.toLowerCase();

    }

    public String decryptCipher(){
        String decryptedText = "";
        int newChar = 0;
        int startRow = 0;
        int startColumn = cipherToDecrypt.charAt(4)-'a'+1;


        for(int i = 0; i<4; i++){
            if(cipherToDecrypt.charAt(i) != 'a') {
                startRow += cipherToDecrypt.charAt(i) - 'a' + 1;
            }
        }

        String[] actualCipher = cipherToDecrypt.substring(6).split(" ");

        for (int i = 0; i<actualCipher.length; i++){
            for (int j = 0; j<actualCipher[i].length(); j++){
                newChar = (actualCipher[i].toLowerCase().charAt(j)-'a') - (additiveLayer[startRow][startColumn].toLowerCase().charAt(j)-'a');
                while (newChar<0){
                    newChar+=25;
                }
                decryptedText += (char) ('a'+newChar);
            }
            decryptedText += " ";
            startColumn++;
            if(startColumn >25){
                startColumn = 0;
                startRow ++;
                if (startRow >104){
                    startRow = 0;
                }
            }
        }
        String[] unAddedEncryptedText = decryptedText.split(" ");
        String fullyDecryptedText = "";
        for(int i = 0; i<unAddedEncryptedText.length;i++) {
            for (int j = 0; j < decryptionKey.size(); j++) {
                if(decryptionKey.get(j).getPlainText().equals(unAddedEncryptedText[i].trim().toUpperCase())){
                    fullyDecryptedText+=decryptionKey.get(j).getEncryptedText();
                }
            }
        }


        return fullyDecryptedText;
    }



}
