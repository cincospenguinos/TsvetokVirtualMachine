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
}