package usa.lafleur.cincospenguinos.assembler;

import org.junit.Test;
import usa.lafleur.cincospenguinos.core.Tuple;
import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

import static org.junit.Assert.*;

public class TsvetokAssemblerTest {
    @Test
    public void test_assemblerHandlesNoOp() {
        TsvetokAssembler assembler = new TsvetokAssembler();
        TsvetokInstruction instruction = assembler.createInstruction("noup");
        assertEquals(instruction.toBytes(), new Tuple<>((byte) 0x0, (byte) 0x0));
    }

    @Test
    public void test_assemblerHandlesHalt() {
        TsvetokAssembler assembler = new TsvetokAssembler();
        TsvetokInstruction instruction = assembler.createInstruction("stoup");
        assertEquals((byte) instruction.toBytes().getA(), (byte) 0xf0);
    }
}