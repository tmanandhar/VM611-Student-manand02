import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import engine.VMThread;
import engine.opcodes.BinaryOp;
import engine.opcodes.BranchOp;
import engine.opcodes.LoadConstOp;
import engine.opcodes.LoadLocalOp;
import engine.opcodes.Opcode;
import engine.opcodes.Operator;
import engine.opcodes.StoreLocalOp;

/**
 * Useful methods for testing.
 */
public class TestUtil {
    /**
     * Parses VM assembly into opcodes.
     *
     * @param scanner
     * @return
     */
    public static List<Opcode> parseOpcodes(String code) {
        String[] lines = code.split("\n");

        List<Opcode> ops = new ArrayList<>();
        for (String line : lines) {
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            String[] tokens = line.split(" ");

            if (tokens[0].endsWith(":")) {
                tokens = Arrays.copyOfRange(tokens, 1, tokens.length);
            }

            ops.add(createOpcode(tokens));
        }
        return ops;
    }

    /**
     * Creates an opcode.
     *
     * @param tokens the tokens for this opcode
     * @return the opcode
     */
    private static Opcode createOpcode(String[] tokens) {
        String opName = tokens[0];

        Operator type = Operator.fromInstruction(opName);
        if (type != null) {
            return new BinaryOp(type);
        }

        if ("load_const".equals(opName)) {
            return new LoadConstOp(Integer.parseInt(tokens[1]));
        }

        if ("load_local".equals(opName)) {
            return new LoadLocalOp(tokens[1]);
        }

        if ("store_local".equals(opName)) {
            return new StoreLocalOp(tokens[1]);
        }

        if ("branch".equals(opName)) {
            return new BranchOp(BranchOp.Type.UNCONDITIONAL, Integer.parseInt(tokens[1]));
        }

        if ("branchT".equals(opName)) {
            return new BranchOp(BranchOp.Type.TRUE, Integer.parseInt(tokens[1]));
        }

        if ("branchF".equals(opName)) {
            return new BranchOp(BranchOp.Type.FALSE, Integer.parseInt(tokens[1]));
        }

        throw new RuntimeException("Unrecognized opcode: " + opName);
    }

    /**
     * Executes the supplied code, and returns the local variables.
     *
     * @param code the code
     * @return the local variables
     */
    public static Map<String, Integer> testCode(String code) {
        List<Opcode> ops = TestUtil.parseOpcodes(code);
        VMThread e = new VMThread(ops);
        return e.run();
    }
}
