package org.jpvm.objects.pyinterface;

import org.jpvm.errors.PyNotImplemented;
import org.jpvm.internal.Global;
import org.jpvm.objects.PyObject;

public interface TypeGetAttr {
    default PyObject getAttr(PyObject key) throws PyNotImplemented {
        throw new PyNotImplemented("getAttr is not implemented");
    }
}
