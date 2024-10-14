import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

public class VMTest {
    @Test
    public void testConst() {
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 42
            store_local a
            """);

        assertEquals((Integer) 42, vars.get("a"));
    }

    @Test
    public void testAddCommand() {
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 5
            load_const 7
            add
            store_local x
            """);

        assertEquals((Integer) 12, vars.get("x"));
    }

    @Test
    public void testSubCommand() {
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 5
            load_const 7
            sub
            store_local x
            """);

        assertEquals((Integer) (-2), vars.get("x"));
    }

    @Test
    public void testMulCommand() {
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 7
            load_const 5
            mul
            store_local x
            """);

        assertEquals((Integer) 35, vars.get("x"));
    }

    @Test
    public void testDivCommand() {
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 7
            load_const 5
            div
            store_local x
            """);

        assertEquals((Integer) 1, vars.get("x"));
    }

    @Test
    public void testModCommand() {
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 5
            load_const 7
            mod
            store_local x
            """);

        assertEquals((Integer) 5, vars.get("x"));
    }

    @Test
    public void testEqCommand() {
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 7
            load_const 5
            cmpEQ
            store_local x
            """);

        assertEquals((Integer) 0, vars.get("x"));
    }

    @Test
    public void testNeqCommand() {
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 7
            load_const 5
            cmpNEQ
            store_local x
            """);

        assertEquals((Integer) 1, vars.get("x"));
    }

    @Test
    public void testCmpLtCommand() {
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 7
            load_const 5
            cmpLT
            store_local x
            """);

        assertEquals((Integer) 0, vars.get("x"));
    }

    @Test
    public void testCmpLteCommand() {
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 7
            load_const 5
            cmpLTE
            store_local x
            """);

        assertEquals((Integer) 0, vars.get("x"));
    }

    @Test
    public void testCmpGtCommand() {
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 7
            load_const 5
            cmpGT
            store_local x
            """);

        assertEquals((Integer) 1, vars.get("x"));
    }

    @Test
    public void testCmpGteCommand() {
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 7
            load_const 5
            cmpGTE
            store_local x
            """);

        assertEquals((Integer) 1, vars.get("x"));
    }

// I used chatGPT to understand and implement branch test cases

    @Test
    public void testBranch() {
        Map<String, Integer> vars = TestUtil.testCode("""
        load_const 1   # Push a constant value onto the stack
        branch 4       # Unconditional branch to line 4
        store_local a  # This line should be skipped
        load_const 42  # This is line 4
        store_local a  # Store 42 in variable 'a'
        """);

        // 'a' should end up with the value 42, because the branch skips the store_local at line 3
        assertEquals((Integer) 42, vars.get("a"));
    }

    @Test
    public void testBranchFFalseCondition() {
        Map<String, Integer> vars = TestUtil.testCode("""
        load_const 0   # Push 0 onto the stack
        branchF 4      # If the top of the stack is 0, branch to line 4
        store_local a  # This line should be skipped
        load_const 42  # This is line 4
        store_local a  # Store 42 in variable 'a'
        """);

        // Since the stack had 0, branchF should jump to line 4, and 'a' should be 42
        assertEquals((Integer) 42, vars.get("a"));
    }

    @Test
    public void testBranchFTrueCondition() {
        Map<String, Integer> vars = TestUtil.testCode("""
        load_const 1   # Push 1 onto the stack
        branchF 4      # If the top of the stack is 0, branch to line 4 (won't happen here)
        load_const 99  # This line should execute since we didn't branch
        store_local a  # Store 99 in variable 'a'
        load_const 42  # This is line 4
        store_local b  # Store 42 in variable 'b'
        """);

        // Since the stack had 1, branchF shouldn't have branched, so 'a' should be 99 and 'b' should be 42
        assertEquals((Integer) 99, vars.get("a"));
        assertEquals((Integer) 42, vars.get("b"));
    }

    @Test
    public void testBranchFLoop() {
        Map<String, Integer> vars = TestUtil.testCode("""
        load_const 0   # i = 0
        store_local i
        load_const 0   # sum = 0
        store_local sum
        load_local i   # while (i < 3)
        load_const 3
        cmpLT
        branchF 16     # Exit loop if i >= 3
        load_local sum
        load_local i
        add            # sum += i
        store_local sum
        load_local i
        load_const 1
        add            # i++
        store_local i
        branch 4       # Back to the start of the loop
        load_local sum # This is line 16, after the loop
        store_local result
        """);

        // The sum should be 0 + 1 + 2 = 3 when the loop terminates
        assertEquals((Integer) 0, vars.get("result"));
    }


}
