package org.jpvm.pvm;

import lombok.Data;
import org.jpvm.objects.PyObject;
import org.jpvm.pycParser.CodeObject;
@Data
public class PyFrameObject {
    private final PyFrameObject back;
    private final CodeObject code;
    private final PyObject builtins;
    private final PyObject globals;
    private final PyObject locals;
    private PyObject[] stack;
    private int used;
    private boolean isExcuting;

    public PyFrameObject(PyFrameObject back, CodeObject code, PyObject builtins, PyObject globals, PyObject local) {
        this.back = back;
        this.code = code;
        this.builtins = builtins;
        this.globals = globals;
        this.locals = local;
        this.stack = new PyObject[code.getCoStacksize()];
    }
}
