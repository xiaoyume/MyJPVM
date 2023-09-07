package org.jpvm.objects;

import lombok.Data;

@Data
public class PyFunctionObject extends PyObject{
    private PyObject funcCode;
    private PyObject funcGlobals;
    private PyObject funcLocals;
    private PyObject funcDefaults;
    private PyObject funcKwDefaults;
    private PyObject funcDoc;
    private PyObject funcName;
    private PyObject funcDict;
    private PyObject funcQualName;

    public PyFunctionObject(PyObject funcCode, PyObject funcGlobals, PyObject funcLocals, PyObject funcDefaults, PyObject funcKwDefaults, PyObject funcDoc, PyObject funcName, PyObject funcDict, PyObject funcQualName) {
        this.funcCode = funcCode;
        this.funcGlobals = funcGlobals;
        this.funcLocals = funcLocals;
        this.funcDefaults = funcDefaults;
        this.funcKwDefaults = funcKwDefaults;
        this.funcDoc = funcDoc;
        this.funcName = funcName;
        this.funcDict = funcDict;
        this.funcQualName = funcQualName;
    }

    @Override
    public Object getType() {
        return type;
    }
    public static PyBoolObject check(PyObject o){
        return new PyBoolObject(o == type);
    }
}
