package org.jpvm.objects.pyinterface;

import org.jpvm.internal.Global;
import org.jpvm.objects.PyObject;

public interface TypeSetAttr {
    default PyObject setAttr(PyObject key, PyObject val){
        return Global.notImplemented;
    }
}
