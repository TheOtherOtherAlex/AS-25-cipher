package org.example;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        double intitialTime = System.currentTimeMillis();

        System.out.println("Hello and welcome!");
        System.out.println('z'-'a');
        //EncryptedTextExtractor encryptedText = new EncryptedTextExtractor("src/main/resources/LongEncryptedText");

        //CreateAdditiveArray createAdditiveArray = new CreateAdditiveArray();
        //createAdditiveArray.createArray("src/main/resources/NewAdditiveBook");

        EncryptNewText encryptNewText = new EncryptNewText("src/main/resources/NewPlainText","src/main/resources/NewDecryptionKey");

        encryptNewText.extractAdditiveLayer("src/main/resources/NewAdditiveBook");
        encryptNewText.addAdditiveLayer();

        DecryptNewCipher decryptNewCipher = new DecryptNewCipher(encryptNewText.getMappings(), encryptNewText.getAdditiveBook(),encryptNewText.getFullyEncryptedText());
        System.out.println(decryptNewCipher.decryptCipher());






       //DecryptionKeyExtractor decrpytionText = new DecryptionKeyExtractor("src/main/resources/codebook_agnes.txt");


//       FastDecryptor fastDecryptor = new FastDecryptor(encryptedText.encryptedText,"src/main/resources/CodebookAgnesFieldEncrypt.txt");
//       System.out.println(fastDecryptor.decryptFile());


       //DecryptText decryptText = new DecryptText(encryptedText.encryptedText,decrpytionText.getMappings());
//       System.out.println(decryptText.decryptedText());

//
//       TextSorter textSorter = new TextSorter(decrpytionText);
//       System.out.println("go!");
//       textSorter.writeSortedToFile("src/main/resources/CodebookAgnesFieldDecrypt.txt",false);
//       textSorter.writeSortedToFile("src/main/resources/CodebookAgnesFieldEncrypt.txt",true);
//       System.out.println("done!");
    }
}
