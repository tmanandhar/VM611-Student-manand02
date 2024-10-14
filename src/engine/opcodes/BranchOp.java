package engine.opcodes;

import java.util.Map;
import java.util.Stack;

/**
 * Possibly branches to another location in the code.
 */
public class BranchOp implements Opcode {
    public enum Type {
        UNCONDITIONAL,
        TRUE,
        FALSE
    }

    private final Type type;
    private final int dest;
    public BranchOp(Type type, int dest) {
        this.type = type;
        this.dest = dest;
    }

    @Override
    public int execute(int pc, Stack<Integer> opStack, Map<String, Integer> localVars) {
        return pc + 1;
    }

}
