package org.jpvm.errors;

import org.jpvm.objects.PyUnicodeObject;

public class PyNoSuchMethod extends PyException{
    public PyNoSuchMethod(String msg) {
        super(msg);
    }

    public PyNoSuchMethod(PyUnicodeObject msg){
        super((String)msg.toJavaType());
    }
}
