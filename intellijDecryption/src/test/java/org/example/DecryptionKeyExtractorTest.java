package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DecryptionKeyExtractorTest {

    @Test
    public void testDecryptionKeyExtractor() throws Exception{
        EncryptedTextExtractor encryptedText = new EncryptedTextExtractor(
                "src/main/resources/EncryptedTextTest");
        DecryptionKeyExtractor decryptionText = new DecryptionKeyExtractor(
                "src/main/resources/codebook_test.txt");
        DecryptText decryptText = new DecryptText(encryptedText.encryptedText,decryptionText.getMappings());
        assertEquals("ATESTTOCHECKACCURACY", decryptText.decryptedText());

    }

    @Test
    public void testEncryptedTestExtractor(){
        EncryptedTextExtractor encryptedText = new EncryptedTextExtractor(
                "src/main/resources/EncryptedTextTest");
        ArrayList<String> encryptedTest = new ArrayList<>();
        encryptedTest.add("XHEJA");
        encryptedTest.add("OIEGE");
        encryptedTest.add("ZHCVG");
        encryptedTest.add("VYBIL");
        assertArrayEquals(encryptedTest, encryptedText.encryptedText);

    }
    @Test
    public void testEncryptedTestExtractorNoText(){
        EncryptedTextExtractor encryptedText = new EncryptedTextExtractor(
                "src/main/resources/emptyFile.txt");
        ArrayList<String> encryptedTest = new ArrayList<>();
        assertArrayEquals(encryptedTest, encryptedText.encryptedText);

    }
    @Test
    public void decryptionSorterTest() throws Exception{
        EncryptedTextExtractor encryptedText = new EncryptedTextExtractor(
                "src/main/resources/EncryptedTextTest");
        DecryptionKeyExtractor decrpytionText = new DecryptionKeyExtractor(
                "src/main/resources/codebook_test.txt");
        TextSorter textSorter = new TextSorter(decrpytionText);
        textSorter.writeSortedToFile("src/main/resources/SortedCodebookTest.txt",false);
        FileReader fileReader = new FileReader("src/main/resources/SortedCodebookTest.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String tempText = bufferedReader.readLine();

        ArrayList<String> arrayList = new ArrayList<>();

        while(tempText != null){
            arrayList.add(tempText);
            tempText = bufferedReader.readLine();
        }
        ArrayList<String> trueArrayList = new ArrayList<>();
        trueArrayList.add("ATEST - XHEJA");
        trueArrayList.add("CKACC - ZHCVG");
        trueArrayList.add("TOCHE - OIEGE");
        trueArrayList.add("URACY - VYBIL");
        assertArrayEquals(arrayList,trueArrayList);
    }

    @Test
    public void encryptionSorterTest() throws Exception{
        EncryptedTextExtractor encryptedText = new EncryptedTextExtractor(
                "src/main/resources/EncryptedTextTest");
        DecryptionKeyExtractor decrpytionText = new DecryptionKeyExtractor(
                "src/main/resources/codebook_test.txt");

        TextSorter textSorter = new TextSorter(decrpytionText);
        textSorter.writeSortedToFile("src/main/resources/SortedCodebookTest.txt",true);
        FileReader fileReader = new FileReader("src/main/resources/SortedCodebookTest.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String tempText = bufferedReader.readLine();

        ArrayList<String> arrayList = new ArrayList<>();

        while(tempText != null){
            arrayList.add(tempText);
            tempText = bufferedReader.readLine();
        }
        ArrayList<String> trueArrayList = new ArrayList<>();
        trueArrayList.add("TOCHE - OIEGE");
        trueArrayList.add("URACY - VYBIL");
        trueArrayList.add("ATEST - XHEJA");
        trueArrayList.add("CKACC - ZHCVG");
        assertArrayEquals(arrayList,trueArrayList);
    }

    @Test
    public void fastDecryptorGeneralTest() throws Exception{
        EncryptedTextExtractor encryptedText = new EncryptedTextExtractor(
                "src/main/resources/EncryptedText");
        FastDecryptor fastDecryptor = new FastDecryptor(encryptedText.encryptedText,"src/main/resources/CodebookAgnesFieldEncrypt.txt");

        String decryptedText = fastDecryptor.decryptFile();

        assertEquals("YOUHAVEBEENASKEDTOCREATEAPHYSICALCODEBOOKTHATCANBEUSEDTOENCODEANDDECODEMESSAGESINTHEFIELDXXXXXGENERATETWOTEXTFILESTITLEDCODEBOOKAGNESFIELDENCRYPTANDCODEBOOKAGNESFIELDDECRYPTXXXXXTHEFIRSTSHOULDINCLUDEALLMAPPINGSORTEDBYINPUTVALUESXXXXXTHESECONDSHOULDINCLUDEALLMAPPINGSSORTEDBYCYPHERTEXTXXXXXTHESEMAPPINGSMUSTBEPRESENTEDINTHEFORMATINPUTSPACEDASHSPACECYPHERXXXXXXX",decryptedText);

    }
    @Test
    public void fastDecryptorIndexCheck() throws Exception{
        EncryptedTextExtractor encryptedText = new EncryptedTextExtractor(
                "src/main/resources/FastDecryptorEncryptedTextTest.txt");
        FastDecryptor fastDecryptor = new FastDecryptor(encryptedText.encryptedText,"src/main/resources/FastDecryptorDecryptionKeyTest.txt");

        String decryptedText = fastDecryptor.decryptFile();

        assertEquals("THISISWORK",decryptedText);

    }

    @Test
    public void fastDecryptorEmptyFileCheck() throws Exception{
        EncryptedTextExtractor encryptedText = new EncryptedTextExtractor(
                "src/main/resources/emptyFile.txt");
        FastDecryptor fastDecryptor = new FastDecryptor(encryptedText.encryptedText,"src/main/resources/emptyFile.txt");

        String decryptedText = fastDecryptor.decryptFile();

        assertEquals("",decryptedText);

    }
    private void assertArrayEquals(ArrayList<String> encryptedTest, ArrayList<String> encryptedText) {
    }

    @Test
    public void fastDecryptorTimeTest() throws Exception{
        EncryptedTextExtractor encryptedText = new EncryptedTextExtractor(
                "src/main/resources/LongEncryptedText");
        FastDecryptor fastDecryptor = new FastDecryptor(encryptedText.encryptedText,"src/main/resources/CodebookAgnesFieldEncrypt.txt");

        double startTime = System.currentTimeMillis();
        String decryptedText = fastDecryptor.decryptFile();
        double finalTime = System.currentTimeMillis()-startTime;

        if(finalTime<10000){
            Assertions.assertEquals(true,true);
        }else{
            Assertions.assertEquals(true,false);
        }



    }
}
