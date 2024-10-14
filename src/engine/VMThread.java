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
        Stack<Integer> stack = new Stack<>();
        Map<String, Integer> result = new HashMap<>();
        int count = 0;
        for (Opcode opCode : opCodes) {
           count = opCode.execute(count,  stack, result);
        }
        return result;
    }
}
