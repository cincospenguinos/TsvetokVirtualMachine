package usa.lafleur.cincospenguinos.model;

import org.junit.Test;
import usa.lafleur.cincospenguinos.model.instructions.Instruction;

import static org.junit.Assert.*;

public class JumpOnZeroInstructionTest {
    @Test
    public void test_doesNotJumpOnNonZero() {
        byte machineInstruction = (byte) 0b11100010; // jnp0 $ak $rej1
        Instruction instruction = new JumpOnZeroInstruction(machineInstruction);
        RegisterArray registers = new RegisterArray(new byte[] {
                (byte) 0xff, (byte) 0xff, 0, 0, 0
        });
        assertFalse(registers.isSet(RegisterArray.ZERO_FLAG));
        instruction.execute(null, registers);
        assertEquals((byte) 0, registers.programCounter());
    }

    @Test
    public void test_doesJumpOnNonZero() {
        byte machineInstruction = (byte) 0b11100010; // jnp0 $ak $rej1
        Instruction instruction = new JumpOnZeroInstruction(machineInstruction);
        RegisterArray registers = new RegisterArray(new byte[] {
                (byte) 0xff, 0, (byte) 0xff, 0, 0
        });
        registers.setFlag(RegisterArray.ZERO_FLAG);
        instruction.execute(null, registers);
        assertEquals((short) 0xffff, registers.programCounter());
    }
}