package org.jpvm.objects.pyinterface;

import org.jpvm.errors.PyNotImplemented;
import org.jpvm.objects.PyObject;

public interface TypeCall {
    default PyObject call() throws PyNotImplemented {
        throw  new PyNotImplemented("typecall is not implemented");
    }
}
