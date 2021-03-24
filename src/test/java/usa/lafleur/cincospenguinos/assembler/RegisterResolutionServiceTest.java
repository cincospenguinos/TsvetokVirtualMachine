package usa.lafleur.cincospenguinos.assembler;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterResolutionServiceTest {
    private RegisterResolutionService service = new RegisterResolutionService();

    @Test
    public void test_resolvesAccumulatorRegister() {
        byte registerByte = service.resolve("$ak");
        assertEquals((byte) 0x0, registerByte);
    }

    @Test
    public void test_resolvesGeneralPurposeRegisters() {
        int[] registers = { 1, 2, 3, 4, 5 };
        for (int i : registers) {
            String registerName = "$rej" + i;
            byte registerByte = service.resolve(registerName);
            assertEquals((byte) i, registerByte);
        }
    }

    @Test
    public void test_resolvesTemporaryRegisters() {
        byte registerByte = service.resolve("$tnp0");
        assertEquals((byte) 0x6, registerByte);
        registerByte = service.resolve("$tnp1");
        assertEquals((byte) 0x7, registerByte);
    }

    @Test
    public void test_resolvesArgumentRegisters() {
        byte registerByte = service.resolve("$arj0");
        assertEquals((byte) 0x8, registerByte);
        registerByte = service.resolve("$arj1");
        assertEquals((byte) 0x9, registerByte);
        registerByte = service.resolve("$arj2");
        assertEquals((byte) 0xa, registerByte);
    }

    @Test
    public void test_resolvesSubroutineRegisters() {
        byte registerByte = service.resolve("$retrf");
        assertEquals((byte) 0xb, registerByte);
        registerByte = service.resolve("$retra");
        assertEquals((byte) 0xc, registerByte);
    }

    @Test
    public void test_resolvesStackPointerRegister() {
        byte registerByte = service.resolve("$fn");
        assertEquals((byte) 0xe, registerByte);
    }

    @Test
    public void test_resolvesProgramCounterRegister() {
        byte registerByte = service.resolve("$pn");
        assertEquals((byte) 0xf, registerByte);
    }
}