package org.jpvm.protocols;

import org.jpvm.internal.Global;
import org.jpvm.objects.PyObject;

public interface PySequenceMethods {
    default PyObject sqLength(PyObject o){
        return Global.notImplemented;
    }
    default PyObject sqConcat(PyObject o){
        return Global.notImplemented;
    }
    default PyObject sqRepeat(PyObject o){
        return Global.notImplemented;
    }

    default PyObject sqItem(PyObject o){
        return Global.notImplemented;
    }
    default PyObject sqAssItem(PyObject o){
        return Global.notImplemented;
    }
    default PyObject sqContain(PyObject o){
        return Global.notImplemented;
    }
    default PyObject sqInplaceContain(PyObject o){
        return Global.notImplemented;
    }

    default PyObject sqInplaceRepeat(PyObject o){
        return Global.notImplemented;
    }
}
