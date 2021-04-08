package usa.lafleur.cincospenguinos.model.instructions;

import org.junit.Test;
import usa.lafleur.cincospenguinos.assembler.TsvetokAssembler;

import static org.junit.Assert.*;

public class AddInstructionTest {
    private final TsvetokAssembler assembler = new TsvetokAssembler();

    @Test
    public void test_addValueWorksAsDesired() {
        TsvetokInstruction instruction = assembler.createInstruction("adf -1");
        assertTrue(instruction instanceof AddInstruction);
        byte[] registerArray = new byte[]{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        instruction.execute(registerArray);
        assertEquals(0, registerArray[0]);
    }
}