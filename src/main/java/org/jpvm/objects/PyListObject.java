package org.jpvm.objects;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class PyListObject extends PyObject {
    private final List<PyObject> obItem;

    public PyListObject() {
        this(1);
    }

    public PyListObject(int size) {
        obItem = new ArrayList<>(size);
    }

    public void app1(PyObject obj) {
        obItem.add(obj);
    }

    public void insert(int idx, PyObject obj) {
        obItem.add(idx, obj);
    }

    public PyObject pop() {
        if (obItem.size() == 0) {
            throw new IndexOutOfBoundsException("list has no elements");
        }
        PyObject obj = obItem.get(obItem.size() - 1);
        obItem.remove(obItem.size() - 1);
        return obj;
    }

    public boolean remove(PyObject obj) {
        return obItem.remove(obj);
    }

    public void reverse() {
        Collections.reverse(obItem);
    }

    public PyObject get(int idx) {
        if (idx < 0 || idx >= obItem.size()) {
            throw new IndexOutOfBoundsException();
        }
        return obItem.get(idx);
    }

    public int size() {
        return obItem.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (PyObject object : obItem){
            builder.append(object.toString());
            builder.append(", ");
        }
        if(builder.length() > 2){
            builder.delete(builder.length() - 2, builder.length());

        }
        builder.append("]");
        return builder.toString();
    }
}
