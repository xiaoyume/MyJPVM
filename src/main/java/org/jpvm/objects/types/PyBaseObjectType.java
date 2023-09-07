package org.jpvm.objects.types;

import org.jpvm.objects.PyUnicodeObject;
import org.jpvm.objects.pyinterface.TypeCheck;
import org.jpvm.objects.pyinterface.TypeName;

public class PyBaseObjectType implements TypeName, TypeCheck {
    private final PyUnicodeObject name;
    public Object parentType = PyTypeType.parentType;

    public PyBaseObjectType(){
        name = new PyUnicodeObject("object");
    }
    @Override
    public Object getType() {
        return parentType;
    }

    @Override
    public PyUnicodeObject getTypeName() {
        return name;
    }
}
