package org.jpvm.pvm;

import org.jpvm.pycParser.CodeObject;

public class EvaluationLoop {
    private final PyFrameObject frameObject;
    private final CodeObject codeObject;

    public EvaluationLoop(CodeObject codeObject) {
        this.codeObject = codeObject;
        frameObject = new PyFrameObject(
                null,
                codeObject,
                null,
                null,
                null
        );
    }
}
