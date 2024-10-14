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
        switch (type) {
            case UNCONDITIONAL:
                // Unconditionally jump to the destination line
                return dest;
            case TRUE:
                // Pop the top of the stack and branch if the value is non-zero (true)
                if (opStack.pop() != 0) {
                    return dest;
                }
                break;
            case FALSE:
                // Pop the top of the stack and branch if the value is zero (false)
                if (opStack.pop() == 0) {
                    return dest;
                }
                break;
            default:
                throw new RuntimeException("Unknown branch type: " + type);
        }
        // If no branch occurs, just move to the next instruction
        return pc + 1;
    }
}

