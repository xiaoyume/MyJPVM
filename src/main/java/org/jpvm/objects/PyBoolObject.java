package org.jpvm.objects;

import lombok.Data;
import org.jpvm.objects.pyinterface.PyArgs;
import org.jpvm.objects.types.PyBoolType;
import org.jpvm.protocols.PyNumberMethods;
@Data
public class PyBoolObject extends PyObject implements PyNumberMethods {
    public static PyObject type = new PyBoolType();
    private boolean bool;

    public PyBoolObject(boolean bool) {
        this.bool = bool;
    }


    public boolean isTrue(){
        return bool;
    }
    public boolean isFalse(){
        return !bool;
    }

    @Override
    public Object getType() {
        return type;
    }

    public static PyBoolObject check(PyObject o){
        return new PyBoolObject(o == type);
    }

    @Override
    public PyObject add(PyObject o) {
        if(!(o instanceof PyBoolObject)){
            return new PyBoolObject(false);
        }
        return new PyBoolObject(bool && ((PyBoolObject)o).bool);
    }

    @Override
    public PyObject or(PyObject o) {
        if(!(o instanceof PyBoolObject)){
            return new PyBoolObject(false);
        }
        return new PyBoolObject(bool || ((PyBoolObject)o).bool);
    }

    @Override
    public PyObject xor(PyObject o) {
        if(!(o instanceof PyBoolObject)){
            return new PyBoolObject(false);
        }
        return new PyBoolObject(bool != ((PyBoolObject)o).bool);
    }

    @Override
    public String toString() {
        return "PyBoolObject{" +
                "bool=" + bool +
                '}';
    }

    @Override
    public Object toJavaType() {
        return bool;
    }
}
