package org.jpvm.module;

import org.jpvm.objects.*;
import org.jpvm.pycParser.CodeObject;
import org.jpvm.python.BuiltIn;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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

    public Marshal() {
        refs = new PyListObject();
    }

    /**
     * 引用追踪，将对象加入到引用列表中，其它对象通过引用指向相同的对象。
     * flag 不为0且与flagref位与操作，提取出标志位，判断是否需要引用
     *
     * @param o
     * @return
     */
    public PyObject RREF(PyObject o) {
        if (flag != 0) {
            assert (flag & TYPE.FLAG_REF) != 0;
            if (o == null) {
                return BuiltIn.NULL;
            }
            refs.app1(o);
        }
        return o;
    }

    public int RREFReserve() {
        if (flag != 0) {
            int size = refs.size();
            refs.app1(BuiltIn.None);
            return size;
        }
        return 0;
    }

    public void RREFInsert(int idx, PyObject o) {
        if (flag != 0) {
            refs.set(idx, o);
        }
    }


    /**
     * 重写一个加载对象方法，使用fileinputstream做参数
     *
     * @param stream
     * @return
     * @throws IOException
     */
    public PyObject loadPyObject(FileInputStream stream) throws IOException, IllegalAccessException {
        int size = stream.available();
        byte[] bytes = new byte[size];
        var s = stream.read(bytes);
        //小端存储
        return loadPyObject(ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN));
    }

    public PyObject loadPyObject(ByteBuffer buffer) throws IllegalAccessException {
        int code = buffer.get() & 0xff;
        int type = code & (~TYPE.FLAG_REF);
        flag = code & TYPE.FLAG_REF;
        return switch (type) {
            case TYPE.TYPE_NULL -> BuiltIn.NULL;
            case TYPE.TYPE_NONE -> BuiltIn.None;
            case TYPE.TYPE_STOPITER -> BuiltIn.PyExcStopIteration;
            case TYPE.TYPE_ELLIPSIS -> BuiltIn.ELLIPSIS;
            case TYPE.TYPE_FALSE -> BuiltIn.False;
            case TYPE.TYPE_TRUE -> BuiltIn.True;
            case TYPE.TYPE_INT -> loadInt(buffer);
            case TYPE.TYPE_INT64 -> loadInt64(buffer);
            case TYPE.TYPE_SMALL_TUPLE -> loadSmallTuple(buffer);
            case TYPE.TYPE_TUPLE -> loadTuple(buffer);
            case TYPE.TYPE_CODE -> loadCodeObject(buffer);
            case TYPE.TYPE_LIST -> loadList(buffer);
            case TYPE.TYPE_LONG -> loadLong(buffer);
            case TYPE.TYPE_FLOAT -> loadFloat(buffer);
            case TYPE.TYPE_BINARY_FLOAT -> loadBinaryFloat(buffer);
            case TYPE.TYPE_COMPLEX -> throw new RuntimeException("unsupported TYPE_COMPLEX");
            case TYPE.TYPE_BINARY_COMPLEX -> loadComplex(buffer);
            case TYPE.TYPE_STRING -> loadStringObject(buffer);
            case TYPE.TYPE_ASCII_INTERNED, TYPE.TYPE_INTERNED -> loadASCII(buffer, true, false);
            case TYPE.TYPE_ASCII, TYPE.TYPE_UNICODE -> loadASCII(buffer, false, false);
            case TYPE.TYPE_SHORT_ASCII -> loadASCII(buffer, false, true);
            case TYPE.TYPE_SHORT_ASCII_INTERNED -> loadASCII(buffer, true, true);
            case TYPE.TYPE_DICT -> loadDict(buffer);
            case TYPE.TYPE_SET -> loadSet(buffer, false);
            case TYPE.TYPE_FROZENSET -> loadSet(buffer, true);
            case TYPE.TYPE_REF -> loadRef(buffer);
            default -> throw new IllegalAccessException("Unexpected value: " + type);
        };
    }

    private PyObject loadRef(ByteBuffer buffer) {
        int i = buffer.getInt();
        return refs.get(i);
    }

    private PySetObject loadSet(ByteBuffer buffer, boolean isFrozen) throws IllegalAccessException {
        int s = buffer.getInt();
        if (isFrozen && s == 0) {
            RREF(BuiltIn.FROZENSET);
            return BuiltIn.FROZENSET;
        } else {
            int idx = 0;
            PySetObject setObject = new PySetObject(isFrozen);
            if (!isFrozen) {
                RREF(setObject);
            } else {
                idx = RREFReserve();
            }
            for (int i = 0; i < s; i++) {
                setObject.put(loadPyObject(buffer));
            }
            if (isFrozen) {
                RREFInsert(idx, setObject);
            }
            return setObject;
        }
    }

    private PyDictObject loadDict(ByteBuffer buffer) throws IllegalAccessException {
        PyDictObject pyDictObject = new PyDictObject();
        RREF(pyDictObject);
        for (; ; ) {
            PyObject key = loadPyObject(buffer);
            if (key == BuiltIn.NULL) {
                break;
            }
            PyObject val = loadPyObject(buffer);
            if (val == BuiltIn.NULL) {
                break;
            }
            pyDictObject.put(key, val);
        }
        RREF(pyDictObject);
        return pyDictObject;
    }

    private PyUnicodeObject loadASCII(ByteBuffer buffer, boolean interned, boolean isShort) {
        int size;
        if (isShort) {
            size = buffer.get() & 0xff;
        } else {
            size = buffer.getInt();
        }
        byte[] bytes = new byte[size];
        buffer.get(bytes);
        PyUnicodeObject object = new PyUnicodeObject(bytes);
        RREF(object);
        return object;
    }

    private PyByteObject loadStringObject(ByteBuffer buffer) {
        int s = buffer.getInt();
        byte[] bytes = new byte[s];
        buffer.get(bytes);
        PyByteObject object = new PyByteObject(bytes);
        RREF(object);
        return object;
    }

    private PyComplexObject loadComplex(ByteBuffer buffer) {
        PyComplexObject object = new PyComplexObject(loadBinaryFloat(buffer), loadBinaryFloat(buffer));
        RREF(object);
        return object;
    }

    private PyFloatObject loadBinaryFloat(ByteBuffer buffer) {
        PyFloatObject floatObject = new PyFloatObject(buffer.getDouble());
        RREF(floatObject);
        return floatObject;
    }

    private PyFloatObject loadFloat(ByteBuffer buffer) {
        int s = buffer.get() & 0xff;
        if (s == 4) {
            PyFloatObject floatObject = new PyFloatObject(buffer.getFloat());
            RREF(floatObject);
            return floatObject;
        } else if (s == 8) {
            PyFloatObject floatObject = new PyFloatObject(buffer.getDouble());
            RREF(floatObject);
            return floatObject;
        }
        throw new RuntimeException("can not convert " + s + " bytes into a pyFloat");
    }

    private PyObject loadLong(ByteBuffer buffer) {
        throw new RuntimeException("do not support big int");
    }

    private PyListObject loadList(ByteBuffer buffer) throws IllegalAccessException {
        int s = buffer.getInt();
        PyListObject pyListObject = new PyListObject(s);
        RREF(pyListObject);
        for (int i = 0; i < s; i++) {
            pyListObject.app1(loadPyObject(buffer));
        }
        RREF(pyListObject);
        return pyListObject;
    }

    public CodeObject loadCodeObject(byte[] bytes) throws IllegalAccessException {
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        return (CodeObject) loadPyObject(buffer);
    }

    public CodeObject loadCodeObject(FileInputStream stream) throws IOException, IllegalAccessException {
        var size = stream.available();
        byte[] bytes = new byte[size];
        var s = stream.read(bytes);
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        return (CodeObject) loadPyObject(buffer);
    }

    public CodeObject loadCodeObject(ByteBuffer buffer) throws IllegalAccessException {
        CodeObject codeObject = new CodeObject();
        int idx = RREFReserve();//先保存一个空值引用占位，后面会把codeobj对象存储得到这个位置
        codeObject.setCoArgcount(buffer.getInt());
        codeObject.setCoPosOnlyArgcount(buffer.getInt());
        codeObject.setCoKwOnlyArgcount(buffer.getInt());
        codeObject.setCoNLocals(buffer.getInt());
        codeObject.setCoStacksize(buffer.getInt());
        codeObject.setCoFlags(buffer.getInt());

        codeObject.setCoCode(loadPyObject(buffer));
        codeObject.setCoConsts(loadPyObject(buffer));
        codeObject.setCoNames(loadPyObject(buffer));
        codeObject.setCoVarnames(loadPyObject(buffer));
        codeObject.setCoFreevars(loadPyObject(buffer));
        codeObject.setCoCellvars(loadPyObject(buffer));
        codeObject.setCoFilename(loadPyObject(buffer));
        codeObject.setCoName(loadPyObject(buffer));
        codeObject.setCoFirstLineNo(buffer.getInt());
        codeObject.setCoLnotab(loadPyObject(buffer));
        RREFInsert(idx, codeObject);//
        return codeObject;
    }

    private PyTupleObject loadTuple(ByteBuffer buffer) throws IllegalAccessException {
        int s = buffer.getInt();
        PyTupleObject tupleObject = new PyTupleObject(s);
        RREF(tupleObject);
        for (int i = 0; i < s; i++) {
            tupleObject.set(i, loadPyObject(buffer));
        }
        RREF(tupleObject);
        return tupleObject;
    }

    private PyTupleObject loadSmallTuple(ByteBuffer buffer) throws IllegalAccessException {
        int s = buffer.get() & 0xff;
        PyTupleObject tupleObject = new PyTupleObject(s);
        for (int i = 0; i < s; i++) {
            tupleObject.set(i, loadPyObject(buffer));
        }
        RREF(tupleObject);
        return tupleObject;
    }

    private PyLongObject loadInt64(ByteBuffer buffer) {
        PyLongObject pyLongObject = new PyLongObject(buffer.getLong());
        RREF(pyLongObject);
        return pyLongObject;
    }

    private PyLongObject loadInt(ByteBuffer buffer) {
        PyLongObject longObject = new PyLongObject(buffer.getInt());
        RREF(longObject);
        return longObject;
    }


}
