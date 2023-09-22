package org.jpvm.objects.pyinterface;

import org.jpvm.errors.PyNotImplemented;
import org.jpvm.internal.Global;
import org.jpvm.objects.PyObject;

public interface TypeDescriptor {
    default PyObject descrGet(PyObject obj, PyObject cls) throws PyNotImplemented {
        throw new PyNotImplemented("descrGet is not implemented");
    }
    default PyObject descrSet(PyObject obj, PyObject val) throws PyNotImplemented {
        throw new PyNotImplemented("descrSet is not implemented");
    }
}
