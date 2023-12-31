package org.jpvm.objects;

import lombok.Data;
import org.jpvm.errors.PyNotImplemented;
import org.jpvm.errors.PyTypeNotMatch;
import org.jpvm.objects.pyinterface.PyArgs;
import org.jpvm.objects.pyinterface.TypeDoIterate;
import org.jpvm.objects.pyinterface.TypeIterable;
import org.jpvm.objects.pyinterface.TypeName;
import org.jpvm.objects.types.PyNoneType;
import org.jpvm.objects.types.PySetType;
import org.jpvm.protocols.PyNumberMethods;
import org.jpvm.protocols.PySequenceMethods;
import org.jpvm.python.BuiltIn;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Data
public class PySetObject extends PyObject implements TypeIterable, PyNumberMethods, PySequenceMethods {
    public static PyObject type = new PySetType();
    private Set<PyObject> set;
    private boolean isFrozen;

    public PySetObject(boolean isFrozen) {
        this();
        this.isFrozen = isFrozen;
    }

    public PySetObject() {
        this.set = new HashSet<>();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (PyObject obj : set) {
            builder.append(obj.toString());
            builder.append(", ");
        }
        if (builder.length() > 2) {
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append("}");
        return builder.toString();
    }

    public void put(PyObject key) {
        set.add(key);
    }

    public void putAll(PySetObject key){
        set.addAll(key.set);
    }

    public boolean contains(PyObject key) {
        return set.contains(key);
    }

    @Override
    public Object toJavaType() {
        return set;
    }

    @Override
    public Object getType() {
        return type;
    }

    @Override
    public PyObject getIterator() {
        return new PysetItrObject();
    }

    public static class PySetItrType extends  PyObject implements TypeName{
        private PyUnicodeObject name;
        public PySetItrType(){
            name = new PyUnicodeObject("set_iterator");
        }

        @Override
        public PyUnicodeObject getTypeName() {
            return name;
        }
    }

    public class PysetItrObject extends PyObject implements TypeDoIterate{
        Iterator<PyObject> iterator;
        public PysetItrObject(){
            iterator = set.iterator();
        }

        @Override
        public PyObject next() {
            if(iterator.hasNext()){
                return iterator.next();
            }
            return BuiltIn.PyExcStopIteration;
        }
    }

    @Override
    public PyUnicodeObject getTypeName() {
        return type.getTypeName();
    }

    @Override
    public PyUnicodeObject str() {
        return  new PyUnicodeObject(toString());
    }

    @Override
    public PyUnicodeObject repr() {
        return str();
    }

    @Override
    public PyBoolObject richCompare(PyObject o) {
        if (o instanceof PySetObject obj) {
            if (set.equals(obj.toJavaType()))
                return BuiltIn.True;
            return BuiltIn.False;
        }
        return BuiltIn.False;
    }

    @Override
    public PyObject sub(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        if (o instanceof PySetObject s) {
            PySetObject ret = new PySetObject();
            ret.putAll(this);
            ret.set.removeAll((Set)s.toJavaType());
            return ret;
        }
        throw new PyTypeNotMatch("set sub function require type set");
    }

    @Override
    public PyObject and(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        if (o instanceof PySetObject s) {
            PySetObject ret = new PySetObject();
            ret.putAll(this);
            ret.set.retainAll((Set)s.toJavaType());
            return ret;
        }
        throw new PyTypeNotMatch("set sub function require type set");
    }

    @Override
    public PyObject xor(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        if (o instanceof PySetObject s) {
            PySetObject ret = new PySetObject();
            for (PyObject pyObject : set) {
                if (!s.contains(pyObject))
                    ret.put(pyObject);
            }
            for (PyObject pyObject : s.set) {
                if (!set.contains(pyObject))
                    ret.put(pyObject);
            }
            return ret;
        }
        throw new PyTypeNotMatch("set sub function require type set");
    }

    @Override
    public PyObject or(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        if (o instanceof PySetObject s) {
            PySetObject ret = new PySetObject();
            ret.putAll(this);
            ret.set.addAll((Set)s.toJavaType());
            return ret;
        }
        throw new PyTypeNotMatch("set sub function require type set");
    }

    @Override
    public PyObject inplaceAnd(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        if (o instanceof PySetObject s) {
            set.retainAll(s.set);
            return this;
        }
        throw new PyTypeNotMatch("set sub function require type set");
    }

    @Override
    public PyObject inplaceXor(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        if (o instanceof PySetObject s) {
            HashSet<PyObject> set = new HashSet<>();
            for (PyObject pyObject : this.set) {
                if (!s.contains(pyObject))
                    set.add(pyObject);
            }
            for (PyObject pyObject : s.set) {
                if (!this.set.contains(pyObject))
                    set.add(pyObject);
            }
            this.set.clear();
            this.set.addAll(set);
            return this;
        }
        throw new PyTypeNotMatch("set sub function require type set");
    }

    @Override
    public PyObject inplaceOr(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        if (o instanceof PySetObject s) {
            set.addAll((Set)s.toJavaType());
            return this;
        }
        throw new PyTypeNotMatch("set sub function require type set");
    }

    @Override
    public PyObject sqLength(PyObject o) throws PyNotImplemented {
        return new PyLongObject(set.size());
    }

    @Override
    public PyObject sqContain(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        return new PyBoolObject(set.contains(o));
    }
}
