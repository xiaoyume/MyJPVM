package org.jpvm.objects.pyinterface;

import org.jpvm.errors.PyNotImplemented;
import org.jpvm.internal.Global;
import org.jpvm.objects.PyObject;

public interface TypeNew {
    default PyObject allocate() throws PyNotImplemented {
        throw new PyNotImplemented("allocate is not implemented");
    }
}
