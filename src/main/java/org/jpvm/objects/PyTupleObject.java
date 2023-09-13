package org.jpvm.objects;

import org.jpvm.objects.pyinterface.PyArgs;
import org.jpvm.objects.types.PySetType;
import org.jpvm.objects.types.PyTupleType;

import java.util.Arrays;

public class PyTupleObject extends PyObject implements PyArgs {
    public static PyObject type = new PyTupleType();
    private final PyObject[] obItem;

    public PyTupleObject(int size) {
        obItem = new PyObject[size];
    }

    public void set(int idx, PyObject obj) {
        obItem[idx] = obj;
    }

    public PyObject get(int idx) {
        if (idx < 0 || idx >= obItem.length) {
            throw new IndexOutOfBoundsException("idx = " + idx + "is out of bound");
        }
        return obItem[idx];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        for (PyObject object : obItem) {
            builder.append(object.toString());
            builder.append(", ");
        }
        if (builder.length() > 2) {
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append(")");
        return builder.toString();
    }

    @Override
    public Object toJavaType() {
        return obItem;
    }

    public int size(){
        return obItem.length;
    }

    @Override
    public Object getType() {
        return type;
    }

    public static PyBoolObject check(PyObject o){
        return new PyBoolObject(o == type);
    }
}
