package org.jpvm.protocols;

import org.jpvm.internal.Global;
import org.jpvm.objects.PyObject;

public interface PyMappingMethods {

    default PyObject mpLength(PyObject o){
        return Global.notImplemented;
    }

    default PyObject mpSubscript(PyObject o){
        return Global.notImplemented;
    }

    default PyObject mpAssSubscript(PyObject o){
        return Global.notImplemented;
    }
}
