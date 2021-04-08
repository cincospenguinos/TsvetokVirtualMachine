package usa.lafleur.cincospenguinos.model;

import usa.lafleur.cincospenguinos.assembler.TsvetokExecutable;
import usa.lafleur.cincospenguinos.model.instructions.TsvetokInstruction;

public class TsvetokMachine {
    private TsvetokExecutable _executable;
    private byte[] registerArray;

    public TsvetokMachine(TsvetokExecutable executable) {
        _executable = executable;
        registerArray = new byte[16];
    }

    public void execute() {
        for (TsvetokInstruction instruction : _executable.getInstructions()) {
            instruction.execute(registerArray);
        }
    }

    public int valueInRegister(String registerName) {
        return registerArray[0];
    }
}
