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
}