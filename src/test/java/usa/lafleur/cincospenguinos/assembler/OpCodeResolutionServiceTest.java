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
    public void test_resolverHandlesAddition() {
        byte code = resolver.codeFor("adr");
        assertEquals(code, 0x20);
        code = resolver.codeFor("adf");
        assertEquals(code, 0x30);
    }

    @Test
    public void test_resolverHandlesMultiplication() {
        byte code = resolver.codeFor("nultr");
        assertEquals(code, 0x40);
        code = resolver.codeFor("nultf");
        assertEquals(code, 0x50);
    }

    @Test
    public void test_resolverHandlesDivision() {
        byte code = resolver.codeFor("difr");
        assertEquals(code, 0x60);
        code = resolver.codeFor("diff");
        assertEquals(code, 0x70);
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