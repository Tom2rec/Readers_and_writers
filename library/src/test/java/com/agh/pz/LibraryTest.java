package com.agh.pz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;

public class LibraryTest {

    private Library library;

    @Before
    public void createLibrary() {
        library = new Library("test Content");
    }


    @Test
    public void constructorTest() {
        Assert.assertEquals("test Content", library.getBookContent());
        Assert.assertEquals(0, library.getReadersInLibrary());
        Assert.assertEquals(0, library.getWritersInLibrary());
    }

    @Test
    public void writeTest() throws InterruptedException {
        String[] expectedMessage = {"message 1", "message 2", "message 3"};
        String[] actualMessage = new String[3];

        Thread thread1 = new Thread(() -> library.write(1111, "message 1"));

        Thread thread2 = new Thread(() -> library.write(1112, "message 2"));

        Thread thread3 = new Thread(() -> library.write(1113, "message 3"));

        thread1.start();
        thread1.join();
        actualMessage[0] = library.getBookContent();

        thread2.start();
        thread2.join();
        actualMessage[1] = library.getBookContent();

        thread3.start();
        thread3.join();
        actualMessage[2] = library.getBookContent();

        Assert.assertArrayEquals(expectedMessage, actualMessage);
    }

    @Test
    public void readTest() throws InterruptedException {
        String expectedMessage = "message 1";

        Thread writer = new Thread(() -> library.write(1111, "message 1"));
        writer.start();
        writer.join();

        Reader reader = new Reader();
        Thread threadReader = new Thread(reader);

        threadReader.start();
        threadReader.join();

        Assert.assertEquals(expectedMessage, reader.getValue());
    }

    public class Reader implements Runnable {
        private volatile String value;

        @Override
        public void run() {
            value = library.read(1112);
        }

        public String getValue(){
            return value;
        }
    }

}
