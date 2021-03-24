package usa.lafleur.cincospenguinos.assembler;

import org.junit.Test;

import static org.junit.Assert.*;

public class TsvetokAssemblerTest {
    @Test
    public void test_assemblerHandlesNoOp() {
        TsvetokAssembler assembler = new TsvetokAssembler();
        TsvetokAssembler.TsvetokInstruction instruction = assembler.createInstruction("noup");
        assertEquals(instruction.toBytes(), new TsvetokAssembler.Tuple<>((byte) 0x0, (byte) 0x0));
    }
}