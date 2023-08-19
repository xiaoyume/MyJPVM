package org.jpvm.pvm;

import org.jpvm.pycParser.PycReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        String filename = "";
        PycReader pycReader = new PycReader(filename);
        pycReader.doParse();
    }
}
