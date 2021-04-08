package usa.lafleur.cincospenguinos.model;

import usa.lafleur.cincospenguinos.assembler.RegisterResolutionService;
import usa.lafleur.cincospenguinos.assembler.TsvetokExecutable;
import usa.lafleur.cincospenguinos.model.instructions.TsvetokInstruction;

public class TsvetokMachine {
    private TsvetokExecutable _executable;
    private RegisterArray registerArray;

    public TsvetokMachine(TsvetokExecutable executable) {
        _executable = executable;
        registerArray = new RegisterArray();
    }

    public void execute() {
        for (TsvetokInstruction instruction : _executable.getInstructions()) {
            instruction.execute(registerArray);
        }
    }

    public int valueInRegister(String registerName) {
        int registerIndex = RegisterResolutionService.resolveRegister(registerName);
        return registerArray.getValueOf(registerIndex);
    }
}
