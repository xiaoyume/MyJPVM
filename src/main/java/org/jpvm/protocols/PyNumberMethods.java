package org.jpvm.protocols;

import org.jpvm.errors.PyNotImplemented;
import org.jpvm.errors.PyTypeNotMatch;
import org.jpvm.internal.Global;
import org.jpvm.objects.PyObject;

import java.util.zip.Deflater;

public interface PyNumberMethods {
    default PyObject add(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("add is not implemeted");
    }
    default PyObject sub(PyObject o) throws PyNotImplemented, PyTypeNotMatch{
        throw new PyNotImplemented("sub is not implemeted");
    }

    default PyObject mul(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("mul is not implemeted");
    }

    default PyObject mod(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("mod is not implemeted");
    }

    default PyObject divmod(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("divmod is not implemeted");
    }

    default PyObject pow(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("pow is not implemeted");
    }

    default PyObject neg() throws PyNotImplemented {
        throw new PyNotImplemented("neg is not implemeted");
    }
    default PyObject pos() throws PyNotImplemented {
        throw new PyNotImplemented("pos is not implemeted");
    }
    default PyObject abs() throws PyNotImplemented {
        throw new PyNotImplemented("abs is not implemeted");
    }
    default PyObject bool() throws PyNotImplemented {
        throw new PyNotImplemented("bool is not implemeted");
    }
    default PyObject invert() throws PyNotImplemented {
        throw new PyNotImplemented("invert is not implemeted");
    }
    default PyObject lshift(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("lshift is not implemeted");
    }
    default PyObject rshift(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("rshift is not implemeted");
    }
    default PyObject and(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("and is not implemeted");
    }
    default PyObject xor(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("xor is not implemeted");
    }
    default PyObject or(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("or is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_int
     */
    default PyObject nbInt() throws PyNotImplemented {
        throw new PyNotImplemented("nbInt is not implemeted");
    }
    /**
     * implementation of corresponding cpython nb_reserved
     */
    default PyObject reserved(PyObject o) throws PyNotImplemented {
        throw new PyNotImplemented("reserved is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_float
     */
    default PyObject nbFloat() throws PyNotImplemented {
        throw new PyNotImplemented("nbFloat is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_inplace_add
     */
    default PyObject inplaceAdd(PyObject o) throws PyNotImplemented {
        throw new PyNotImplemented("inplaceAdd is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_inplace_subtract
     */
    default PyObject inplaceSub(PyObject o) throws PyNotImplemented {
        throw new PyNotImplemented("inplaceSub is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_inplace_multiply
     */
    default PyObject inplaceMul(PyObject o) throws PyNotImplemented {
        throw new PyNotImplemented("inplaceMul is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_inplace_remainder
     */
    default PyObject inplaceMod(PyObject o) throws PyNotImplemented {
        throw new PyNotImplemented("inplaceMod is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_inplace_power
     */
    default PyObject inplacePow(PyObject o) throws PyNotImplemented {
        throw new PyNotImplemented("inplacePow is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_inplace_lshift
     */
    default PyObject inplaceLshift(PyObject o) throws PyNotImplemented {
        throw new PyNotImplemented("inplaceLshift is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_inplace_rshift
     */
    default PyObject inplaceRshift(PyObject o) throws PyNotImplemented {
        throw new PyNotImplemented("inplaceRshift is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_inplace_and
     */
    default PyObject inplaceAnd(PyObject o) throws PyNotImplemented {
        throw new PyNotImplemented("inplaceAnd is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_inplace_xor
     */
    default PyObject inplaceXor(PyObject o) throws PyNotImplemented {
        throw new PyNotImplemented("inplaceXor is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_inplace_or
     */
    default PyObject inplaceOr(PyObject o) throws PyNotImplemented {
        throw new PyNotImplemented("inplaceOr is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_floor_divide
     */
    default PyObject floorDiv(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("floorDiv is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_true_divide
     */
    default PyObject trueDiv(PyObject o) throws PyNotImplemented, PyTypeNotMatch {
        throw new PyNotImplemented("trueDiv is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_inplace_floor_divide
     */
    default PyObject inplaceFloorDiv(PyObject o) throws PyNotImplemented {
        throw new PyNotImplemented("inplaceFloorDiv is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_inplace_true_divide
     */
    default PyObject inplaceTrueDiv(PyObject o) throws PyNotImplemented {
        throw new PyNotImplemented("inplaceTrueDiv is not implemeted");
    }

    /**
     * implementation of corresponding cpython nb_index
     */
    default PyObject index() throws PyNotImplemented {
        throw new PyNotImplemented("index is not implemeted");
    }
}
