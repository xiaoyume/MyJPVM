package org.jpvm.Parser;

import org.jpvm.pycParser.CodeObject;
import org.jpvm.pycParser.Marshal;
import org.jpvm.pycParser.PycReader;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ParserTest {
    @Test
    public void testhead() throws IOException, IllegalAccessException {
        String fpath = "src/test/resources/pys/__pycache__/add.cpython-38.pyc";
        PycReader pycReader = new PycReader(fpath);
        pycReader.doParse();
        System.out.println(pycReader.getTimestamp());
        System.out.println(pycReader.getMagicNumber());
        System.out.println(pycReader.getCodeObject());
    }
    @Test
    public void codeObjTest() throws IOException, IllegalAccessException {
        String fpath = "src/test/resources/pys/__pycache__/add.cpython-38.pyc";
        FileInputStream inputStream = new FileInputStream(fpath);
        inputStream.skipNBytes(16);
        int available = inputStream.available();
        byte[] bytes = new byte[available];

        Marshal marshal = new Marshal();
        int s = inputStream.read(bytes);
        CodeObject codeObject = marshal.loadCodeObject(bytes);
        System.out.println(codeObject);


    }

    @Test
    public void test1(){
        byte b = 'a';
        System.out.println(b);
    }
}
