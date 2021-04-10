package usa.lafleur.cincospenguinos.assembler;

import org.junit.Test;
import usa.lafleur.cincospenguinos.assembler.exceptions.DuplicateLabelException;
import usa.lafleur.cincospenguinos.machine.TsvetokExecutable;

public class TsvetokExecutableTest {
    @Test(expected = DuplicateLabelException.class)
    public void test_executableCannotHaveCopiesOfLabels() {
        TsvetokExecutable executable = new TsvetokExecutable();
        executable.addLabelAtCurrentPosition(".main");
        executable.addLabelAtCurrentPosition(".main");
    }
}