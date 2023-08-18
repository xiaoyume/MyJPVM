package org.jpvm.objects;

import lombok.Data;

@Data
public class PyBoolObject extends PyObject {
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
}
