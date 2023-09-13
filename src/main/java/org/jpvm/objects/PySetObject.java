package org.jpvm.objects;

import lombok.Data;
import org.jpvm.objects.pyinterface.PyArgs;
import org.jpvm.objects.pyinterface.TypeDoIterate;
import org.jpvm.objects.pyinterface.TypeIterable;
import org.jpvm.objects.pyinterface.TypeName;
import org.jpvm.objects.types.PyNoneType;
import org.jpvm.objects.types.PySetType;
import org.jpvm.python.BuiltIn;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Data
public class PySetObject extends PyObject implements PyArgs, TypeIterable {
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

    public static PyBoolObject check(PyObject o){
        return new PyBoolObject(o == type);
    }
}
