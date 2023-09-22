package org.jpvm.objects.pyinterface;

import org.jpvm.errors.PyIndexOutOfBound;
import org.jpvm.errors.PyNotImplemented;
import org.jpvm.internal.Global;
import org.jpvm.objects.PyObject;

public interface TypeDoIterate {
    default PyObject next() throws PyNotImplemented {
        throw new PyNotImplemented("next is not implemented");
    }

    default PyObject get(int idx) throws PyNotImplemented, PyIndexOutOfBound {
        throw new PyNotImplemented("get is not implemented");
    }

    default int size(){
        return -1;
    }
}
