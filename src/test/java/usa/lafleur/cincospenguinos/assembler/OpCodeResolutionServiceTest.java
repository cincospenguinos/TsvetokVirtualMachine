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
    public void test_resolverHandlesMovement() {
        byte code = resolver.codeFor("boujr");
        assertEquals(code, 0x1);
        code = resolver.codeFor("boujf");
        assertEquals(code, 0x1);
    }

    @Test
    public void test_resolverHandlesMemory() {
        byte code = resolver.codeFor("nens");
        assertEquals(code, 0x1);
        code = resolver.codeFor("nensou");
        assertEquals(code, 0x1);
    }

    @Test
    public void test_resolverHandlesAddition() {
        byte code = resolver.codeFor("adr");
        assertEquals(code, 0x2);
        code = resolver.codeFor("adf");
        assertEquals(code, 0x3);
    }

    @Test
    public void test_resolverHandlesMultiplication() {
        byte code = resolver.codeFor("nultr");
        assertEquals(code, 0x4);
        code = resolver.codeFor("nultf");
        assertEquals(code, 0x5);
    }

    @Test
    public void test_resolverHandlesDivision() {
        byte code = resolver.codeFor("difr");
        assertEquals(code, 0x6);
        code = resolver.codeFor("diff");
        assertEquals(code, 0x7);
    }

    @Test
    public void test_resolverHandlesJump() {
        byte code = resolver.codeFor("jnp");
        assertEquals(code, (byte) 0x8);
    }

    @Test
    public void test_resolverHandlesJumpOnZero() {
        byte code = resolver.codeFor("jnps");
        assertEquals(code, (byte) 0x9);
    }

    @Test
    public void test_resolverHandlesJumpOnNotZero() {
        byte code = resolver.codeFor("jnpns");
        assertEquals(code, (byte) 0xa);
    }

    @Test
    public void test_resolverHandlesReturn() {
        byte code = resolver.codeFor("retr");
        assertEquals(code, (byte) 0xb);
    }

    @Test
    public void test_resolverHandlesPush() {
        byte code = resolver.codeFor("pous");
        assertEquals(code, (byte) 0xc);
    }

    @Test
    public void test_resolverHandlesPop() {
        byte code = resolver.codeFor("pap");
        assertEquals(code, (byte) 0xd);
    }

    @Test
    public void test_resolverHandlesSystemCall() {
        byte code = resolver.codeFor("sis");
        assertEquals(code, (byte) 0xe);
    }

    @Test
    public void test_resolverHandlesHalt() {
        byte code = resolver.codeFor("stoup");
        assertEquals(code, (byte) 0xf);
    }

    @Test(expected = InvalidOperationException.class)
    public void test_failsWhenGivenANonOperation() {
        resolver.codeFor("this is not a thing");
    }
}