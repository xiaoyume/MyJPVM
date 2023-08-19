package org.jpvm.objects;

import lombok.Data;
import org.jpvm.objects.pyinterface.PyArgs;

@Data
public class PyFloatObject extends PyObject implements PyArgs {
    private double data;

    public PyFloatObject(double data) {
        this.data = data;
    }

    public PyFloatObject(float data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }

    @Override
    public Object toJavaType() {
        return data;
    }
}
