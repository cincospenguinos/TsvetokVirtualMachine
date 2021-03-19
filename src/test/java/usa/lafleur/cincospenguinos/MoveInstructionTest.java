package usa.lafleur.cincospenguinos;

import org.junit.Test;
import usa.lafleur.cincospenguinos.model.instructions.Instruction;
import usa.lafleur.cincospenguinos.model.instructions.MoveInstruction;

import static org.junit.Assert.*;

public class MoveInstructionTest {
    @Test
    public void test_executeMovesCorrectRegisters() {
        byte machineInstruction = (byte) 0b10001001; // Move $rej2 into $rej1
        Instruction instruction = new MoveInstruction(machineInstruction);
        byte[] registers = new byte[] { 0, 0, 33, 0 };
        instruction.execute(null, registers);
        assertEquals((byte) 33, registers[1]);
    }

    @Test
    public void test_executeMovesImmediate() {
        byte machineInstruction = (byte) 0b10011001; // Move $rej2 into $rej1
        Instruction instruction = new MoveInstruction(machineInstruction);
        byte[] registers = new byte[] { 0, 0, 0, 0 };
        instruction.execute(null, registers);
        assertEquals((byte) 9, registers[0]);
    }
}