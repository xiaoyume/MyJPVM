package org.jpvm.pycParser;

import lombok.Data;
import org.jpvm.module.Marshal;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Data
public class PycReader {
    private final String pyc;
    private int magicNumber;
    private int bitFiled;
    private int timestamp;
    private int fileSize;
    private CodeObject codeObject;

    public PycReader(String filename) {
        this.pyc = filename;
    }

    public void doParse() throws IOException, IllegalAccessException {
        FileInputStream stream = new FileInputStream(pyc);
        magicNumber = nextInt(stream);
        bitFiled = nextInt(stream);
        timestamp = nextInt(stream);
        fileSize = nextInt(stream);
        Marshal marshal = new Marshal();
        marshal.loadPyObject(stream);
        stream.close();
    }


    public static int nextInt(FileInputStream stream) throws IOException {
        var bytes = new byte[4];
        var size = stream.read(bytes);
        assert size == 4;
        return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

}
