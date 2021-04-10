package usa.lafleur.cincospenguinos;

import usa.lafleur.cincospenguinos.assembler.TsvetokAssembler;
import usa.lafleur.cincospenguinos.assembler.TsvetokExecutable;
import usa.lafleur.cincospenguinos.model.TsvetokMachine;

public class Main {
    public static void main(String[] args) {
        String program =
                "boujf 5\n" +
                "boujr $ak $tnp0\n" +
                "boujf 10\n" +
                "adr $ak $tnp0";
        TsvetokAssembler assembler = new TsvetokAssembler();
        TsvetokExecutable executable = assembler.assemble(program);
        TsvetokMachine machine = new TsvetokMachine(executable);
        machine.execute();
    }
}
