package org.jpvm.pvm;

import lombok.Data;
import org.jpvm.objects.PyObject;
@Data
public class ThreadState {
    private InterpreterState is;
    private PyObject builtins;
    private int recursionDepth;
    private int gilCounter;
    private PyObject curExcType;
    private PyObject curExcValue;
    private PyObject curExcTrace;

    public ThreadState(InterpreterState is, PyObject builtins) {
        this.is = is;
        this.builtins = builtins;
    }
}
