package usa.lafleur.cincospenguinos.machine.instructions;

import usa.lafleur.cincospenguinos.machine.RandomAccessMemory;
import usa.lafleur.cincospenguinos.machine.RegisterArray;

public class NoOperationInstruction extends TsvetokInstruction {
    public NoOperationInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    public void execute(RegisterArray registerArray, RandomAccessMemory memory) {}
}
