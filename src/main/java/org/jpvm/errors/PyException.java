package org.jpvm.errors;

import lombok.Data;
import org.jpvm.objects.PyObject;
import org.jpvm.objects.PyUnicodeObject;

@Data
public class PyException extends Exception implements PyExcLogging {
    private String message;

    public PyException(String msg) {
        this.message = msg;
    }


    @Override
    public PyUnicodeObject log() {
        return new PyUnicodeObject(message);
    }


}
