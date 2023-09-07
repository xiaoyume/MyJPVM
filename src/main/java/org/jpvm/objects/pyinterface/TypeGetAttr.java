package org.jpvm.objects.pyinterface;

import org.jpvm.internal.Global;
import org.jpvm.objects.PyObject;

public interface TypeGetAttr {
    default PyObject getAttr(PyObject key){
        return Global.notImplemented;
    }
}
