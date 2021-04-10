package usa.lafleur.cincospenguinos.machine;

import org.junit.Test;
import usa.lafleur.cincospenguinos.assembler.TsvetokAssembler;

import static org.junit.Assert.*;

public class TsvetokMachineTest {
    private final TsvetokAssembler assembler = new TsvetokAssembler();

    @Test
    public void test_programWorksIGuess() {
        String assemblyCode = "# Let's write a simple program that does some fancy stuff\n" +
                "   boujf 3\n" +
                "   boujr $ak $rej5\n" +
                "   boujf 0\n" +
                ".loop\n" +
                "   adf 1\n" +
                "   boujr $ak $rej4\n" +
                "   boujr $ak $rej3\n" +
                "   nultf -1\n" +
                "   adr $ak $rej4\n" +
                "   jnpns .end\n" +
                "# YO YO YIGGITY YOLOOOOOO\n" +
                "   boujr $rej3 $ak\n" +
                "   jnp .loop\n" +
                ".end # This is the end for you my friend\n" +
                "   stoup # I won't forgive, I won't forget\n"
                ;
        TsvetokExecutable executable = assembler.assemble(assemblyCode);
        TsvetokMachine machine = new TsvetokMachine(executable);
        machine.execute();
        assertEquals(3, machine.valueInRegister("$rej5"));
    }
}