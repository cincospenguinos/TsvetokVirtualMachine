package usa.lafleur.cincospenguinos.model.instructions;

import org.junit.Test;
import usa.lafleur.cincospenguinos.model.RegisterArray;

import static org.junit.Assert.*;

public class LogicInstructionTest {
    @Test
    public void test_logicalAndWorks() {
        byte machineInstruction = (byte) 0b10100010; // loujet $ak $rej2
        Instruction instruction = new LogicInstruction(machineInstruction);
        RegisterArray registers = new RegisterArray(new byte[] { 2, 0, 0, 0, 0 });
        instruction.execute(null, registers);
        assertEquals((byte) 0, registers.accumulator());
    }

    @Test
    public void test_logicalOrWorks() {
        byte machineInstruction = (byte) 0b10110010; // loujour $ak $rej2
        Instruction instruction = new LogicInstruction(machineInstruction);
        RegisterArray registers = new RegisterArray(new byte[] { 0, 0, 2, 0, 0 });
        instruction.execute(null, registers);
        assertEquals((byte) 2, registers.accumulator());
    }
}