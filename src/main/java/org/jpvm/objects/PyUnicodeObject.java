package org.jpvm.objects;

import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PyUnicodeObject extends PyObject {
    private byte[] data;

    public PyUnicodeObject(byte[] data) {
        this.data = data;
    }

    //字节数组转字符串
    public String getData() {
        return new String(data, StandardCharsets.UTF_8);
    }

    public void setData(String s) {
        this.data = s.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String toString() {
        return "'" + getData() + "'";
    }
}
