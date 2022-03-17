package com.agh.pz;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.agh.pz.LibraryConst.*;

/**
 * Library
 * The library represents area with resource which can be read and modified by library users
 */
public class Library implements ILibrary {

    protected static final Logger LOGGER = Logger.getLogger(Library.class.getName());

    public Lock getLock() {
        return lock;
    }

    public Condition getWriters() {
        return writers;
    }

    public Condition getReaders() {
        return readers;
    }

    public String getBookContent() {
        return bookContent;
    }

    public int getWritersInLibrary() {
        return writersInLibrary;
    }

    public int getReadersInLibrary() {
        return readersInLibrary;
    }

    private final Lock lock = new ReentrantLock();

    private final Condition writers = lock.newCondition();

    private final Condition readers = lock.newCondition();

    private String bookContent;

    private int writersInLibrary;

    private int readersInLibrary;

    /**
     * Constructor for empty library with book which has specific content
     *
     * @param initialBookContent initial content for book
     */
    public Library(String initialBookContent) {
        this.bookContent = initialBookContent;
        writersInLibrary = 0;
        readersInLibrary = 0;
    }

    /**
     * Method which logs operation of user who wants to read and allows to read the book
     * It is possible for user to read if in library is 0 writers and up to 5 readers, otherwise it holds off user
     *
     * @param readerId reader identifier
     * @return bookContent content of the book (resource)
     */
    public String read(int readerId) {

        LOGGER.log(Level.INFO, () -> READER_ID + addBraces(readerId) + WANT);
        lock.lock();
        try {
            while (writersInLibrary > 0 || readersInLibrary > 4) {
                readers.awaitUninterruptibly();
            }

            ++readersInLibrary;

            LOGGER.log(Level.INFO, () -> READER_ID + addBraces(readerId) + IN + "\n" +
                    W + writersInLibrary + R + readersInLibrary + "\n");
        } finally {
            lock.unlock();
        }


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            Thread.currentThread().interrupt();
        }

        LOGGER.log(Level.INFO, () -> READER_ID + addBraces(readerId) + READ + bookContent);

        lock.lock();
        try {
            --readersInLibrary;
            LOGGER.log(Level.INFO, () -> READER_ID + addBraces(readerId) + OUT + "\n" +
                    W + writersInLibrary + R + readersInLibrary + "\n");

            if (readersInLibrary == 0) {
                writers.signal();
            }
        } finally {
            lock.unlock();
        }
        return bookContent;
    }

    /**
     * Method which logs operation of user who wants to write and allows to write the book
     * It is possible for user to write if in library is nobody, otherwise it holds off user
     *
     * @param writerId    identifier of the current writer
     * @param bookContent content of the book (resource)
     */
    public void write(int writerId, String bookContent) {

        LOGGER.log(Level.INFO, () -> WRITER_ID + addBraces(writerId) + WANT);
        lock.lock();
        try {
            while (writersInLibrary > 0 || readersInLibrary > 0) {
                writers.awaitUninterruptibly();
            }

            ++writersInLibrary;
            LOGGER.log(Level.INFO, () -> WRITER_ID + addBraces(writerId) + IN + "\n" +
                    W + writersInLibrary + R + readersInLibrary + "\n");
        } finally {
            lock.unlock();

        }


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            Thread.currentThread().interrupt();
        }

        this.bookContent = bookContent;

        LOGGER.log(Level.INFO, () -> WRITER_ID + addBraces(writerId) + WRITE);

        lock.lock();
        try {
            --writersInLibrary;
            LOGGER.log(Level.INFO, () -> WRITER_ID + addBraces(writerId) + OUT + "\n" +
                    W + writersInLibrary + R + readersInLibrary + "\n");

            readers.signalAll();
            writers.signal();
        } finally {
            lock.unlock();
        }
    }

    private String addBraces(int value) {
        return "[" + value + "]";
    }
}
