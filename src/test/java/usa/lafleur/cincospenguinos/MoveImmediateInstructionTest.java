package usa.lafleur.cincospenguinos;

import org.junit.Test;
import usa.lafleur.cincospenguinos.model.instructions.Instruction;
import usa.lafleur.cincospenguinos.model.instructions.MoveImmediateInstruction;

import static org.junit.Assert.*;

public class MoveImmediateInstructionTest {
    @Test
    public void test_executeMovesCorrectValuesIntoRegister() {
        Instruction instruction = new MoveImmediateInstruction((byte) 0b01001100);
        byte[] registers = new byte[4];
        instruction.execute(null, registers);
        assertEquals((byte) 12, registers[0]);
    }
}