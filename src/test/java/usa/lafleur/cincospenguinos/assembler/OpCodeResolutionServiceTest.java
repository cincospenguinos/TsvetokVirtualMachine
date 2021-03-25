package usa.lafleur.cincospenguinos.assembler;

import org.junit.Test;

import static org.junit.Assert.*;

public class OpCodeResolutionServiceTest {
    private final OpCodeResolutionService resolver = new OpCodeResolutionService();

    @Test
    public void test_resolverHandlesNoOp() {
        byte code = resolver.codeFor("noup");
        assertEquals(code, 0x00);
    }

    @Test
    public void test_resolverHandlesMemory() {
        byte code = resolver.codeFor("nens");
        assertEquals(code, 0x10);
        code = resolver.codeFor("nensou");
        assertEquals(code, 0x10);
    }

    @Test
    public void test_resolverHandlesHalt() {
        byte code = resolver.codeFor("stoup");
        assertEquals(code, (byte) 0xf0);
    }

    @Test(expected = InvalidOperationException.class)
    public void test_failsWhenGivenANonOperation() {
        resolver.codeFor("this is not a thing");
    }
}