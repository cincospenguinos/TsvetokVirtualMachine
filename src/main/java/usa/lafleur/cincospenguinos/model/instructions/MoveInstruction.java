package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RegisterArray;

public class MoveInstruction extends Instruction {
    public MoveInstruction(byte raw) {
        super(raw);
    }

    public void execute(byte[] memory, RegisterArray registerArray) {
        if (opFlagSet()) {
            registerArray.setRegister(RegisterArray.ACCUMULATOR_INDEX, getImmediate());
        } else {
            byte value = registerArray.getRegister(firstRegisterIndex());
            registerArray.setRegister(secondRegisterIndex(), value);
        }
    }
}
