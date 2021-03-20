package usa.lafleur.cincospenguinos.model.instructions;

import org.junit.Test;
import usa.lafleur.cincospenguinos.model.RegisterArray;

import static org.junit.Assert.*;

public class DivisionInstructionTest {
    @Test
    public void test_executeWorks() {
        byte machineInstruction = (byte) 0b11010010; // dif 2
        Instruction instruction = new DivisionInstruction(machineInstruction);
        RegisterArray registers = new RegisterArray(new byte[] { 12, 0, 0, 12, 0 });
        instruction.execute(null, registers);
        assertEquals((byte) 6, registers.accumulator());
    }
}