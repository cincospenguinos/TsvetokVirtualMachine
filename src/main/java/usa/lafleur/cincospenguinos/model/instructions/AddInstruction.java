package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RandomAccessMemory;
import usa.lafleur.cincospenguinos.model.RegisterArray;

public class AddInstruction extends ArithmeticInstruction {
    public AddInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    protected int executeImmediate(RegisterArray registerArray, RandomAccessMemory memory) {
        byte value = registerArray.getValueOf(ACCUMULATOR_INDEX);

        return value + getParameterByte();
    }

    @Override
    protected int executeRegister(RegisterArray registerArray, RandomAccessMemory memory) {
        int leftValue = registerArray.getValueOf(leftRegisterIndex());
        int rightValue = registerArray.getValueOf(rightRegisterIndex());

        return leftValue + rightValue;
    }
}
