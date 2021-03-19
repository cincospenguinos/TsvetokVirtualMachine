package usa.lafleur.cincospenguinos;

import org.junit.Test;

import static org.junit.Assert.*;

public class TsvetokMachineTest {
    private TsvetokExecutable headerExecutable = new TsvetokExecutable(TsvetokExecutable.VALID_HEADER);

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
}