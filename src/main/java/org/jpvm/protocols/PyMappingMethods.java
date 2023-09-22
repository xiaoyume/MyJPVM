package org.jpvm.protocols;

import org.jpvm.errors.PyIndexOutOfBound;
import org.jpvm.errors.PyKeyError;
import org.jpvm.errors.PyNotImplemented;
import org.jpvm.errors.PyTypeNotMatch;
import org.jpvm.internal.Global;
import org.jpvm.objects.PyObject;

public interface PyMappingMethods {

    default PyObject mpLength(PyObject o) throws PyNotImplemented {
        throw new PyNotImplemented("mplength is not implemented");
    }

    default PyObject mpSubscript(PyObject o) throws PyNotImplemented, PyIndexOutOfBound,
            PyKeyError, PyTypeNotMatch {
        throw new PyNotImplemented("mpSubscript is not implemented");
    }

    default PyObject mpAssSubscript(PyObject key, PyObject val) throws PyNotImplemented,
            PyKeyError{
        throw new PyNotImplemented("mpAssscript is not implemented");
    }
}
