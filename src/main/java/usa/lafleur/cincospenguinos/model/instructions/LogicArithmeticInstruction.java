package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RegisterArray;

public abstract class LogicArithmeticInstruction extends Instruction {
    public LogicArithmeticInstruction(byte raw) {
        super(raw);
    }

    @Override
    public void execute(byte[] memory, RegisterArray registerArray) {
        byte first, second;

        if (immediateFlagSet()) {
            first = registerArray.accumulator();
            second = getImmediate();
        } else {
            first = registerArray.getRegister(firstRegisterIndex());
            second = registerArray.getRegister(secondRegisterIndex());
        }

        int result = performOperation(first, second);
        setFlags(result, registerArray);
        registerArray.accumulator((byte) result);
    }

    private boolean immediateFlagSet() {
        return opFlagSet();
    }

    private void setFlags(int operationResult, RegisterArray registerArray) {
        if (operationResult >= 127) {
            registerArray.setFlag(RegisterArray.OVERFLOW_FLAG);
        }

        if ((operationResult & 0xff) == 0) {
            registerArray.setFlag(RegisterArray.ZERO_FLAG);
        }
    }

    protected abstract int performOperation(byte left, byte right);
}
