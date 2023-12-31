package org.jpvm.objects.types;

import org.jpvm.objects.PyObject;
import org.jpvm.objects.PySetObject;
import org.jpvm.objects.PyUnicodeObject;

public class PySetType extends PyObject {
    private final PyUnicodeObject name;
    public static Object parentType = PyTypeType.parentType;
    public PySetType(){
        name = new PyUnicodeObject("set");
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
