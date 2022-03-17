package com.agh.pz;

/**
 * Class which stores library const for specific type
 */
public final class LibraryConst {

    private LibraryConst() {
        throw new IllegalStateException("Library const class");
    }

    public static final int WRITERS_NUMBER = 3;

    public static final int READERS_NUMBER = 10;

    public static final String IN = " has entered the library";

    public static final String OUT = " has left the library";

    public static final String WANT = " wants to go into Library";

    public static final String READ = " has read book: ";

    public static final String WRITE = " has written the book";

    public static final String READER_ID = "READER ID: ";

    public static final String WRITER_ID = "WRITER ID: ";

    public static final String W = "WRITERS: ";

    public static final String R = " READERS: ";

}
