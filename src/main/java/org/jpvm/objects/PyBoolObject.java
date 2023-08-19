package org.jpvm.objects;

import lombok.Data;
import org.jpvm.objects.pyinterface.PyArgs;

@Data
public class PyBoolObject extends PyObject implements PyArgs {
    private boolean bool;

    public PyBoolObject(boolean bool) {
        this.bool = bool;
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
