package org.jpvm.objects;

import org.jpvm.errors.PyException;
import org.jpvm.errors.PyIndexOutOfBound;
import org.jpvm.errors.PyNotImplemented;
import org.jpvm.objects.pyinterface.PyArgs;
import org.jpvm.objects.pyinterface.TypeDoIterate;
import org.jpvm.objects.pyinterface.TypeIterable;
import org.jpvm.objects.types.PySetType;
import org.jpvm.objects.types.PyTupleType;
import org.jpvm.objects.types.PyTypeType;
import org.jpvm.python.BuiltIn;

import java.util.Arrays;

public class PyTupleObject extends PyObject implements TypeIterable {
    public static PyObject type = new PyTupleType();
    private final PyObject[] obItem;
    private int hashCode;
    private boolean hashDone;

    public PyTupleObject(int size) {
        obItem = new PyObject[size];
    }

    public void set(int idx, PyObject obj) {
        obItem[idx] = obj;
    }

    public PyObject get(int idx) {
        if (idx < 0 || idx >= obItem.length) {
            throw new IndexOutOfBoundsException("idx = " + idx + "is out of bound");
        }
        return obItem[idx];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        for (PyObject object : obItem) {
            builder.append(object.toString());
            builder.append(", ");
        }
        if (builder.length() > 2) {
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append(")");
        return builder.toString();
    }

    @Override
    public Object toJavaType() {
        return obItem;
    }

    public int size(){
        return obItem.length;
    }

    @Override
    public Object getType() {
        return type;
    }

    public static PyBoolObject check(PyObject o){
        return new PyBoolObject(o == type);
    }
    public static class PyTupleItrType extends PyObject{
        private final Object type = PyTypeType.parentType;
        private final PyUnicodeObject name;

        public PyTupleItrType() {
            name = new PyUnicodeObject("tuple_iterator");
        }

        @Override
        public Object getType() {
            return type;
        }

        @Override
        public PyUnicodeObject getTypeName() {
            return name;
        }
    }

    public class PyTupleItrObject extends PyObject implements TypeDoIterate {
        private int idx;

        public static PyObject type = new PyTupleItrType();

        public PyTupleItrObject() {
            idx = 0;
        }

        @Override
        public PyObject next() throws PyException {
            if (idx < obItem.length)
                return obItem[idx++];
            return BuiltIn.PyExcStopIteration;
        }

        @Override
        public PyObject get(int idx) throws PyIndexOutOfBound, PyNotImplemented {
            if (idx < obItem.length)
                return obItem[idx];
            throw new PyIndexOutOfBound("tuple is out of bound for index " + idx);
        }

        @Override
        public int size() {
            return obItem.length;
        }
    }

    @Override
    public PyBoolObject isHashable() {
        return BuiltIn.True;
    }

    @Override
    public PyObject getIterator() {
        return new PyTupleItrObject();
    }

    @Override
    public PyLongObject hash() {
        if (hashDone)
            return new PyLongObject(hashCode);
        for (PyObject e : obItem) {
            hashCode = 31 * hashCode + (e == null ? 0 : (int) e.hash().getData());
        }
        return new PyLongObject(hashCode);
    }
}
