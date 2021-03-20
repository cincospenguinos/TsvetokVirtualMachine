package usa.lafleur.cincospenguinos;

import usa.lafleur.cincospenguinos.assembler.TsvetokAssembler;
import usa.lafleur.cincospenguinos.model.TsvetokExecutable;
import usa.lafleur.cincospenguinos.model.TsvetokMachine;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        TsvetokAssembler assembler = new TsvetokAssembler(new File("example.ta"));
        TsvetokExecutable executable = new TsvetokExecutable(assembler.assemble().toByteArray());
        TsvetokMachine machine = new TsvetokMachine(executable);
        machine.execute();
    }
}
