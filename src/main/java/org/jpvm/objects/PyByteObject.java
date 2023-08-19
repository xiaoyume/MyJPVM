package org.jpvm.objects;

import lombok.Data;
import org.jpvm.objects.pyinterface.PyArgs;

import java.util.Arrays;

@Data
public class PyByteObject extends PyObject implements PyArgs {

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

    @Override
    public Object toJavaType() {
        return data;
    }
}
