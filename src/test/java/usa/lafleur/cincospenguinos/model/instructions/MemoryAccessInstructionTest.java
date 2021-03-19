package usa.lafleur.cincospenguinos.model.instructions;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemoryAccessInstructionTest {
    @Test
    public void test_executeHandlesRead() {
        byte rawInstruction = (byte) 0b00100110;// Read from address 1
        byte[] memory = new byte[] { 0, 21 };
        byte[] registers = new byte[] { 0, 0, 1, 0 };
        Instruction instruction = new MemoryAccessInstruction(rawInstruction);
        instruction.execute(memory, registers);
        assertEquals((byte) 21, registers[0]);
    }

    @Test
    public void test_executeHandlesWrite() {
        byte rawInstruction = (byte) 0b00110110;// Write from $ak to address 1
        byte[] memory = new byte[] { 0, 0 };
        byte[] registers = new byte[] { 12, 0, 1, 0 };
        Instruction instruction = new MemoryAccessInstruction(rawInstruction);
        instruction.execute(memory, registers);
        assertEquals((byte) 12, memory[1]);
    }
}