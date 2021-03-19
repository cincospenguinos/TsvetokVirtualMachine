package usa.lafleur.cincospenguinos;

import org.junit.Test;

import static org.junit.Assert.*;

public class TsvetokMachineTest {
    private final TsvetokExecutable headerExecutable = new TsvetokExecutable(TsvetokExecutable.VALID_HEADER);

    @Test
    public void test_executePermitsJustHeaderExecutable() {
        TsvetokMachine machine = new TsvetokMachine(headerExecutable);
        assertEquals(machine.execute(), TsvetokMachine.RETURN_CODE_OK);
    }

    @Test
    public void test_executeReturnsCodeBadFile() {
        TsvetokMachine machine = new TsvetokMachine(new TsvetokExecutable(new byte[] { 1, 1, 1, 1 }));
        assertEquals(machine.execute(), TsvetokMachine.RETURN_CODE_BAD_EXECUTABLE);
    }

    @Test
    public void test_executeHandlesLoad() {
        TsvetokExecutable executable = new TsvetokExecutable(new byte[] {
            0x54, 0x56, 0x4d, 97, 71, 12,
            0b01001111, // Move the immediate value of 15 into the first register
        });
        TsvetokMachine machine = new TsvetokMachine(executable);
        machine.execute();
        assertEquals((byte) 0x0f, machine.valueInRegister(0x00));
    }
}