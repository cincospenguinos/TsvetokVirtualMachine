package usa.lafleur.cincospenguinos.model.instructions;

import org.junit.Test;
import usa.lafleur.cincospenguinos.model.RegisterArray;

import static org.junit.Assert.*;

public class MultiplyInstructionTest {
    @Test
    public void test_multiplyHandlesRegisters() {
        byte machineInstruction = 0b01100101; // nult $rej1 $rej1
        RegisterArray registerArray = new RegisterArray(new byte[] { 0, 2, 0, 0, 0 });
        Instruction instruction = new MultiplyInstruction(machineInstruction);
        instruction.execute(null, registerArray);
        assertEquals(4, registerArray.accumulator());
    }
}