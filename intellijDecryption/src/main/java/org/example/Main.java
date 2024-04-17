package org.example;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        double intitialTime = System.currentTimeMillis();

        System.out.println("Hello and welcome!");

        //CreateAdditiveArray createAdditiveArray = new CreateAdditiveArray();
        //createAdditiveArray.createArray("src/main/resources/NewAdditiveBook");

        EncryptNewText encryptNewText = new EncryptNewText("src/main/resources/NewPlainText","src/main/resources/NewDecryptionKey");

        encryptNewText.extractAdditiveLayer("src/main/resources/NewAdditiveBook");
        encryptNewText.addAdditiveLayer();

        DecryptNewCipher decryptNewCipher = new DecryptNewCipher(encryptNewText.getMappings(), encryptNewText.getAdditiveBook(),encryptNewText.getFullyEncryptedText());
        System.out.println(decryptNewCipher.decryptCipher());

    }
}
