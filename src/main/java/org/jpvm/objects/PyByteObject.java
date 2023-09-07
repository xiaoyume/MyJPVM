package org.jpvm.objects;

import lombok.Data;
import org.jpvm.objects.pyinterface.PyArgs;
import org.jpvm.objects.types.PyBytesType;

import java.util.Arrays;

@Data
public class PyByteObject extends PyObject implements PyArgs {

    public static PyObject type = new PyBytesType();
    private byte[] data;

    public PyByteObject(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("b'");
        for(byte datum : data){
            builder.append(Integer.toHexString((datum & 0xf0) >> 4));
            builder.append(Integer.toHexString(datum & 0xf));
        }
        builder.append("'");
        return builder.toString();
    }

    @Override
    public Object toJavaType() {
        return data;
    }

    @Override
    public Object getType() {
        return data;
    }

    public static PyBoolObject check(PyObject o){
        return new PyBoolObject(o == type);
    }

}
