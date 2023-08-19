package org.jpvm.objects;

import lombok.Data;
import org.jpvm.objects.pyinterface.PyArgs;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PyUnicodeObject extends PyObject implements PyArgs {
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

    @Override
    public Object toJavaType() {
        return new String(data, StandardCharsets.UTF_8);
    }
}
