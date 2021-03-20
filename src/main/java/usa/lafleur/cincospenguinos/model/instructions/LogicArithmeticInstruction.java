package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RegisterArray;

public abstract class LogicArithmeticInstruction extends Instruction {
    public LogicArithmeticInstruction(byte raw) {
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

        int result = performOperation(first, second);

        if (result >= 127) {
            registerArray.setFlag(RegisterArray.OVERFLOW_FLAG);
        }

        if ((result & 0xff) == 0) {
            registerArray.setFlag(RegisterArray.ZERO_FLAG);
        }

        registerArray.setRegister(RegisterArray.ACCUMULATOR_INDEX, (byte) result);
    }

    protected abstract int performOperation(byte left, byte right);
}
