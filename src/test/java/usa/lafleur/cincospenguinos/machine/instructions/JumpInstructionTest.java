package usa.lafleur.cincospenguinos.machine.instructions;

import org.junit.Test;
import usa.lafleur.cincospenguinos.assembler.TsvetokAssembler;
import usa.lafleur.cincospenguinos.machine.RegisterArray;
import usa.lafleur.cincospenguinos.machine.TsvetokExecutable;

import static org.junit.Assert.*;

public class JumpInstructionTest {
    private final TsvetokAssembler assembler = new TsvetokAssembler();

    @Test
    public void test_jumpUpdatesWithLabel() {
        TsvetokExecutable executable = assembler.assemble("boujf 12\n\t.main\n\tjnp .main");
        RegisterArray registerArray = new RegisterArray();
        assertEquals(0, registerArray.getProgramCounter());
        executable.getInstructions().get(1).execute(registerArray, null);
        assertEquals(1, registerArray.getProgramCounter());
    }

    @Test
    public void test_jumpOnZeroUpdatesWithLabel() {
        TsvetokExecutable executable = assembler.assemble("boujf 12\n\t.main\n\tjnps .main");
        RegisterArray registerArray = new RegisterArray();
        registerArray.setZeroFlag(true);
        assertEquals(0, registerArray.getProgramCounter());
        executable.getInstructions().get(1).execute(registerArray, null);
        assertEquals(1, registerArray.getProgramCounter());
    }

    @Test
    public void test_jumpOnZeroDoesNotUpdateWhenZeroFlagIsFalse() {
        TsvetokExecutable executable = assembler.assemble("boujf 12\n\t.main\n\tjnps .main");
        RegisterArray registerArray = new RegisterArray();
        registerArray.setZeroFlag(false);
        assertEquals(0, registerArray.getProgramCounter());
        executable.getInstructions().get(1).execute(registerArray, null);
        assertEquals(0, registerArray.getProgramCounter());
    }
}