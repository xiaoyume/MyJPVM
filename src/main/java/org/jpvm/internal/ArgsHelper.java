package org.jpvm.internal;

import org.jpvm.objects.PyDictObject;
import org.jpvm.objects.PyFunctionObject;
import org.jpvm.objects.PyObject;
import org.jpvm.objects.PyTupleObject;
import org.jpvm.pycParser.CodeObject;

public class ArgsHelper {
    private final PyDictObject args;
    private final PyFunctionObject func;

    public ArgsHelper(PyFunctionObject functionObject){
        func = functionObject;
        this.args = new PyDictObject();
        parse(functionObject);
    }

    private void parse(PyFunctionObject functionObject){
        CodeObject code = (CodeObject) functionObject.getFuncCode();
        PyTupleObject varNames = (PyTupleObject) code.getCoVarnames();
        PyTupleObject defaults = (PyTupleObject) functionObject.getFuncDefaults();
        PyDictObject kwDefaults = (PyDictObject) functionObject.getFuncKwDefaults();
        if(null != defaults){
            for(int i = 0; i < defaults.size(); i++){
                PyObject object = defaults.get(i);
                this.args.put(
                        varNames.get(varNames.size() - defaults.size() + i), object
                );
            }
        }
        if(null != kwDefaults){
            args.addAll(kwDefaults);
        }
    }

    public PyDictObject doParse(boolean isKw, PyObject... args){
        PyDictObject ans = new PyDictObject();
        ans.addAll(this.args);
        CodeObject code = (CodeObject) func.getFuncCode();
        PyTupleObject varNames = (PyTupleObject) code.getCoVarnames();
        if(isKw){
            PyTupleObject arg = (PyTupleObject) args[0];
            for(int i = 0; i < arg.size(); i++){
                ans.put(arg.get(i), args[i+1]);
            }
            for(int i = arg.size() + 1; i < args.length; i++){
                ans.put(varNames.get(i - arg.size() - 1), args[i]);
            }
        }else{
            for(int i = 0; i < args.length; i++){
                ans.put(varNames.get(i), args[i]);
            }
        }
        return ans;
    }
}
