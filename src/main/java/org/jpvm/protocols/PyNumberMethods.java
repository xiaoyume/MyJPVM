package org.jpvm.protocols;

import org.jpvm.internal.Global;
import org.jpvm.objects.PyObject;

import java.util.zip.Deflater;

public interface PyNumberMethods {
    default PyObject add(PyObject o){
        return Global.notImplemented;
    }
    default PyObject sub(PyObject o){
        return Global.notImplemented;
    }

    default PyObject mul(Object o){
        return Global.notImplemented;
    }

    default PyObject mod(PyObject o){
        return Global.notImplemented;
    }

    default PyObject divmod(PyObject o){
        return Global.notImplemented;
    }

    default PyObject pow(PyObject o){
        return Global.notImplemented;
    }

    default PyObject neg(PyObject o){
        return Global.notImplemented;
    }
    default PyObject pos(PyObject o){
        return Global.notImplemented;
    }
    default PyObject abs(PyObject o){
        return Global.notImplemented;
    }
    default PyObject bool(PyObject o){
        return Global.notImplemented;
    }
    default PyObject invert(PyObject o){
        return Global.notImplemented;
    }
    default PyObject lshift(PyObject o){
        return Global.notImplemented;
    }
    default PyObject rshift(PyObject o){
        return Global.notImplemented;
    }
    default PyObject and(PyObject o){
        return Global.notImplemented;
    }
    default PyObject xor(PyObject o){
        return Global.notImplemented;
    }
    default PyObject or(PyObject o) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_int
     */
    default PyObject nbInt(PyObject o) {
        return Global.notImplemented;
    }
    /**
     * implementation of corresponding cpython nb_reserved
     */
    default PyObject reserved(PyObject o) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_float
     */
    default PyObject nbFloat(PyObject l) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_inplace_add
     */
    default PyObject inplaceAdd(PyObject o) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_inplace_subtract
     */
    default PyObject inplaceSub(PyObject o) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_inplace_multiply
     */
    default PyObject inplaceMul(PyObject o) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_inplace_remainder
     */
    default PyObject inplaceMod(PyObject o) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_inplace_power
     */
    default PyObject inplacePow(PyObject o) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_inplace_lshift
     */
    default PyObject inplaceLshift(PyObject o) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_inplace_rshift
     */
    default PyObject inplaceRshift(PyObject o) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_inplace_and
     */
    default PyObject inplaceAnd(PyObject o) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_inplace_xor
     */
    default PyObject inplaceXor(PyObject o) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_inplace_or
     */
    default PyObject inplaceOr(PyObject o) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_floor_divide
     */
    default PyObject floorDiv(PyObject o) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_true_divide
     */
    default PyObject trueDiv(PyObject o) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_inplace_floor_divide
     */
    default PyObject inplaceFloorDiv(PyObject o) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_inplace_true_divide
     */
    default PyObject inplaceTrueDiv(PyObject o) {
        return Global.notImplemented;
    }

    /**
     * implementation of corresponding cpython nb_index
     */
    default PyObject index(PyObject o) {
        return Global.notImplemented;
    }
}
