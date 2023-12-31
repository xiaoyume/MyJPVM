package org.jpvm.objects;

import org.jpvm.objects.types.PyBoolType;
import org.jpvm.objects.types.PyNoneType;

public class PyNoneObject extends PyObject {
    public static PyObject type = new PyNoneType();
    @Override
    public String toString() {
        return "None";
    }

    @Override
    public Object getType() {
        return type;
    }

    public static PyBoolObject check(PyObject o){
        return new PyBoolObject(o == type);
    }
}
