package usa.lafleur.cincospenguinos.machine.instructions;

import org.junit.Test;
import usa.lafleur.cincospenguinos.assembler.RegisterResolutionService;
import usa.lafleur.cincospenguinos.assembler.TsvetokAssembler;
import usa.lafleur.cincospenguinos.machine.RandomAccessMemory;
import usa.lafleur.cincospenguinos.machine.RegisterArray;

import static org.junit.Assert.*;

public class MemoryRegisterMovementInstructionTest {
    private final TsvetokAssembler assembler = new TsvetokAssembler();
    private static final int ACCUMULATOR_INDEX = RegisterResolutionService.resolveRegister("$ak");

    @Test
    public void test_loadValueWorksAsDesired() {
        TsvetokInstruction instruction = assembler.createInstruction("nens $tnp0 $tnp1");
        assertTrue(instruction instanceof MemoryRegisterMovementInstruction);
        RegisterArray registerArray = new RegisterArray(
            new byte[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        );
        RandomAccessMemory memory = new RandomAccessMemory(new byte[] { 12, 0, 0 });
        instruction.execute(registerArray, memory);
        assertEquals(12, registerArray.getValueOf(ACCUMULATOR_INDEX));
    }

    @Test
    public void test_storeValueWorksAsDesired() {
        TsvetokInstruction instruction = assembler.createInstruction("nensou $tnp0 $tnp1");
        RegisterArray registerArray = new RegisterArray(
                new byte[]{ 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        );
        RandomAccessMemory memory = new RandomAccessMemory(new byte[] { 0, 0, 0 });
        instruction.execute(registerArray, memory);
        assertEquals(3, memory.readAt((byte) 0, (byte) 0));
    }

    @Test
    public void test_moveRegisterWorksAsDesired() {
        TsvetokInstruction instruction = assembler.createInstruction("boujr $ak $rej2");
        RegisterArray registerArray = new RegisterArray(
                new byte[]{ 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        );
        instruction.execute(registerArray, null);
        int registerIndex = RegisterResolutionService.resolveRegister("$rej2");
        assertEquals(3, registerArray.getValueOf(registerIndex));
    }

    @Test
    public void test_moveValueWorksAsDesired() {
        TsvetokInstruction instruction = assembler.createInstruction("boujf 29");
        RegisterArray registerArray = new RegisterArray(
                new byte[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        );
        instruction.execute(registerArray, null);
        assertEquals(29, registerArray.getValueOf(ACCUMULATOR_INDEX));
    }
}