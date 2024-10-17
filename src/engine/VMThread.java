package engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import engine.opcodes.Opcode;

/**
 * VMThread executes a list of opcodes.
 */
public class VMThread {

    private final List<Opcode> opCodes;

    public VMThread(List<Opcode> opcodes) {
        this.opCodes = opcodes;
    }

    /**
     * Runs the opcodes, and returns the resulting local variables.
     *
     * @return the local variables
     */
    public Map<String, Integer> run() {
        Map<String, Integer> localVars = new HashMap<>();
        Stack<Integer> opStack = new Stack<>();
        int pc = 0;  // Program counter starts at 0

        while (pc < opCodes.size()) {
            Opcode currentOpcode = opCodes.get(pc);
            pc = currentOpcode.execute(pc, opStack, localVars);  // Execute the current opcode
        }

        return localVars;
    }
}