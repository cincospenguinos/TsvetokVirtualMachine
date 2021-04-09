package usa.lafleur.cincospenguinos.model;

import usa.lafleur.cincospenguinos.assembler.RegisterResolutionService;
import usa.lafleur.cincospenguinos.assembler.TsvetokExecutable;
import usa.lafleur.cincospenguinos.model.instructions.TsvetokInstruction;

public class TsvetokMachine {
    private TsvetokExecutable _executable;
    private RegisterArray registerArray;
    private RandomAccessMemory memory;

    public TsvetokMachine(TsvetokExecutable executable) {
        _executable = executable;
        registerArray = new RegisterArray();
        memory = new RandomAccessMemory();
    }

    public void execute() {
        for (TsvetokInstruction instruction : _executable.getInstructions()) {
            instruction.execute(registerArray, memory);
        }
    }

    public int valueInRegister(String registerName) {
        int registerIndex = RegisterResolutionService.resolveRegister(registerName);
        return registerArray.getValueOf(registerIndex);
    }
}
