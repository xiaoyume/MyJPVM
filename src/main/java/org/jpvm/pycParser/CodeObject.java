package org.jpvm.pycParser;

import lombok.Data;
import org.jpvm.objects.PyObject;

@Data
public class CodeObject extends PyObject {
    private int coArgcount;//argument
    private int coKwOnlyArgcount;//keyword args
    private int coPosOnlyArgcount;
    private int coNLocals;//local variables
    private int coStacksize;//max size
    private int coFlags;//表示codeobject属性特征
    private int coFirstLineNo;//标识第一行位置
    private PyObject coCode;//指令码
    private PyObject coConsts;//常量表
    private PyObject coNames;//变量表
    private PyObject coVarnames;//本地变量的元组
    private PyObject coFreevars;//自由变量表
    private PyObject coCellvars;//
    private PyObject coFilename;//加载文件路径
    private PyObject coName;//
    private PyObject coLnotab;//代码行号和指令码对应表
    private PyObject coZombieFrame;//栈
    private PyObject coWeakRefList;//
    private PyObject coExtra;

    @Override
    public String toString() {
        return "CodeObject{" +
                "coArgcount=" + coArgcount +
                ", coKwOnlyArgcount=" + coKwOnlyArgcount +
                ", coNLocals=" + coNLocals +
                ", coStacksize=" + coStacksize +
                ", coFlags=" + coFlags +
                ", coFirstLineNo=" + coFirstLineNo +
                ", coCode=" + coCode +
                ", coConsts=" + coConsts +
                ", coNames=" + coNames +
                ", coVarnames=" + coVarnames +
                ", coFreevars=" + coFreevars +
                ", coCellvars=" + coCellvars +
                ", coFilename=" + coFilename +
                ", coName=" + coName +
                ", coLnotab=" + coLnotab +
                ", coZombieFrame=" + coZombieFrame +
                ", coWeakRefList=" + coWeakRefList +
                ", coExtra=" + coExtra +
                '}';
    }
}
