package usa.lafleur.cincospenguinos.machine.instructions;

import usa.lafleur.cincospenguinos.machine.RandomAccessMemory;
import usa.lafleur.cincospenguinos.machine.RegisterArray;

public class HaltInstruction extends TsvetokInstruction {
    public HaltInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    public void execute(RegisterArray registerArray, RandomAccessMemory memory) {}
}
