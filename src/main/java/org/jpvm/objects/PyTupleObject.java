package org.jpvm.objects;

import java.util.Arrays;

public class PyTupleObject extends PyObject {
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
}
