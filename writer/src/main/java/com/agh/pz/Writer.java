package com.agh.pz;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Class which represents writer
 */
public class Writer extends Thread {

    protected static final Logger LOGGER = Logger.getLogger(Writer.class.getName());

    public int getWriterId() {
        return writerId;
    }

    public String getMessage() {
        return message;
    }

    public Random getRand() {
        return rand;
    }

    public Library getLibrary() {
        return library;
    }

    private final int writerId;

    private final String message;

    private final Random rand;

    private final Library library;

    /**
     * @param writerId identifier of the writer
     * @param message message which user wants to write
     * @param library specific library which this reader use
     */
    public Writer(int writerId, String message, Library library) {
        this.writerId = writerId;
        this.message = message;
        this.library = library;
        this.rand = new Random();
    }

    /**
     *  Infinite method which execute method write and then wait from 5000 to 110000 milliseconds
     */
    @Override
    public void run(){
        while(true){
            write();
            try {
                int ms = rand.nextInt(6000) + 5000;
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * allows user to write to library resource
     */
    public void write(){
        library.write(writerId, message);
    }

}
