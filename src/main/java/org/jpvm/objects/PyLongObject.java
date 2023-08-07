package org.jpvm.objects;

import lombok.Data;

@Data
public class PyLongObject extends PyObject{
    private long data;

    public PyLongObject(long data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }

    public PyLongObject(int data){
        this.data = data;
    }
}
