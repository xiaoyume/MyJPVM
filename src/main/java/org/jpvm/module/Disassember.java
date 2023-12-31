package org.jpvm.module;

import lombok.Data;
import org.jpvm.bytecode.ByteCodeBuffer;
import org.jpvm.bytecode.Instruction;
import org.jpvm.bytecode.OpMap;
import org.jpvm.objects.PyTupleObject;
import org.jpvm.pycParser.CodeObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

@Data
public class Disassember {

    private final CodeObject codeObject;
    private final ByteCodeBuffer buffer;

    public Disassember(CodeObject codeObject) {
        this.codeObject = codeObject;
        buffer = new ByteCodeBuffer(codeObject);
    }

    /**
     * 字节码反编译
     */
    public void dis() {
        HashSet<Object> enterPoint = new HashSet<>();
        Iterator<Instruction> iterator = buffer.iterator();
        for (Instruction ins : buffer) {
            if (OpMap.instructions.containsKey(ins.getOpcode())) {
                if (ins.getOpName().toString().toLowerCase().contains("jump")) {
                    enterPoint.add(ins.getOparg());
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        while (iterator.hasNext()) {
            Instruction ins = iterator.next();
            if (!OpMap.instructions.containsKey(ins.getOpcode())) {
                continue;
            }
            builder.delete(0, builder.length());
            if (enterPoint.contains(ins.getPos())) {
                builder.append(" >>");
            }
            builder.append("\t");
            builder.append(String.format("%4d", ins.getPos()));
            builder.append(" ");
            builder.append(String.format("%-15s", ins.getOpName()));
            builder.append("\t");
            builder.append(String.format("%3d", ins.getOparg()));
            switch (ins.getOpName()) {
                case LOAD_CONST -> {
                    var coConsts = (PyTupleObject) codeObject.getCoConsts();
                    if (coConsts.get(ins.getOparg()) instanceof CodeObject cb) {
                        builder.append(" <CodeObject ").append(cb.getCoName())
                                .append(" @0x")
                                .append(Integer.toHexString(System.identityHashCode(cb)))
                                .append(" ")
                                .append(cb.getCoFilename()).append(", line ")
                                .append(cb.getCoFirstLineNo()).append(" >");
                    } else {
                        builder.append("(").append(coConsts.get(ins.getOparg())).append(")");
                    }

                }
                case STORE_NAME, LOAD_NAME, IMPORT_NAME -> {
                    var coNames = (PyTupleObject) codeObject.getCoNames();
                    builder.append("(").append(coNames.get(ins.getOparg())).append(")");
                }
            }
            System.out.println(builder);
        }
    }

}
