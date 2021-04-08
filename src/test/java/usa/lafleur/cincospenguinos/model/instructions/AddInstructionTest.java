package usa.lafleur.cincospenguinos.model.instructions;

import org.junit.Test;
import usa.lafleur.cincospenguinos.assembler.TsvetokAssembler;
import usa.lafleur.cincospenguinos.model.RegisterArray;

import static org.junit.Assert.*;

public class AddInstructionTest {
    private final TsvetokAssembler assembler = new TsvetokAssembler();

    @Test
    public void test_addValueWorksAsDesired() {
        TsvetokInstruction instruction = assembler.createInstruction("adf -1");
        assertTrue(instruction instanceof AddInstruction);
        byte[] registerArray = new byte[]{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        instruction.execute(new RegisterArray(registerArray));
        assertEquals(0, registerArray[0]);
    }

    @Test
    public void test_addRegisterWorksAsDesired() {
        TsvetokInstruction instruction = assembler.createInstruction("adr $ak $ak");
        byte[] registerArray = new byte[]{ 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        instruction.execute(new RegisterArray(registerArray));
        assertEquals(6, registerArray[0]);
    }

    @Test
    public void test_addRegisterTriggersOverflowFlag() {
        TsvetokInstruction instruction = assembler.createInstruction("adf 1");
        assertTrue(instruction instanceof AddInstruction);
        RegisterArray registerArray = new RegisterArray(new byte[]{ 0x7f, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        assertFalse(registerArray.isOverflowFlagSet());
        instruction.execute(registerArray);
        assertTrue(registerArray.isOverflowFlagSet());
    }
}