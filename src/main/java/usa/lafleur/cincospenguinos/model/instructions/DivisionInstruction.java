package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RandomAccessMemory;
import usa.lafleur.cincospenguinos.model.RegisterArray;
import usa.lafleur.cincospenguinos.model.exceptions.DivisionByZeroException;

public class DivisionInstruction extends ArithmeticInstruction {
    public DivisionInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    protected int executeImmediate(RegisterArray registerArray, RandomAccessMemory memory) {
        byte value = registerArray.getValueOf(ACCUMULATOR_INDEX);

        try {
            return value / getParameterByte();
        } catch (ArithmeticException e) {
            throw new DivisionByZeroException();
        }
    }

    @Override
    protected int executeRegister(RegisterArray registerArray, RandomAccessMemory memory) {
        int leftValue = registerArray.getValueOf(leftRegisterIndex());
        int rightValue = registerArray.getValueOf(rightRegisterIndex());

        try {
            return leftValue / rightValue;
        } catch (ArithmeticException e) {
            throw new DivisionByZeroException();
        }
    }
}
