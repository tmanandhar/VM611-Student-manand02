package engine.opcodes;

import java.util.Map;
import java.util.Stack;

/**
 * Loads a local variable onto the stack.
 */
public class LoadLocalOp implements Opcode {

    private final String varName;
    public LoadLocalOp(String varName) {
        this.varName = varName;
    }

    @Override
    public int execute(int pc, Stack<Integer> opStack, Map<String, Integer> localVars) {
        Integer val = localVars.get(varName);
        opStack.push(val);
        return pc + 1;
    }

}
