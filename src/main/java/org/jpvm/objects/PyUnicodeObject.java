package org.jpvm.objects;

import lombok.Data;

import java.util.Arrays;
@Data
public class PyUnicodeObject extends PyObject{
    private byte[] data;

    public PyUnicodeObject(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PyUnicodeObject{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
