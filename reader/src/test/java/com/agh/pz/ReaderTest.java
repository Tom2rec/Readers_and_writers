package com.agh.pz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReaderTest {

    private Reader reader;

    @Before
    public void createLibraryAndReader(){
        Library library = new Library("test Content");
        reader = new Reader(1111, library);
    }

    @Test
    public void constructorTest(){
        Assert.assertEquals(1111, reader.getReaderId());
        Assert.assertEquals("test Content",reader.getLibrary().read(reader.getReaderId()));
    }

    @Test
    public void randomTest(){
        final int NUMBER = 10;
        int counter = 0;
        int[] nextInt = new int[NUMBER];
        int[] randomInt = new int[NUMBER];

        for(var i = 0; i < NUMBER; i++){
            nextInt[i] = i * NUMBER;
            randomInt[i] = reader.getRand().nextInt(NUMBER * NUMBER);
        }
        for (var i = 0; i < NUMBER; i++) {
            for (var k = 0; k < NUMBER; k++) {
                if (nextInt[i] == randomInt[k]) {
                    ++counter;
                }
            }
        }
        Assert.assertTrue(counter < NUMBER / 2);
    }

    @Test
    public void readTest(){
        Assert.assertEquals("test Content", reader.read());
    }

}
