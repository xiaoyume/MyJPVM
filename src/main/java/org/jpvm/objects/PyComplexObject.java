package org.jpvm.objects;

import lombok.Data;
import org.jpvm.objects.types.PyComplexType;

@Data
public class PyComplexObject extends PyObject {
    public static PyObject type = new PyComplexType();
    private PyFloatObject real;
    private PyFloatObject image;

    public PyComplexObject(PyFloatObject real, PyFloatObject image) {
        this.real = real;
        this.image = image;
    }

    @Override
    public String toString() {
        return real + "+" + image + "i";
    }

    @Override
    public Object toJavaType() {
        return new double[]{(double)getImage().toJavaType(), (double)getReal().toJavaType()};
    }

    @Override
    public Object getType() {
        return type;
    }
    public static PyBoolObject check(PyObject o){
        return new PyBoolObject(o == type);
    }
}
