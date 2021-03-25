package usa.lafleur.cincospenguinos.assembler;

import org.junit.Test;
import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

import static org.junit.Assert.*;

public class TsvetokAssemblerTest {
    private final TsvetokAssembler assembler = new TsvetokAssembler();

    @Test
    public void test_createInstructionHandlesMemoryLoadFlag() {
        TsvetokInstruction instruction = assembler.createInstruction("nens $ak $ak");
        byte upper = instruction.toBytes().getA();
        byte flags = (byte) (upper & 0b00001111);
        assertEquals(0x00, flags);
    }

    @Test
    public void test_createInstructionHandlesMemoryStoreFlag() {
        TsvetokInstruction instruction = assembler.createInstruction("nensou $rej1 $tnp0");
        byte opcode = instruction.toBytes().getA();
        byte flags = (byte) (((opcode & 0b00001111)) >> 3);
        assertEquals(0x01, flags);
    }
}