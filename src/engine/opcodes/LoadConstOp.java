package engine.opcodes;

import java.util.Map;
import java.util.Stack;

/**
 * Loads a constant onto the stack.
 */
public class LoadConstOp implements Opcode {

    private final int  value;

    public LoadConstOp(int value) {
        this.value = value;
    }

    @Override
    public int execute(int pc, Stack<Integer> opStack, Map<String, Integer> localVars) {
        opStack.push(value);
        return pc + 1;
    }

}
