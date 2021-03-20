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
    public void test_assembleHandlesSystemCall() {
        TsvetokAssembler assembler = new TsvetokAssembler("sis 2");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b00010010, result[6]);
    }

    @Test
    public void test_assembleHandlesMemoryRead() {
        TsvetokAssembler assembler = new TsvetokAssembler("nens $rej2 $rej1");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b00101001, result[6]);
    }

    @Test
    public void test_assembleHandlesMemoryWrite() {
        TsvetokAssembler assembler = new TsvetokAssembler("nensou $ak $rej2");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b00110010, result[6]);
    }

    @Test
    public void test_assembleHandlesMoveRegister() {
        TsvetokAssembler assembler = new TsvetokAssembler("bouj $ak $rej2");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b10000010, result[6]);
    }

    @Test
    public void test_assembleHandlesMoveImmediate() {
        TsvetokAssembler assembler = new TsvetokAssembler("bouj 13");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b10011101, result[6]);
    }

    @Test
    public void test_assembleHandlesAddRegisters() {
        TsvetokAssembler assembler = new TsvetokAssembler("ad $rej1 $rej2");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b01000110, result[6]);
    }

    @Test
    public void test_assembleHandlesAddImmediate() {
        TsvetokAssembler assembler = new TsvetokAssembler("ad 9");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b01011001, result[6]);
    }

    @Test
    public void test_assembleHandlesMultiplyRegisters() {
        TsvetokAssembler assembler = new TsvetokAssembler("nult $retr $ak");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b01101100, result[6]);
    }

    @Test
    public void test_assembleHandlesMultiplyImmediate() {
        TsvetokAssembler assembler = new TsvetokAssembler("nult 12");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b01111100, result[6]);
    }

    @Test
    public void test_assembleHandlesDivideWithRegisters() {
        TsvetokAssembler assembler = new TsvetokAssembler("dif $rej2 $rej1");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b11001001, result[6]);
    }

    @Test
    public void test_assembleHandlesDivideImmediate() {
        TsvetokAssembler assembler = new TsvetokAssembler("dif 2");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b11010010, result[6]);
    }

    @Test
    public void test_assembleHandlesLogicalAnd() {
        TsvetokAssembler assembler = new TsvetokAssembler("loujet $rej2 $rej1");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b10101001, result[6]);
    }

    @Test
    public void test_assembleHandlesLogicalOr() {
        TsvetokAssembler assembler = new TsvetokAssembler("loujour $rej2 $rej1");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b10111001, result[6]);
    }

    @Test
    public void test_assembleHandlesJumpIfZero() {
        TsvetokAssembler assembler = new TsvetokAssembler("jnps $ak $rej2");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b11100010, result[6]);
    }

    @Test
    public void test_assembleHandlesHalt() {
        TsvetokAssembler assembler = new TsvetokAssembler("stoup");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b11110000, result[6]);
    }

    @Test
    public void test_assembleHandlesCodeComments() {
        TsvetokAssembler assembler = new TsvetokAssembler("# This is a comment\nstoup");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b11110000, result[6]);
    }
}