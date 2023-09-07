package org.jpvm.python;

import org.jpvm.errors.PyException;
import org.jpvm.objects.PyBoolObject;
import org.jpvm.objects.PyNoneObject;
import org.jpvm.objects.PyObject;
import org.jpvm.objects.PySetObject;

public class BuiltIn {
    public static PyBoolObject True = new PyBoolObject(true);
    public static PyBoolObject False = new PyBoolObject(false);
    public static PyNoneObject None = new PyNoneObject();
    public static PyNoneObject NULL = new PyNoneObject();
    public static PyObject ELLIPSIS = new PyObject();
    public static PySetObject FROZENSET = new PySetObject(true);
    public static PyObject PyExcStopIteration = new PyException("PyExcStopIteration");

    public PyObject print(PyObject... objs){
        for(PyObject obj : objs){

        }
        return BuiltIn.None;
    }
}
