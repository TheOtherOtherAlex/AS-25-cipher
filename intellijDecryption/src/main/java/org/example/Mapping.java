package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class Mapping {
    //value
    public String plainText;
    //key
    public String encryptedText;

    public Mapping(String plainText, String encryptedText){
        this.plainText = plainText;
        this.encryptedText = encryptedText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }
    public void setEncryptedText(String encryptedText) {
        this.encryptedText = encryptedText;
    }

    //makes sure we don't have multiple overlapping mappings
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mapping mapping)) return false;
        return Objects.equals(encryptedText, mapping.encryptedText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(encryptedText);
    }

    public String getPlainText() {
        return plainText;
    }

    public String getEncryptedText() {
        return encryptedText;
    }





}

class sortByPlainText implements Comparator<Mapping>{
    public int compare(Mapping a, Mapping b){
        return a.getPlainText().compareTo(b.getPlainText());
    }
}
class sortByEncryptedText implements Comparator<Mapping>{
    public int compare(Mapping a, Mapping b){
        return a.getEncryptedText().compareTo(b.getEncryptedText());
    }
}
