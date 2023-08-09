package org.jpvm.errors;

public class PyExcStopIteration extends PyException{

    public PyExcStopIteration(String msg) {
        super(msg);
    }

    @Override
    public void log() {
        System.err.println("PyExcStopIteration:" + getMessage());
    }
}
