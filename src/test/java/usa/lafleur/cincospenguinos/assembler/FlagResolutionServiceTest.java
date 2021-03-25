package usa.lafleur.cincospenguinos.assembler;

import org.junit.Test;

import static org.junit.Assert.*;

public class FlagResolutionServiceTest {
    private final FlagResolutionService resolver = new FlagResolutionService();

    @Test
    public void test_givesCorrectMemoryLoadFlag() {
        byte flags = resolver.flagBitsFor("nens");
        assertEquals(0x0, flags);
    }

    @Test
    public void test_givesCorrectMemoryStoreFlag() {
        byte flags = resolver.flagBitsFor("nensou");
        assertEquals(0x8, flags);
    }

    @Test
    public void test_givesCorrectMoveRegistersFlag() {
        byte flags = resolver.flagBitsFor("boujr");
        assertEquals(0x4, flags);
    }

    @Test
    public void test_givesCorrectMoveImmediateFlag() {
        byte flags = resolver.flagBitsFor("boujf");
        assertEquals(0xc, flags);
    }
}