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
}
