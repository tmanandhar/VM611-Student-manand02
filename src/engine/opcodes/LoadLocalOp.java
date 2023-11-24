package engine.opcodes;

import java.util.Map;
import java.util.Stack;

/**
 * Loads a local variable onto the stack.
 */
public class LoadLocalOp implements Opcode {

    public LoadLocalOp(String varName) {
    }

    @Override
    public int execute(int pc, Stack<Integer> opStack, Map<String, Integer> localVars) {
        return pc + 1;
    }

}
