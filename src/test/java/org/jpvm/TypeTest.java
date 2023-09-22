package org.jpvm;

import org.jpvm.objects.PyLongObject;
import org.jpvm.objects.types.PyLongType;
import org.junit.Test;

public class TypeTest {
    @Test
    public void testPyLong(){
        PyLongObject longObject = new PyLongObject(1);
        System.out.println(longObject.getType());
        System.out.println(longObject.getTypeName());
        assert longObject.getTypeName().getData().equals("int");
    }
}
