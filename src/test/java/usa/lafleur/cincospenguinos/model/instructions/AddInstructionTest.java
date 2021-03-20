package usa.lafleur.cincospenguinos.model.instructions;

import org.junit.Test;
import usa.lafleur.cincospenguinos.model.RegisterArray;

import static org.junit.Assert.*;

public class AddInstructionTest {
    @Test
    public void test_addHandlesRegisters() {
        byte machineInstruction = (byte) 0b01001111; // $retr + $retr
        Instruction instruction = new AddInstruction(machineInstruction);
        RegisterArray registers = new RegisterArray(new byte[] { 0, 0, 0, 12, 0 });
        instruction.execute(null, registers);
        assertEquals((byte) 24, registers.getRegister(RegisterArray.ACCUMULATOR_INDEX));
    }

    @Test
    public void test_addHandlesImmediate() {
        byte machineInstruction = (byte) 0b01011111; // $ak += 15
        Instruction instruction = new AddInstruction(machineInstruction);
        RegisterArray registers = new RegisterArray(new byte[] { 15, 0, 0, 0, 0 });
        instruction.execute(null, registers);
        assertEquals((byte) 30, registers.getRegister(RegisterArray.ACCUMULATOR_INDEX));
    }

    @Test
    public void test_addSetsOverflowFlag() {
        byte machineInstruction = (byte) 0b01010001; // $ak += 1
        Instruction instruction = new AddInstruction(machineInstruction);
        RegisterArray registers = new RegisterArray(new byte[] { 127, 0, 0, 0, 0 });
        instruction.execute(null, registers);
        assertTrue(registers.isSet(RegisterArray.OVERFLOW_FLAG));
    }

    @Test
    public void test_addSetsZeroFlag() {
        byte machineInstruction = (byte) 0b01010000; // $ak += 0
        Instruction instruction = new AddInstruction(machineInstruction);
        RegisterArray registers = new RegisterArray(new byte[] { 0, 0, 0, 0, 0 });
        instruction.execute(null, registers);
        assertTrue(registers.isSet(RegisterArray.ZERO_FLAG));
    }
}