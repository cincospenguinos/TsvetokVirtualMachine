package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.assembler.RegisterResolutionService;
import usa.lafleur.cincospenguinos.model.RandomAccessMemory;
import usa.lafleur.cincospenguinos.model.RegisterArray;

public abstract class ArithmeticInstruction extends TsvetokInstruction {
    private static final int[] ARITHMETIC_OPCODES = { OpCodes.ADD_IMMEDIATE,
            OpCodes.MULTIPLY_IMMEDIATE, OpCodes.DIVIDE_IMMEDIATE
    };

    protected static final int ACCUMULATOR_INDEX = RegisterResolutionService.resolveRegister(
            RegisterResolutionService.ACCUMULATOR_REGISTER_NAME
    );

    public ArithmeticInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    public void execute(RegisterArray registerArray, RandomAccessMemory memory) {
        int endValue;

        if (isImmediate()) {
            endValue = executeImmediate(registerArray, memory);
        } else {
            endValue = executeRegister(registerArray, memory);
        }

        setFlags(endValue, registerArray);
        registerArray.setValueOf(ACCUMULATOR_INDEX, (byte) endValue);
    }

    protected abstract int executeImmediate(RegisterArray registerArray, RandomAccessMemory memory);

    protected abstract int executeRegister(RegisterArray registerArray, RandomAccessMemory memory);

    protected boolean isImmediate() {
        int opcode = getOperationByte() >> 4;

        for (int arithmeticCode : ARITHMETIC_OPCODES) {
            if (opcode == arithmeticCode) {
                return true;
            }
        }

        return false;
    }

    protected void setFlags(int endValue, RegisterArray registerArray) {
        registerArray.setOverflowFlag(endValue > 127);
        registerArray.setZeroFlag(endValue == 0);
    }
}
