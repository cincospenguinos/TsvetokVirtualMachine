package usa.lafleur.cincospenguinos.model;

import org.junit.Test;
import usa.lafleur.cincospenguinos.assembler.TsvetokAssembler;
import usa.lafleur.cincospenguinos.assembler.TsvetokExecutable;

import static org.junit.Assert.*;

public class TsvetokMachineTest {
    private final TsvetokAssembler assembler = new TsvetokAssembler();

    @Test
    public void test_addInstruction() {
        TsvetokExecutable executable = assembler.assemble("adf 12");
        TsvetokMachine machine = new TsvetokMachine(executable);
        machine.execute();
        assertEquals(machine.valueInRegister("$ak"), 12);
    }
}