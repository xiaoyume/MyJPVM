package org.jpvm.internal;

import org.jpvm.errors.PyNotImplemented;
import org.jpvm.objects.PyLongObject;
import org.jpvm.objects.PyObject;
import org.jpvm.protocols.PyNumberMethods;

public class NumberHelper {
    /**
     * 把一个对象转为Long类型
     * @param o
     * @return
     */
    public static Long transformPyObject2Long (PyObject o) {
        if (o instanceof PyNumberMethods n) {//这个对象必须是实现了number接口的类的实例
            PyObject object;
            try {
                object = n.nbInt();
                if (object instanceof PyLongObject d)
                    return d.getData();
            } catch (PyNotImplemented ignored) {
            }
            try {
                object = n.index();
                if (object instanceof PyLongObject d)
                    return d.getData();
            } catch (PyNotImplemented ignored) {
            }
        }
        return null;
    }
}
