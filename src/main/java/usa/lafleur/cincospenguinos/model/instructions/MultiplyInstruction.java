package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RegisterArray;

public class MultiplyInstruction extends Instruction {
    public MultiplyInstruction(byte raw) {
        super(raw);
    }

    @Override
    public void execute(byte[] memory, RegisterArray registerArray) {
        byte first, second;

        if (opFlagSet()) {
            first = registerArray.accumulator();
            second = getImmediate();
        } else {
            first = registerArray.getRegister(firstRegisterIndex());
            second = registerArray.getRegister(secondRegisterIndex());
        }

        int result = (first * second);
        registerArray.setRegister(RegisterArray.ACCUMULATOR_INDEX, (byte) result);
    }
}
