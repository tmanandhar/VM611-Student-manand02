package engine;

import java.util.List;
import java.util.Map;

import engine.opcodes.Opcode;

/**
 * VMThread executes a list of opcodes.
 */
public class VMThread {

    public VMThread(List<Opcode> opcodes) {
    }

    /**
     * Runs the opcodes, and returns the resulting local variables.
     * 
     * @return the local variables
     */
    public Map<String, Integer> run() {
        return null;
    }
}
