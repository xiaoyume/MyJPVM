package org.jpvm;

import org.jpvm.objects.PyUnicodeObject;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class Reflection {
    @Test
    public void testreflect() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        PyUnicodeObject unicodeObject = new PyUnicodeObject("hello".getBytes(StandardCharsets.UTF_8));
        Class<? extends PyUnicodeObject> clz = unicodeObject.getClass();
        Method getdata = clz.getMethod("getData");
        System.out.println(getdata);
        Object invoke = getdata.invoke(unicodeObject);
        System.out.println(invoke);
    }



}
