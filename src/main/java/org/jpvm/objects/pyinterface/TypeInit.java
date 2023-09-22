package org.jpvm.objects.pyinterface;

import org.jpvm.errors.PyNotImplemented;
import org.jpvm.objects.PyObject;

public interface TypeInit {
    default PyObject init() throws PyNotImplemented {
        throw new PyNotImplemented("typeInit init is not implemented");
    };


}
