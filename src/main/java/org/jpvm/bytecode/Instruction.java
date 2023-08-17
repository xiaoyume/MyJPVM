package org.jpvm.bytecode;

import lombok.Data;

@Data
public class Instruction {
    private OpMap.OpName opName;
    private int opcode;
    private int oparg;

    public Instruction() {
    }

    /***
     * 指令创建需要3个参数
     * @param opName
     * @param opcode
     * @param oparg
     */
    public Instruction(OpMap.OpName opName, int opcode, int oparg) {
        this.opName = opName;
        this.opcode = opcode;
        this.oparg = oparg;
    }
}
