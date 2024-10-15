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

    @Test
    public void testBranch() {
        Map<String, Integer> vars = TestUtil.testCode("""
                load_const 42
                branch 4
                load_const 99 # This line will be skipped
                store_local a # This line will be skipped
                load_const 77
                store_local a
                """);

        // The branch jumps over the line with 99 and executes the store of 77
        assertEquals((Integer) 77, vars.get("a"));
    }

    @Test
    public void testBranchF() {
        Map<String, Integer> vars = TestUtil.testCode("""
                load_const 0  # False
                branchF 4     # jump to line 4
                load_const 42 # This line should be skipped
                store_local a # This line should be skipped
                load_const 99 # execute this line after jumping
                store_local a
                """);

        // The branchF jumps to the line that loads 99, so the value of 'a' should be 99
        assertEquals((Integer) 99, vars.get("a"));
    }

    @Test
    public void testBranchT() {
        Map<String, Integer> vars = TestUtil.testCode("""
                load_const 1  # True
                branchT 4     # Should jump to line 4
                load_const 42 # This line should be skipped
                store_local a
                load_const 99 # execute this line after jumping
                store_local a
                """);

        // The branchT jumps to the line that loads 99, so the value of 'a' should be 99
        assertEquals((Integer) 99, vars.get("a"));
    }

//    @Test
//    public void testBranchFLoop() {
//        Map<String, Integer> vars = TestUtil.testCode("""
//                load_const 0  # i = 0
//                store_local i
//                load_const 0  # sum = 0
//                store_local sum
//                load_local i  # while (i < 3)
//                load_const 3
//                cmpLT
//                branchF 12    # Jump to end of loop if false
//                load_local sum
//                load_const 1  # sum++
//                add
//                store_local sum
//                load_local i  # i++
//                load_const 1
//                add
//                store_local i
//                branch 4      # Jump back to condition check
//                load_local sum
//                store_local result  # Final result
//                """);
//
//        // The loop should run 3 times, so sum should be 3
//        assertEquals((Integer) 3, vars.get("result"));
//    }

    @Test
    public void testBranchTNoJump() {
        Map<String, Integer> vars = TestUtil.testCode("""
                load_const 0  # False
                branchT 5     # Should NOT jump to line 5
                load_const 42 # Should execute this line
                store_local a
                load_const 99 # This line should not be skipped
                store_local a
                """);

        // Since branchT doesn't jump, the value of 'a' should be 99
        assertEquals((Integer) 99, vars.get("a"));
    }
}