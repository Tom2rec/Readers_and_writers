package com.agh.pz;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class which represents reader
 */
public class Reader extends Thread {

    protected static final Logger LOGGER = Logger.getLogger(Reader.class.getName());

    private final int readerId;

    private final Library library;

    public int getReaderId() {
        return readerId;
    }

    public Library getLibrary() {
        return library;
    }

    public Random getRand() {
        return rand;
    }

    private final Random rand;

    /**
     * @param readerId reader identifier
     * @param library specific library which this reader use
     */
    public Reader(int readerId, Library library) {
        this.readerId = readerId;
        this.library = library;
        this.rand = new Random();
    }

    /**
     * Infinite method which allows user to read and then wait from 2000 to 6000 milliseconds
     */
    @Override
    public void run(){
        while(true){
            read();
            try {
                int ms = rand.nextInt(4000) + 2000;
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                Thread.currentThread().interrupt();
            }
        }
    }

    public String read(){
       return library.read(readerId);
    }
}
