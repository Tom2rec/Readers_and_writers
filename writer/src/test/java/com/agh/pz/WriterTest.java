package com.agh.pz;

import org.junit.Assert;
import org.junit.Test;

public class WriterTest {

    private final Library library = new Library("test");
    private final Writer writer = new Writer(1111,"test message", library);

    @Test
    public void ConstructorTest(){
        Assert.assertEquals(1111, writer.getWriterId());
        Assert.assertEquals("test message", writer.getMessage());
    }

    @Test
    public void randomGeneratorTest(){
        final int NUMBER = 20;
        int counter = 0;
        int[] nextInt = new int[NUMBER];
        int[] randomInt = new int[NUMBER];

        for(var i = 0; i < NUMBER; i++){
            nextInt[i] = i * NUMBER;
            randomInt[i] = writer.getRand().nextInt(NUMBER * NUMBER);
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
    public void writeTest(){
        writer.write();
        Assert.assertEquals("test message", library.getBookContent());
    }

}
