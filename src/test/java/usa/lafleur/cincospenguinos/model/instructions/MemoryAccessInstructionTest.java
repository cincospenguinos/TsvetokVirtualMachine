package usa.lafleur.cincospenguinos.model.instructions;

import org.junit.Test;
import usa.lafleur.cincospenguinos.assembler.RegisterResolutionService;
import usa.lafleur.cincospenguinos.assembler.TsvetokAssembler;
import usa.lafleur.cincospenguinos.model.RandomAccessMemory;
import usa.lafleur.cincospenguinos.model.RegisterArray;

import static org.junit.Assert.*;

public class MemoryAccessInstructionTest {
    private final TsvetokAssembler assembler = new TsvetokAssembler();
    private static final int ACCUMULATOR_INDEX = RegisterResolutionService.resolveRegister("$ak");

    @Test
    public void test_loadValueWorksAsDesired() {
        TsvetokInstruction instruction = assembler.createInstruction("nens $tnp0 tnp1");
        assertTrue(instruction instanceof MemoryAccessInstruction);
        RegisterArray registerArray = new RegisterArray(
            new byte[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        );
        RandomAccessMemory memory = new RandomAccessMemory(new byte[] { 12, 0, 0 });
        instruction.execute(registerArray, memory);
        assertEquals(12, registerArray.getValueOf(ACCUMULATOR_INDEX));
    }

    @Test
    public void test_storeValueWorksAsDesired() {
        TsvetokInstruction instruction = assembler.createInstruction("nensou $tnp0 tnp1");
        assertTrue(instruction instanceof MemoryAccessInstruction);
        RegisterArray registerArray = new RegisterArray(
                new byte[]{ 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        );
        RandomAccessMemory memory = new RandomAccessMemory(new byte[] { 0, 0, 0 });
        instruction.execute(registerArray, memory);
        assertEquals(3, memory.readAt((byte) 0, (byte) 0));
    }
}