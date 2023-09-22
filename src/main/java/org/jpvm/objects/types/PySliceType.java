package org.jpvm.objects.types;

import org.jpvm.objects.PyObject;
import org.jpvm.objects.PyUnicodeObject;

public class PySliceType extends PyObject {
    private final PyUnicodeObject name;
    public Object parentType = PyTypeType.parentType;
    public PySliceType(){
        name = new PyUnicodeObject("slice");
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
