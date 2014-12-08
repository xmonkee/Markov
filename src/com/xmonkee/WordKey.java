package com.xmonkee;
import java.util.LinkedList;

public class WordKey {
    public final String words;
    public LinkedList<String> wordList;

    private WordKey(LinkedList<String> wList) {
        String ws = "";
        wordList = new LinkedList<String>();
        for(String word: wList) {
            ws += word + " ";
            wordList.add(word);
        }
        if(wList.size() > 0)
            words = ws.substring(0, ws.length()-1);
        else words = ws;
    }

    public static WordKey createWordKey(LinkedList<String> wList) {
        return new WordKey(wList);
    }

    @Override
    public int hashCode() {
        return words.hashCode();
    }

    @Override
    public String toString() {
        return words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordKey wordKey = (WordKey) o;
        if (words != null ? !words.equals(wordKey.words) : wordKey.words != null) return false;
        return true;
    }
}
