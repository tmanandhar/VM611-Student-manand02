package engine.opcodes;

import java.util.Map;
import java.util.Stack;

/**
 * Loads a constant onto the stack.
 */
public class LoadConstOp implements Opcode {

    public LoadConstOp(int value) {
    }

    @Override
    public int execute(int pc, Stack<Integer> opStack, Map<String, Integer> localVars) {
        return pc + 1;
    }

}
