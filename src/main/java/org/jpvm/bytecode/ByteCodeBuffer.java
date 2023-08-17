package org.jpvm.bytecode;

import lombok.Data;
import org.jpvm.objects.PyByteObject;
import org.jpvm.pycParser.CodeObject;

import java.util.Iterator;
@Data
public class ByteCodeBuffer {
    private final CodeObject codeObject;
    private final PyByteObject code;
    private final byte[] codeBuf;

    public ByteCodeBuffer(CodeObject codeObject) {
        this.codeObject = codeObject;
        code = (PyByteObject) codeObject.getCoCode();
        codeBuf = code.getData();
    }

    public Iterator<Instruction> iterator() {
        return new Itr();
    }

    public class Itr implements Iterator<Instruction> {

        int cursor;

        public Itr() {
            cursor = 0;
        }

        public Itr(int cusor) {
            this.cursor = cusor;
        }

        @Override
        public boolean hasNext() {
            return cursor < codeBuf.length;
        }

        @Override
        public Instruction next() {
            if(!hasNext()){
                throw new UnsupportedOperationException("no more elements");
            }
            Instruction instruction = new Instruction();
            int opcode;
            int oparg = 0;//没有参数
            instruction.setPos(cursor);
            int extendedArg = 0;
            do {
                //一条指令是占两个字节，opcode+oparg
                opcode = codeBuf[cursor++] & 0xff;
                if(opcode == 0){
                    break;
                }
                if(opcode >= OpMap.HAVE_ARGUMENT){//有参数的操作码
                    oparg = (codeBuf[cursor++] & 0xff) | extendedArg;
                    if (opcode == OpMap.EXTENDED_ARG) {//如果是拓展参数，参数超界限
                        /**
                         *
                         */
                        extendedArg = oparg << 8;
                    }else{
                        extendedArg = 0;
                    }
                }
            }while(opcode == OpMap.EXTENDED_ARG);
            instruction.setOpcode(opcode);
            instruction.setOpName(OpMap.instructions.get(opcode));
            instruction.setOparg(oparg);

            return instruction;
        }

        public boolean resetCursor(int pos){
            cursor = pos;
            return true;
        }
    }

}
