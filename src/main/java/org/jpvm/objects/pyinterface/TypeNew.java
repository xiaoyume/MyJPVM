package org.jpvm.objects.pyinterface;

import org.jpvm.internal.Global;
import org.jpvm.objects.PyObject;

public interface TypeNew {
    default PyObject allocate(){
        return Global.notImplemented;
    }
}
