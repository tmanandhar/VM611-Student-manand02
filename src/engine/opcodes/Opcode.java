package engine.opcodes;

import java.util.Map;
import java.util.Stack;

public interface Opcode {
    /**
     * Executes the opcode.
     *
     * @param pc        the program counter
     * @param opStack   the op stack
     * @param localVars the local variables
     * @return the next instruction to execute (usually pc + 1)
     */
    int execute(int pc, Stack<Integer> opStack, Map<String, Integer> localVars);
}
