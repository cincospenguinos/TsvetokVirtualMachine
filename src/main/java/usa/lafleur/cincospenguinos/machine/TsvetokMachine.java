package usa.lafleur.cincospenguinos.machine;

import usa.lafleur.cincospenguinos.assembler.RegisterResolutionService;
import usa.lafleur.cincospenguinos.machine.instructions.TsvetokInstruction;

import java.util.List;

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
        List<TsvetokInstruction> program = _executable.getInstructions();

        while (true) {
            TsvetokInstruction instruction = program.get(_registerArray.getProgramCounter());
            instruction.execute(_registerArray, _memory);

            if (instruction.endExecution()) {
                break;
            }

            if (instruction.incrementProgramCounter()) {
                _registerArray.incrementProgramCounter();
            }
        }
    }

    public int valueInRegister(String registerName) {
        int registerIndex = RegisterResolutionService.resolveRegister(registerName);
        return _registerArray.getValueOf(registerIndex);
    }
}
