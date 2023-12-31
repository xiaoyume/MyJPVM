package org.jpvm.objects.types;

import org.jpvm.objects.PyObject;
import org.jpvm.objects.PyUnicodeObject;
import org.jpvm.objects.pyinterface.PyArgs;

public class PyUnicodeType extends PyObject implements PyArgs {
    private final PyUnicodeObject name;

    public static Object parentType = PyTypeType.parentType;

    public PyUnicodeType(){
        this.name = new PyUnicodeObject("str");
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
