package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RegisterArray;
import usa.lafleur.cincospenguinos.model.instructions.Instruction;

public class JumpOnZeroInstruction extends Instruction {

    public JumpOnZeroInstruction(byte raw) {
        super(raw);
    }

    @Override
    public void execute(byte[] memory, RegisterArray registerArray) {
        if (registerArray.isSet(RegisterArray.ZERO_FLAG)) {
            byte left = registerArray.getRegister(firstRegisterIndex());
            byte right = registerArray.getRegister(secondRegisterIndex());
            short address = (short) ((left << 8) | right);
            registerArray.programCounter(address);
        }
    }
}
