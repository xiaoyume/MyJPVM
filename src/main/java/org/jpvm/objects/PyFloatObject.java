package org.jpvm.objects;

import lombok.Data;
import org.jpvm.objects.pyinterface.PyArgs;
import org.jpvm.objects.types.PyFloatType;

@Data
public class PyFloatObject extends PyObject implements PyArgs {
    public static PyObject type = new PyFloatType();
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

    @Override
    public Object getType() {
        return type;
    }
    public static PyBoolObject check(PyObject o){
        return new PyBoolObject(o == type);
    }
}
