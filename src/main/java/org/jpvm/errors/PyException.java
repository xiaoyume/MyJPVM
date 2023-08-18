package org.jpvm.errors;

import lombok.Data;
import org.jpvm.objects.PyObject;

@Data
public class PyException extends PyObject implements PyExcLogging {
    private String message;

    public PyException(String msg) {
        this.message = msg;
    }


    @Override
    public void log() {
        System.err.println(message);
    }


}
