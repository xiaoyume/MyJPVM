package org.jpvm.objects.pyinterface;

import org.jpvm.internal.Global;
import org.jpvm.objects.PyObject;

public interface TypeCall {
    default PyObject call(){
        return Global.notImplemented;
    }
}
