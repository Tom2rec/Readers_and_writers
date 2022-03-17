package com.agh.pz;

import java.util.ArrayList;
import java.util.List;

/**
 * Project launcher
 */
public class LibraryLauncher {

    private static final Library library = new Library("wonderful content");

    protected static final List<Thread> libraryUsers = new ArrayList<>();

    /**
     * This is main method of the project, which
     * - creates library
     * - add library users
     * - enables user to make acction
     * @param args Unused
     */

    public static void main(String[] args) {

        addWriters();
        addReaders();
        startThreads();

    }

    /**
     * method to add writers
     */
    public static void addWriters(){
        for(var i = 0; i < LibraryConst.WRITERS_NUMBER; i++){
            libraryUsers.add(new Writer(i,"Really meaningful message - " + i, library));
        }
    }

    /**
     * method to add readers
     */
    public static void addReaders(){
        for(var i = 0; i < LibraryConst.READERS_NUMBER; i++){
            libraryUsers.add(new Reader(i, library));
        }
    }

    /**
     * method to start threads
     */
    public static void startThreads(){
        for (Thread libraryUser : libraryUsers){
            libraryUser.start();
        }
    }

}
