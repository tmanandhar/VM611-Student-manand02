package engine.opcodes;

import java.util.Map;
import java.util.Stack;

/**
 * Performs an operation with the two values on the top of the stack.
 *
 */
public class BinaryOp implements Opcode {

    /**
     * Creates a BinaryOp.
     *
     * @param op the Operator
     */
    public BinaryOp(Operator op) {
    }

    @Override
    public int execute(int pc, Stack<Integer> opStack, Map<String, Integer> localVars) {
        return pc + 1;
    }
}
