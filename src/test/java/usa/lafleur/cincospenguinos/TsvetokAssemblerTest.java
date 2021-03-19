package usa.lafleur.cincospenguinos;

import org.junit.Test;

import static org.junit.Assert.*;

public class TsvetokAssemblerTest {
    @Test
    public void test_assembleHandlesMoveImmediate() {
        TsvetokAssembler assembler = new TsvetokAssembler("bouj 12");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b01001100, result[6]);
    }

    @Test
    public void test_assembleHandlesNoOp() {
        TsvetokAssembler assembler = new TsvetokAssembler("noup");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b00000000, result[6]);
    }

    @Test
    public void test_assembleHandlesLoad() {
        TsvetokAssembler assembler = new TsvetokAssembler("ld 3");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b00011100, result[6]);
    }

    @Test
    public void test_assembleHandlesStore() {
        TsvetokAssembler assembler = new TsvetokAssembler("stou #0");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b00100000, result[6]);
    }
}