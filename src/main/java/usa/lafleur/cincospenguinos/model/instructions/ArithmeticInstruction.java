package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RegisterArray;

public abstract class ArithmeticInstruction extends Instruction {
    public ArithmeticInstruction(byte raw) {
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

    protected abstract int performOperation(byte left, byte right);
}
