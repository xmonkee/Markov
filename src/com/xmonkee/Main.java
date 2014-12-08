package com.xmonkee;

/**
 * Markov Chain main executable funciton. Calls Markov with text
 * and lookback depth
 */
public class Main {
    public static void main(String[] args){
        Markov markov = new Markov("data/fables.txt", 2);
        markov.generate();
        markov.generate();
        markov.generate();
        markov.generate();
        markov.generate();
        markov.generate();
        markov.generate();
        markov.generate();
    }
}
