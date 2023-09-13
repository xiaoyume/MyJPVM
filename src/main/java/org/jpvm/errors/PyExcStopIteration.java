package org.jpvm.errors;

import org.jpvm.objects.PyUnicodeObject;

public class PyExcStopIteration extends PyException {

    public PyExcStopIteration(String msg) {
        super(msg);
    }

    @Override
    public PyUnicodeObject log() {
        return new PyUnicodeObject("PyExcStopIteration:" + getMessage());
    }
}
