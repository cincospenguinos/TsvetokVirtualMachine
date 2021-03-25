package usa.lafleur.cincospenguinos.assembler;

import org.junit.Test;
import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

import static org.junit.Assert.*;

public class InstructionBuilderTest {
    private final InstructionBuilder builder = new InstructionBuilder();

    @Test
    public void test_handlesInstructionWithRegisters() {
        TsvetokInstruction instruction = builder.clear()
                .setOperationByte("nensou")
                .setLeftRegister("$tnp0")
                .setRightRegister("$arj2")
                .build();
        byte registers = instruction.toBytes().getB();
        assertEquals(0x60, registers & 0xf0);
        assertEquals(0xa, registers & 0x0f);
    }

    @Test
    public void test_handlesInstructionWithImmediate() {
        TsvetokInstruction instruction = builder.clear()
                .setOperationByte("adf")
                .setImmediate("12")
                .build();
        byte immediate = instruction.toBytes().getB();
        assertEquals(0xc, immediate);
    }
}