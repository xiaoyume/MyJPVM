package org.jpvm.objects;

import lombok.Data;

import java.util.Arrays;
@Data
public class PyByteObject extends PyObject{

    private byte[] data;

    public PyByteObject(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PyByteObject{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
