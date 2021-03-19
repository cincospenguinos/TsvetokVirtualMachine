package usa.lafleur.cincospenguinos;

import org.junit.Test;
import usa.lafleur.cincospenguinos.assembler.TsvetokAssembler;

import static org.junit.Assert.*;

public class TsvetokAssemblerTest {

    @Test
    public void test_assembleHandlesNoOp() {
        TsvetokAssembler assembler = new TsvetokAssembler("noup");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b00000000, result[6]);
    }

    @Test
    public void test_assembleHandlesAdd() {
        TsvetokAssembler assembler = new TsvetokAssembler("ad $rej1 $rej2");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b01100110, result[6]);
    }

    @Test
    public void test_assembleHandlesMultiply() {
        TsvetokAssembler assembler = new TsvetokAssembler("nult $rej3 $rej0");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b10001100, result[6]);
    }

    @Test
    public void test_assembleHandlesDivide() {
        TsvetokAssembler assembler = new TsvetokAssembler("dif $rej2 $rej1");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b10101001, result[6]);
    }
}