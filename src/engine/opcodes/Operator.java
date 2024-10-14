package engine.opcodes;

import java.util.HashMap;
import java.util.Map;

/**
 * The operator of a BinaryOperation.
 */
public enum Operator {
    ADD("+", "add"),
    SUB("-", "sub"),
    MUL("*", "mul"),
    DIV("/", "div"),
    MOD("%", "mod"),
    EQ("==", "cmpEQ"),
    NEQ("!=", "cmpNEQ"),
    LT("<", "cmpLT"),
    LTE("<=", "cmpLTE"),
    GT(">", "cmpGT"),
    GTE(">=", "cmpGTE");

    private static final Map<String, Operator> opsBySymbol = new HashMap<>();
    private static final Map<String, Operator> opsByInstruction = new HashMap<>();

    static {
        for (Operator op : Operator.values()) {
            opsBySymbol.put(op.symbol, op);
            opsByInstruction.put(op.instruction, op);
        }
    }

    private String symbol;
    private String instruction;

    private Operator(String symbol, String instruction) {
        this.symbol = symbol;
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return symbol;
    }

    /**
     * Returns the opcode to execute this operator, e.g. "add".
     *
     * @return the opcode to execute this operator
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * Return the symbol for this operator, e.g. "+".
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Finds the Operator for an instruction, e.g. "add".
     *
     * @param instruction the instruction
     * @return the Operator
     */
    public static Operator fromInstruction(String instruction) {
        return opsByInstruction.get(instruction);
    }

    /**
     * Finds the Operator for a symbol, e.g. "+".
     * 
     * @param symbol the symbol
     * @return the Operator
     */
    public static Operator fromSymbol(String symbol) {
        return opsBySymbol.get(symbol);
    }

    public int apply(int op1, int op2) {
        return switch (this) {
        case ADD -> op1 + op2;
        case SUB -> op2 - op1;
        case MUL -> op1 * op2;
        case DIV -> op2 / op1;
        case EQ -> op1 == op2 ? 0 : 1;
        default -> throw new RuntimeException("Unknown operator: " + this);
        };
    }
}
