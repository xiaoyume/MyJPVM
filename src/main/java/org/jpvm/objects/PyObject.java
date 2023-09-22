package org.jpvm.objects;

import lombok.Data;
import org.jpvm.objects.pyinterface.*;
import org.jpvm.objects.types.PyBaseObjectType;
@Data
public class PyObject implements PyArgs, TypeCheck,
        TypeName, TypeStr, TypeRepr, TypeHash, TypeRichCompare, TypeNew,
        TypeInit, TypeCall, PyHashable{

    public static Object type = new PyBaseObjectType();
    public static PyUnicodeObject name;
    private PyDictObject dict;
    private PyTupleObject bases;
    private PyListObject mro;
    private PyLongObject hashcode;


    @Override
    public String toString() {
        return "<PyObject>";
    }

    @Override
    public Object toJavaType() {
        return null;
    }

    @Override
    public Object getType() {
        return type;
    }

    public static PyBoolObject check(PyObject o){
        return  new PyBoolObject(o == type);
    }

    @Override
    public PyLongObject hash() {
        return new PyLongObject(0);
    }

    @Override
    public PyUnicodeObject getTypeName() {
        if(name == null){
            return new PyUnicodeObject("object");
        }
        return name;
    }

    @Override
    public PyUnicodeObject repr() {
        return getTypeName();
    }

    @Override
    public PyBoolObject richCompare(PyObject o) {
        return new PyBoolObject(o == this);
    }

    @Override
    public PyUnicodeObject str() {
        return getTypeName();
    }

    @Override
    public int hashCode(){
        return (int) hash().getData();
    }

}
