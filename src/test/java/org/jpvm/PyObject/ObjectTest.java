package org.jpvm.PyObject;

import org.jpvm.objects.PyFloatObject;
import org.jpvm.objects.PyListObject;
import org.jpvm.objects.PyLongObject;
import org.junit.Test;

public class ObjectTest {
    @Test
    public void PyLongObjectTest(){
        PyLongObject pyLongObject = new PyLongObject(10);
        System.out.println(pyLongObject);
    }
    @Test
    public void PyListObjTest(){
        PyListObject pyListObject = new PyListObject();
        pyListObject.app1(new PyFloatObject(10));
        pyListObject.app1(new PyLongObject(20));
        System.out.println(pyListObject);
    }

}
