package org.jpvm.objects.pyinterface;

import org.jpvm.objects.PyMethodObject;
import org.jpvm.objects.PyUnicodeObject;

public interface TypeMethod {
    PyMethodObject getMethod(PyUnicodeObject methodName);
}
