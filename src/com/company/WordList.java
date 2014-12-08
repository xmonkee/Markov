package com.company;
import java.util.ArrayList;
import java.util.Random;

public class WordList {
    private int sumWordCount; // sum(count) in list
    private ArrayList<WordCountPair> wcpList; //list of (word , count)
    Random rInt;

    WordList(){
        this.sumWordCount = 0;
        this.wcpList = new ArrayList<WordCountPair>();
        this.rInt = new Random();
    }

    public void addWord(String word){
        WordCountPair oldword = findWord(word);
        if(oldword == null){
            wcpList.add(new WordCountPair(word, 1));
        } else {
            oldword.count++;
        }
        sumWordCount++;
    }

    private WordCountPair findWord(String word){
        for (WordCountPair wcPair: wcpList) {
            if(wcPair.word.equals(word)){
                return wcPair;
            }
        }
        return null;
    }

    public String generateWord(){
        int iRand = rInt.nextInt(sumWordCount);
        int wcpIndex = 0;
        int curRand = wcpList.get(wcpIndex).count;
        while(curRand < iRand){
            wcpIndex += 1;
            curRand += wcpList.get(wcpIndex).count;
        }
        return wcpList.get(wcpIndex).word;


    }

}
