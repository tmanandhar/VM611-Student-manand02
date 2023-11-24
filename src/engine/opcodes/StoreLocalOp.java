package engine.opcodes;

import java.util.Map;
import java.util.Stack;

/**
 * Stores a value from the stack into a local variable.
 */
public class StoreLocalOp implements Opcode {

    public StoreLocalOp(String varName) {
    }

    @Override
    public int execute(int pc, Stack<Integer> opStack, Map<String, Integer> localVars) {
        return pc + 1;
    }
}
