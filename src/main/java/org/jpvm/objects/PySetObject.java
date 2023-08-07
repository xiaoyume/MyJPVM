package org.jpvm.objects;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class PySetObject extends PyObject{
    private Set<PyObject> set;
    private boolean isFrozen;

    public PySetObject(boolean isFrozen) {
        this.isFrozen = isFrozen;
    }
    public PySetObject(){
        this.set = new HashSet<>();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (PyObject obj : set){
            builder.append(obj.toString());
            builder.append(", ");
        }
        if(builder.length() > 2){
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append("}");
        return builder.toString();
    }

    public void put(PyObject key){
        set.add(key);
    }
    public boolean contains(PyObject key){
        return set.contains(key);
    }

}
