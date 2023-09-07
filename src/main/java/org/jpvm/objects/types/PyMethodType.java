package org.jpvm.objects.types;

import org.jpvm.objects.PyObject;
import org.jpvm.objects.PyUnicodeObject;

public class PyMethodType extends PyObject{
    private final PyUnicodeObject name;
    public static Object parentType = PyTypeType.parentType;
    public PyMethodType(){
        name = new PyUnicodeObject("method");
    }

    @Override
    public PyUnicodeObject getTypeName() {
        return name;
    }

    @Override
    public Object getType() {
        return parentType;
    }
}