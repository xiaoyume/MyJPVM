package org.jpvm.objects;

import lombok.Data;
import org.jpvm.objects.types.PyBoolType;
import org.jpvm.objects.types.PyFrameType;
import org.jpvm.pycParser.CodeObject;
@Data
public class PyFrameObject extends PyObject{
    public static PyObject type = new PyFrameType();
    private final PyFrameObject back;
    private final CodeObject code;
    private PyObject builtins;
    private PyObject gloabals;
    private PyObject locals;

    private final PyObject[] stack;
    private int used;
    private boolean isExecuting;
    public PyFrameObject(PyFrameObject back,
                         CodeObject code,
                         PyObject builtins,
                         PyObject gloabals,
                         PyObject locals){
        assert code != null;
        this.back = back;
        this.code = code;
        this.builtins = builtins;
        this.gloabals = gloabals;
        this.locals = locals;
        this.stack = new PyObject[code.getCoStacksize()];
    }

    @Override
    public Object getType() {
        return type;
    }
    public static PyBoolObject check(PyObject o){
        return new PyBoolObject(o == type);
    }
}
