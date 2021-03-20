package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RegisterArray;
import usa.lafleur.cincospenguinos.model.TsvetokExecutable;

public class HaltInstruction extends Instruction {
    public HaltInstruction(byte raw) {
        super(raw);
    }

    @Override
    public void execute(byte[] memory, RegisterArray registerArray) {}

    public boolean shouldHalt() {
        return true;
    }
}
