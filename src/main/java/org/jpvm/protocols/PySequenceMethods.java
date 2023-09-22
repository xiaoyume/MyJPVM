package org.jpvm.protocols;

import org.jpvm.errors.PyNotImplemented;
import org.jpvm.errors.PyTypeNotMatch;
import org.jpvm.internal.Global;
import org.jpvm.objects.PyObject;

public interface PySequenceMethods {
    default PyObject sqLength(PyObject o) throws PyNotImplemented {
        throw new PyNotImplemented("sqLength is not implemeted");
    }
    default PyObject sqConcat(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("sqConcat is not implemeted");
    }
    default PyObject sqRepeat(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("sqRepeat is not implemeted");
    }

    default PyObject sqItem(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("sqItem is not implemeted");
    }
    default PyObject sqAssItem(PyObject key, PyObject val) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("sqAssItem is not implemeted");
    }
    default PyObject sqContain(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("sqContain is not implemeted");
    }
    default PyObject sqInplaceConcat(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("sqInplaceConcat is not implemeted");
    }

    default PyObject sqInplaceRepeat(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("sqInplaceRepeat is not implemeted");
    }
}
