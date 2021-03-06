package usa.lafleur.cincospenguinos.machine.instructions;

import org.junit.Test;
import usa.lafleur.cincospenguinos.assembler.RegisterResolutionService;
import usa.lafleur.cincospenguinos.assembler.TsvetokAssembler;
import usa.lafleur.cincospenguinos.machine.RegisterArray;

import static org.junit.Assert.*;

public class MultiplyInstructionTest {
    private final TsvetokAssembler assembler = new TsvetokAssembler();
    private static final int ACCUMULATOR_INDEX = RegisterResolutionService.resolveRegister("$ak");

    @Test
    public void test_multiplyValueWorksAsDesired() {
        TsvetokInstruction instruction = assembler.createInstruction("nultf -1");
        RegisterArray registerArray = new RegisterArray(new byte[]{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        instruction.execute(registerArray, null);
        assertEquals(-1, registerArray.getValueOf(ACCUMULATOR_INDEX));
    }

    @Test
    public void test_multiplyRegisterWorksAsDesired() {
        TsvetokInstruction instruction = assembler.createInstruction("nultr $ak $ak");
        RegisterArray registerArray = new RegisterArray(
                new byte[]{ 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        );
        instruction.execute(registerArray, null);
        assertEquals(9, registerArray.getValueOf(ACCUMULATOR_INDEX));
    }

    @Test
    public void test_multiplyTriggersOverflowFlag() {
        TsvetokInstruction instruction = assembler.createInstruction("nultf 2");
        RegisterArray registerArray = new RegisterArray(new byte[]{ 0x7f, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        assertFalse(registerArray.isOverflowFlagSet());
        instruction.execute(registerArray, null);
        assertTrue(registerArray.isOverflowFlagSet());
    }

    @Test
    public void test_multiplyTriggersZeroFlag() {
        TsvetokInstruction instruction = assembler.createInstruction("nultf 0");
        RegisterArray registerArray = new RegisterArray(new byte[]{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        instruction.execute(registerArray, null);
        assertTrue(registerArray.isZeroFlagSet());
    }
}