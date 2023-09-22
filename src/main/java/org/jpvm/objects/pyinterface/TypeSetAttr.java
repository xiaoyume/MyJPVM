package org.jpvm.objects.pyinterface;

import org.jpvm.errors.PyNotImplemented;
import org.jpvm.internal.Global;
import org.jpvm.objects.PyObject;

public interface TypeSetAttr {
    default PyObject setAttr(PyObject key, PyObject val) throws PyNotImplemented {
        throw new PyNotImplemented("setAttr is not implemented");
    }
}
