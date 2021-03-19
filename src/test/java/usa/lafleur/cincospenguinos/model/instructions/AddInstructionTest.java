package usa.lafleur.cincospenguinos.model.instructions;

import org.junit.Test;

import static org.junit.Assert.*;

public class AddInstructionTest {
    @Test
    public void test_addHandlesRegisters() {
        byte machineInstruction = (byte) 0b01001111; // Move $rej2 into $rej1
        Instruction instruction = new AddInstruction(machineInstruction);
        byte[] registers = new byte[] { 0, 0, 0, 12 };
        instruction.execute(null, registers);
        assertEquals((byte) 24, registers[0]);
    }
}