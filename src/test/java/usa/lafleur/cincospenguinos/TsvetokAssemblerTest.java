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
        TsvetokAssembler assembler = new TsvetokAssembler("ld #3");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b00010011, result[6]);
    }

    @Test
    public void test_assembleHandlesStore() {
        TsvetokAssembler assembler = new TsvetokAssembler("stou #0");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b00100000, result[6]);
    }

    @Test
    public void test_assembleHandlesJump() {
        TsvetokAssembler assembler = new TsvetokAssembler("joump");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b00110000, result[6]);
    }

    @Test
    public void test_assembleHandlesAdd() {
        TsvetokAssembler assembler = new TsvetokAssembler("ad $rej1 $rej2");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b10000110, result[6]);
    }

    @Test
    public void test_assembleHandlesMultiply() {
        TsvetokAssembler assembler = new TsvetokAssembler("nult $rej3 $rej0");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b10011100, result[6]);
    }

    @Test
    public void test_assembleHandlesDivide() {
        TsvetokAssembler assembler = new TsvetokAssembler("dif $rej2 $rej1");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b10101001, result[6]);
    }

    @Test
    public void test_assembleHandlesBitwiseAnd() {
        TsvetokAssembler assembler = new TsvetokAssembler("et $rej2 $rej1");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b10111001, result[6]);
    }

    @Test
    public void test_assembleHandlesBitwiseOr() {
        TsvetokAssembler assembler = new TsvetokAssembler("our $rej2 $rej1");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b11001001, result[6]);
    }

    @Test
    public void test_assembleHandlesSignToggle() {
        TsvetokAssembler assembler = new TsvetokAssembler("toujl $rej1");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b11010100, result[6]);
    }
}