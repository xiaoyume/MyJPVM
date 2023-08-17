package org.jpvm.module;

import lombok.Data;
import org.jpvm.bytecode.ByteCodeBuffer;
import org.jpvm.bytecode.Instruction;
import org.jpvm.objects.PyObject;
import org.jpvm.objects.PyTupleObject;
import org.jpvm.pycParser.CodeObject;

import java.util.Iterator;

@Data
public class Disassember {

    private CodeObject codeObject;
    private ByteCodeBuffer buffer;
    public Disassember(CodeObject codeObject){
        this.codeObject = codeObject;
        buffer = new ByteCodeBuffer(codeObject);
    }

    /**
     * 字节码反编译
     */
    public void dis(){
        Iterator<Instruction> iterator = buffer.iterator();
        StringBuilder builder = new StringBuilder();
        while(iterator.hasNext()){
            builder.delete(0, builder.length());
            Instruction ins = iterator.next();
            builder.append(ins.getPos());
            builder.append(" ");
            builder.append(ins.getOpName());
            builder.append("\t");
            builder.append(ins.getOparg());
            switch (ins.getOpName()){
                case LOAD_CONST -> {
                    var coConsts = (PyTupleObject)codeObject.getCoConsts();
                    builder.append("(").append(coConsts.get(ins.getOparg())).append(")");
                }
                case STORE_NAME -> {
                    var coVarnames = (PyTupleObject)codeObject.getCoVarnames();
                    builder.append("(").append(coVarnames.get(ins.getOparg())).append(")");
                }
            }
            System.out.println(builder);
        }
    }

}
