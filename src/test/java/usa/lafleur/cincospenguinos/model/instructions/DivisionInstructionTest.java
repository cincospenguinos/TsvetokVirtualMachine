package usa.lafleur.cincospenguinos.model.instructions;

import org.junit.Test;
import usa.lafleur.cincospenguinos.assembler.RegisterResolutionService;
import usa.lafleur.cincospenguinos.assembler.TsvetokAssembler;
import usa.lafleur.cincospenguinos.model.RegisterArray;

import static org.junit.Assert.*;

public class DivisionInstructionTest {
    private final TsvetokAssembler assembler = new TsvetokAssembler();
    private static final int ACCUMULATOR_INDEX = RegisterResolutionService.resolveRegister("$ak");

    @Test
    public void test_divideValueWorksAsDesired() {
        TsvetokInstruction instruction = assembler.createInstruction("diff 3");
        RegisterArray registerArray = new RegisterArray(new byte[]{30, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        instruction.execute(registerArray, null);
        assertEquals(10, registerArray.getValueOf(ACCUMULATOR_INDEX));
    }

    @Test
    public void test_divideRegisterWorksAsDesired() {
        TsvetokInstruction instruction = assembler.createInstruction("difr $ak $ak");
        RegisterArray registerArray = new RegisterArray(
                new byte[]{3, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        );
        instruction.execute(registerArray, null);
        assertEquals(1, registerArray.getValueOf(ACCUMULATOR_INDEX));
    }
}