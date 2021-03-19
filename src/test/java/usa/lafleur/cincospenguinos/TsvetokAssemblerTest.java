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
    public void test_assembleHandlesDivide() {
        TsvetokAssembler assembler = new TsvetokAssembler("dif $rej2 $rej1");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b11101001, result[6]);
    }

    @Test
    public void test_assembleHandlesSystemCall() {
        TsvetokAssembler assembler = new TsvetokAssembler("sis 2");
        byte[] result = assembler.assemble().toByteArray();
        assertEquals((byte) 0b00010010, result[6]);
    }
}