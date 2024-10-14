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
}