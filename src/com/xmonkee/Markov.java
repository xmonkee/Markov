package com.xmonkee;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.*;

public class Markov {

    private HashMap<WordKey, WordList> mainTable;
    private Object[] keys;
    private LinkedList<String> words;
    private Random rnd;
    private ArrayList<WordKey> sentenceBeginnings;
    private int lookback;


    Markov(String filename, int lookback) {
        this.lookback = lookback;
        /** Initialize a hashtable of the type <WordKey, WordList>
         * word-list has type (total count, list[(word, count)])
         * WordKey hashes a linked list of lookback words
         * Read word list
         *   word1 = first word
         *   word2 = second word
         *   .
         *   .
         *   wordn = nth word
         *   word = next word
         *   find word in hashtable with key (word1...wordn)
         *   if not found, insert into hashtable with count = 1
         *   if found, increase count by 1
         *   append word to wordlist
         *   remove word1
         *   start adding again
         * Look for word-count in hash(word1...wordn)
         * Generate random number of in [0..word-count]
         * Keep transversing relevant list as long as sum of count so far is less that random number
         * return the word found
         * print word
         * append word to wordlist
         * remove word1
         * start search again
         */

        sentenceBeginnings = new ArrayList<WordKey>();
        words = new LinkedList<String>();
        rnd = new Random();

        FileInputStream f = null;
        try {
            f = openFile(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        readFile(f);
    }

    private FileInputStream openFile(String inputFileName) throws FileNotFoundException {
        FileInputStream file = new FileInputStream(inputFileName);
        return file;
    }

    private void readFile(FileInputStream file) {
        Scanner scnFile, scnLine;
        String word;
        String prevFirst = "";
        mainTable = new HashMap<WordKey, WordList>();

        scnFile = new Scanner(file);
        while(scnFile.hasNext()){
            scnLine = new Scanner(scnFile.nextLine());
            while(scnLine.hasNext()){
                word = scnLine.next();
                if(words.size()<lookback){
                    words.add(word);
                } else {
                    WordKey key = WordKey.createWordKey(words);
                    if (!mainTable.containsKey(key))
                        mainTable.put(key, new WordList());
                    mainTable.get(key).addWord(word);
                    if (prevFirst.endsWith(".") && Character.isUpperCase(words.peekFirst().charAt(0))) //Beginning of a sentence
                        sentenceBeginnings.add(key);
                    words.add(word);
                    prevFirst = words.remove();
                }
            }
        }
    }

    public String nextWord(){
        boolean isSequence = mainTable.containsKey(WordKey.createWordKey(words));
        WordList wl = mainTable.get(WordKey.createWordKey(words));
        String word = wl.generateWord();
        words.add(word);
        words.remove();
        return word;
    }

    public void generate() {
        WordKey initWords = sentenceBeginnings.get(rnd.nextInt(sentenceBeginnings.size()));
        words.clear();
        for(String word: initWords.wordList){
            words.add(word);
        }
        String word = nextWord();
        int len = 10+ rnd.nextInt(20);
        System.out.print(initWords.toString()+ " " + word + " ");
        for (int j = 0; (j < len) || !word.endsWith(".") ; j++) {
           word = nextWord();
           System.out.print(word + " ");
        }
        System.out.print("\n");
    }
}
