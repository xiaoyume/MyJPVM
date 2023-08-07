package org.jpvm.objects;

import lombok.Data;

@Data
public class PyFloatObject extends PyObject{
    private double data;

    public PyFloatObject(double data) {
        this.data = data;
    }
    public PyFloatObject(float data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PyFloatObject{" +
                "data=" + data +
                '}';
    }
}
