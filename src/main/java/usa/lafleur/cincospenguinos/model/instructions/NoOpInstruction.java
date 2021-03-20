package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RegisterArray;
import usa.lafleur.cincospenguinos.model.TsvetokExecutable;

public class NoOpInstruction extends Instruction {
    public NoOpInstruction(byte raw) {
        super(raw);
    }

    @Override
    public void execute(byte[] memory, RegisterArray registerArray) {}
}
