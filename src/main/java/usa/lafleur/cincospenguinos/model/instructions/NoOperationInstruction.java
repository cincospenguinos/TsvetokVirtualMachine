package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RandomAccessMemory;
import usa.lafleur.cincospenguinos.model.RegisterArray;

public class NoOperationInstruction extends TsvetokInstruction {
    public NoOperationInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    public void execute(RegisterArray registerArray, RandomAccessMemory memory) {}
}
