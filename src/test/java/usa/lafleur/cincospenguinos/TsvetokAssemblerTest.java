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
    public void test_assembleHandlesMemoryRead() {
        TsvetokAssembler assembler = new TsvetokAssembler("nens #12");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b00101100, result[6]);
    }

    @Test
    public void test_assembleHandlesMemoryWrite() {
        TsvetokAssembler assembler = new TsvetokAssembler("nensou #12");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b00111100, result[6]);
    }

    @Test
    public void test_assembleHandlesMoveBetweenRegisters() {
        TsvetokAssembler assembler = new TsvetokAssembler("bouj $rej0 $rej3");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b01000011, result[6]);
    }

    @Test
    public void test_assembleHandlesMoveImmediate() {
        TsvetokAssembler assembler = new TsvetokAssembler("bouj 7");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b01010111, result[6]);
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

    @Test
    public void test_assembleHandlesSignToggle() {
        TsvetokAssembler assembler = new TsvetokAssembler("toug $rej2");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b11001000, result[6]);
    }

    @Test
    public void test_assembleHandlesSystemCall() {
        TsvetokAssembler assembler = new TsvetokAssembler("sis 2");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b11100010, result[6]);
    }
}