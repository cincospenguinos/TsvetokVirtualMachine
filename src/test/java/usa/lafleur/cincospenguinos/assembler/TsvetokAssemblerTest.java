package usa.lafleur.cincospenguinos.assembler;

import org.junit.Test;
import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

import static org.junit.Assert.*;

public class TsvetokAssemblerTest {
    private final TsvetokAssembler assembler = new TsvetokAssembler();

    @Test
    public void test_handlesRegisters() {
        TsvetokInstruction instruction = assembler.createInstruction("nensou $tnp0 $arj2");
        byte registers = instruction.toBytes().getB();
        assertEquals(0x60, registers & 0xf0);
        assertEquals(0xa, registers & 0x0f);
    }

    @Test
    public void test_handlesImmediates() {
        TsvetokInstruction instruction = assembler.createInstruction("adf 127");
        byte immediate = instruction.toBytes().getB();
        assertEquals(0x7f, immediate);
    }

    @Test
    public void test_handlesNoOp() {
        TsvetokInstruction instruction = assembler.createInstruction("noup");
        byte right = instruction.toBytes().getB();
        assertEquals(0x00, right);
    }
}