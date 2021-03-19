package usa.lafleur.cincospenguinos;

import org.junit.Test;
import usa.lafleur.cincospenguinos.model.TsvetokExecutable;

import static org.junit.Assert.*;

public class TsvetokExecutableTest {
    private static final byte TSVETOK_T = 0x54;
    private static final byte TSVETOK_V = 0x56;
    private static final byte TSVETOK_M = 0x4d;

    @Test
    public void test_isValidRequiresTVMBytes() {
        byte[] inputBytes = { 0x54, 0x01, 0x2f, 97, 71, 12 };
        assertFalse(new TsvetokExecutable(inputBytes).isValid());
    }

    @Test
    public void test_isValidRequiresSchoolNumber() {
        byte[] inputBytes = { TSVETOK_T, TSVETOK_V, TSVETOK_M, 0x61, 0x47, 0x08 };
        assertFalse(new TsvetokExecutable(inputBytes).isValid());
    }

    @Test
    public void test_isValidWithFirstSixCorrectBytes() {
        byte[] inputBytes = { TSVETOK_T, TSVETOK_V, TSVETOK_M, 97, 71, 12 };
        assertTrue(new TsvetokExecutable(inputBytes).isValid());
    }

    @Test
    public void test_cannotHaveMoreThan2toThe16Bytes() {
        byte[] inputBytes = new byte[0xffff + 1];
        System.arraycopy(TsvetokExecutable.VALID_HEADER, 0, inputBytes, 0, 6);
        assertFalse(new TsvetokExecutable(inputBytes).isValid());
    }
}