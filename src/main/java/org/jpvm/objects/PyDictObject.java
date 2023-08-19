package org.jpvm.objects;

import lombok.Data;
import org.jpvm.objects.pyinterface.PyArgs;

import java.util.HashMap;
import java.util.Map;
@Data
public class PyDictObject extends PyObject implements PyArgs {
    private final Map<PyObject, PyObject> map;
    public PyDictObject(){
        this.map = new HashMap<>();
    }
    public PyObject put(PyObject key, PyObject val){
        return map.put(key, val);
    }

    /**
     * 输出形式{[key=, val=], []}
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        map.forEach((x, y) -> {
            builder.append(x.toString());
            builder.append(": ");
            builder.append(y.toString());
            builder.append(", ");
        });
        if(builder.length() > 2){
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append("}");
        return builder.toString();
    }

    @Override
    public Object toJavaType() {
        return map;
    }
}
