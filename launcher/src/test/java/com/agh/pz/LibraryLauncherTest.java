package com.agh.pz;

import org.junit.Assert;
import org.junit.Test;

public class LibraryLauncherTest {

    @Test
    public void addWritersTest() {
        LibraryLauncher.addWriters();
        Assert.assertEquals(LibraryConst.WRITERS_NUMBER, LibraryLauncher.libraryUsers.size());
    }

    @Test
    public void ReadersTest() {
        LibraryLauncher.addReaders();
        Assert.assertEquals(LibraryConst.READERS_NUMBER + LibraryConst.WRITERS_NUMBER, LibraryLauncher.libraryUsers.size());
    }
}
