package org.jpvm.objects;

import lombok.Data;

@Data
public class PyComplexObject extends PyObject {
    private PyFloatObject real;
    private PyFloatObject image;

    public PyComplexObject(PyFloatObject real, PyFloatObject image) {
        this.real = real;
        this.image = image;
    }

    @Override
    public String toString() {
        return "PyComplexObject{" +
                "real=" + real +
                ", image=" + image +
                '}';
    }
}
