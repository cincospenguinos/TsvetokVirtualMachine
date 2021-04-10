package usa.lafleur.cincospenguinos.model;

import usa.lafleur.cincospenguinos.assembler.RegisterResolutionService;
import usa.lafleur.cincospenguinos.assembler.TsvetokExecutable;
import usa.lafleur.cincospenguinos.model.instructions.TsvetokInstruction;

public class TsvetokMachine {
    private final TsvetokExecutable _executable;
    private final RegisterArray _registerArray;
    private final RandomAccessMemory _memory;

    public TsvetokMachine(TsvetokExecutable executable) {
        _executable = executable;
        _registerArray = new RegisterArray();
        _memory = new RandomAccessMemory();
    }

    public void execute() {
        for (TsvetokInstruction instruction : _executable.getInstructions()) {
            instruction.execute(_registerArray, _memory);
        }
    }

    public int valueInRegister(String registerName) {
        int registerIndex = RegisterResolutionService.resolveRegister(registerName);
        return _registerArray.getValueOf(registerIndex);
    }
}
