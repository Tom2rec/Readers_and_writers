package com.agh.pz;

/**
 * interface for library resource access
 */
public interface ILibrary {

    String read(int readerId);

    void write(int writerId, String bookContent);
}
