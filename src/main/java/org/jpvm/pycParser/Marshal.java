package org.jpvm.pycParser;

import org.jpvm.objects.PyListObject;

/**
 * 解析字节码
 */
public class Marshal {
    public static int CO_OPTIMIZED = 0x0001;
    public static int CO_NEWLOCALS = 0x0002;
    public static int CO_VARARGS = 0x0004;
    public static int CO_VARKEYWORDS = 0x0008;
    public static int CO_NESTED = 0x0010;
    public static int CO_GENERATOR = 0x0020;

    private PyListObject refs;
    private int flag;


}
