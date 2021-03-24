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
}