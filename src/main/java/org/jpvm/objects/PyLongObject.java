package org.jpvm.objects;

import lombok.Data;
import org.jpvm.errors.PyTypeNotMatch;
import org.jpvm.objects.pyinterface.PyArgs;
import org.jpvm.objects.types.PyLongType;
import org.jpvm.protocols.PyNumberMethods;
import org.jpvm.python.BuiltIn;

@Data
public class PyLongObject extends PyObject implements PyNumberMethods {
    public static PyObject type = new PyLongType();
    private long data;

    public PyLongObject(long data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }

    public PyLongObject(int data) {
        this.data = data;
    }

    @Override
    public Object getType() {
        return type;
    }

    @Override
    public PyUnicodeObject getTypeName() {
        return type.getTypeName();
    }

    @Override
    public Object toJavaType() {
        return data;
    }

    public static PyBoolObject check(PyObject o){
        return new PyBoolObject(o == type);
    }

    @Override
    public PyUnicodeObject str() {
        return new PyUnicodeObject(Long.toString(data));
    }

    @Override
    public PyUnicodeObject repr() {
        return str();
    }

    @Override
    public PyBoolObject richCompare(PyObject o) {
        if(!(o instanceof PyLongObject)){
            return BuiltIn.False;
        }
        if(((PyLongObject)o).getData() == data){
            return BuiltIn.True;
        }
        return BuiltIn.False;
    }

    @Override
    public PyLongObject hash() {
        return new PyLongObject(this.data*31);
    }

    @Override
    public PyObject add(PyObject o) throws PyTypeNotMatch {
        if(!(o instanceof PyLongObject)){
            throw new PyTypeNotMatch("cannot apply function add on int and" + o.getTypeName());
        }
        return new PyLongObject(data + ((PyLongObject)o).getData());

    }

    @Override
    public PyObject sub(PyObject o) throws PyTypeNotMatch {
        if(!(o instanceof PyLongObject)){
            throw new PyTypeNotMatch("cannot apply function sub on int and" + o.getTypeName());
        }
        return new PyLongObject(data - ((PyLongObject)o).getData());
    }

    @Override
    public PyObject mul(PyObject o) throws PyTypeNotMatch {
        if(!(o instanceof PyLongObject)){
            throw new PyTypeNotMatch("cannot apply function mul on int and" + o.getTypeName());
        }
        return new PyLongObject(data * ((PyLongObject)o).getData());
    }

    @Override
    public PyObject mod(PyObject o) throws PyTypeNotMatch {
        if(!(o instanceof PyLongObject)){
            throw new PyTypeNotMatch("cannot apply function mod on int and" + o.getTypeName());
        }
        return new PyLongObject(data % ((PyLongObject)o).getData());
    }

    @Override
    public PyObject divmod(PyObject o) throws PyTypeNotMatch {
        if(!(o instanceof PyLongObject)){
            throw new PyTypeNotMatch("can not apply function divmod on int and " + o.getTypeName());
        }
        PyTupleObject tupleObject = new PyTupleObject(2);
        tupleObject.set(0, trueDiv(o));
        tupleObject.set(1, mod(o));
        return tupleObject;
    }

    @Override
    public PyObject pow(PyObject o) throws PyTypeNotMatch {
        if(!(o instanceof PyLongObject)){
            throw new PyTypeNotMatch("cannot apply function pow on int and " + o.getTypeName());
        }
        return new PyLongObject((long)Math.pow(data, ((PyLongObject) o).getData()));
    }

    @Override
    public PyObject neg() {
        return new PyLongObject(-data);
    }

    @Override
    public PyObject pos() {
        return new PyLongObject(data);
    }

    @Override
    public PyObject abs() {
        return new PyLongObject(Math.abs(data));
    }

    @Override
    public PyObject bool() {
        return new PyBoolObject(data != 0);
    }

    @Override
    public PyObject invert() {
        return new PyLongObject(~data);
    }

    @Override
    public PyObject lshift(PyObject o) throws PyTypeNotMatch {
        if(!(o instanceof PyLongObject)){
            throw new PyTypeNotMatch("cannot apply << on int and " + o.getTypeName());
        }
        return new PyLongObject(data << ((PyLongObject) o).getData());
    }

    @Override
    public PyObject rshift(PyObject o) throws PyTypeNotMatch {
        if(!(o instanceof PyLongObject)){
            throw new PyTypeNotMatch("cannot apply >> on int and " + o.getTypeName());
        }
        return new PyLongObject(data >> ((PyLongObject) o).getData());
    }

    @Override
    public PyObject and(PyObject o) throws PyTypeNotMatch {
        if(!(o instanceof PyLongObject)){
            throw new PyTypeNotMatch("cannot apply and on int and " + o.getTypeName());
        }
        return new PyLongObject(data & ((PyLongObject) o).getData());
    }

    @Override
    public PyObject xor(PyObject o) throws PyTypeNotMatch {
        if(!(o instanceof PyLongObject)){
            throw new PyTypeNotMatch("cannot apply xor on int and " + o.getTypeName());
        }
        return new PyLongObject(data ^ ((PyLongObject) o).getData());
    }

    @Override
    public PyObject or(PyObject o) throws PyTypeNotMatch {
        if(!(o instanceof PyLongObject)){
            throw new PyTypeNotMatch("cannot apply or on int and " + o.getTypeName());
        }
        return new PyLongObject(data | ((PyLongObject) o).getData());
    }

    @Override
    public PyObject nbInt() {
        return new PyLongObject(data);
    }

    @Override
    public PyObject floorDiv(PyObject o) throws PyTypeNotMatch {
        if (!(o instanceof PyLongObject))
            throw new PyTypeNotMatch("can apply / on int and " + o.getTypeName());
        return new PyLongObject(data /  ((PyLongObject) o).getData());
    }

    @Override
    public PyObject trueDiv(PyObject o) throws PyTypeNotMatch {
        if (!(o instanceof PyLongObject))
            throw new PyTypeNotMatch("can apply & on int and " + o.getTypeName());
        return new PyFloatObject((double) data /  ((PyLongObject) o).getData());
    }

    @Override
    public PyObject index() {
        return new PyLongObject(data);
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj instanceof Integer o) return data == o;
        if (obj instanceof Long o) return data == o;
        return false;
    }
}
