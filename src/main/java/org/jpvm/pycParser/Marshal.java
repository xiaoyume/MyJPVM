package org.jpvm.pycParser;

import org.jpvm.objects.BuiltIn;
import org.jpvm.objects.PyListObject;
import org.jpvm.objects.PyObject;

import java.nio.Buffer;
import java.nio.ByteBuffer;

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

    public static class TYPE {
        public static final byte TYPE_NULL = '0';
        public static final byte TYPE_NONE = 'N';
        public static final byte TYPE_FALSE = 'F';
        public static final byte TYPE_TRUE = 'T';
        public static final byte TYPE_STOPITER = 'S';
        public static final byte TYPE_ELLIPSIS = '.';
        public static final byte TYPE_INT = 'i';
        public static final byte TYPE_INT64 = 'I';
        public static final byte TYPE_FLOAT = 'f';
        public static final byte TYPE_BINARY_FLOAT = 'g';
        public static final byte TYPE_COMPLEX = 'x';
        public static final byte TYPE_BINARY_COMPLEX = 'y';
        public static final byte TYPE_LONG = 'l';
        public static final byte TYPE_STRING = 's';
        public static final byte TYPE_INTERNED = 't';
        public static final byte TYPE_REF = 'r';
        public static final byte TYPE_TUPLE = '(';
        public static final byte TYPE_LIST = '[';
        public static final byte TYPE_DICT = '{';
        public static final byte TYPE_CODE = 'c';
        public static final byte TYPE_UNICODE = 'u';
        public static final byte TYPE_UNKNOWN = '?';
        public static final byte TYPE_SET = '<';
        public static final byte TYPE_FROZENSET = '>';
        public static final byte FLAG_REF = (byte) 0x80;
        public static final byte TYPE_ASCII = 'a';
        public static final byte TYPE_ASCII_INTERNED = 'A';
        public static final byte TYPE_SMALL_TUPLE = ')';
        public static final byte TYPE_SHORT_ASCII = 'z';
        public static final byte TYPE_SHORT_ASCII_INTERNED = 'Z';

    }

    private PyListObject refs;
    private int flag;

    public PyObject loadPyObject(ByteBuffer buffer) throws IllegalAccessException {
        byte b = buffer.get();
        int code = b & 0xff;
        int type = code & (~TYPE.FLAG_REF);
        flag = code & TYPE.FLAG_REF;
        return switch (type){
            case TYPE.TYPE_NULL -> BuiltIn.NULL;
            case TYPE.TYPE_NONE -> BuiltIn.None;
            case TYPE.TYPE_STOPITER -> BuiltIn.PyExcStopIteration;
            case TYPE.TYPE_ELLIPSIS -> BuiltIn.ELLIPSIS;
            case TYPE.TYPE_FALSE -> BuiltIn.False;
            case TYPE.TYPE_TRUE -> BuiltIn.True;
            default -> throw new IllegalAccessException("Unexpected value: " + type);
        };
    }


}
