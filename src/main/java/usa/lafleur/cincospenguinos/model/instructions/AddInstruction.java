package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RegisterArray;

public class AddInstruction extends Instruction {
    public AddInstruction(byte raw) {
        super(raw);
    }

    @Override
    public void execute(byte[] memory, RegisterArray registerArray) {
        byte first, second;

        if (opFlagSet()) {
            first = registerArray.getRegister(RegisterArray.ACCUMULATOR_INDEX);
            second = getImmediate();
        } else {
            first = registerArray.getRegister(firstRegisterIndex());
            second = registerArray.getRegister(secondRegisterIndex());
        }

        int result = (first + second);

        if (result >= 128) {
            registerArray.setFlag(RegisterArray.OVERFLOW_FLAG);
        }

        if ((result & 0xff) == 0) {
            registerArray.setFlag(RegisterArray.ZERO_FLAG);
        }

        registerArray.setRegister(RegisterArray.ACCUMULATOR_INDEX, (byte) result);
    }
}
