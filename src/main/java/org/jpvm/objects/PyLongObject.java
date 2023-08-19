package org.jpvm.objects;

import lombok.Data;
import org.jpvm.objects.pyinterface.PyArgs;

@Data
public class PyLongObject extends PyObject implements PyArgs {
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
    public Object toJavaType() {
        return data;
    }
}
