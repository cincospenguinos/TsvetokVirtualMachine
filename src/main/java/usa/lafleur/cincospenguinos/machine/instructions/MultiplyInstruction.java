package usa.lafleur.cincospenguinos.machine.instructions;

import usa.lafleur.cincospenguinos.machine.RandomAccessMemory;
import usa.lafleur.cincospenguinos.machine.RegisterArray;

public class MultiplyInstruction extends ArithmeticInstruction {
    public MultiplyInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    protected int executeImmediate(RegisterArray registerArray, RandomAccessMemory memory) {
        byte value = registerArray.getValueOf(ACCUMULATOR_INDEX);

        return value * getParameterByte();
    }

    @Override
    protected int executeRegister(RegisterArray registerArray, RandomAccessMemory memory) {
        int leftValue = registerArray.getValueOf(leftRegisterIndex());
        int rightValue = registerArray.getValueOf(rightRegisterIndex());

        return leftValue * rightValue;
    }
}
