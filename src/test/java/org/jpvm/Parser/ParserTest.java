package org.jpvm.Parser;

import org.jpvm.bytecode.ByteCodeBuffer;
import org.jpvm.bytecode.Instruction;
import org.jpvm.module.Disassember;
import org.jpvm.pycParser.CodeObject;
import org.jpvm.module.Marshal;
import org.jpvm.pycParser.PycReader;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

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

    @Test
    public void codebuftest() throws IOException, IllegalAccessException {
        String fpath = "src/test/resources/pys/__pycache__/add.cpython-38.pyc";
        PycReader pycReader = new PycReader(fpath);
        pycReader.doParse();
        CodeObject codeObject = pycReader.getCodeObject();
        System.out.println(codeObject);
        ByteCodeBuffer codeBuffer = new ByteCodeBuffer(codeObject);
        System.out.println(codeBuffer.getCode());
        Iterator<Instruction> iterator = codeBuffer.iterator();
        while (iterator.hasNext()){
            Instruction next = iterator.next();
            System.out.println(next.getOpcode());
            System.out.println(next.getOparg());
            System.out.println("*******");
        }
    }

//    @Test
//    public void disTest() throws IOException, IllegalAccessException {
//        String fpath = "src/test/resources/pys/__pycache__/add.cpython-38.pyc";
//        PycReader pycReader = new PycReader(fpath);
//        pycReader.doParse();
//        CodeObject codeObject = pycReader.getCodeObject();
//        Disassember disassember = new Disassember(codeObject);
////        disassember.dis();
//
//    }
}