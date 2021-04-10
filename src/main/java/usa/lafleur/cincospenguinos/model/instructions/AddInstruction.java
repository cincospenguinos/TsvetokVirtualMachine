package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.assembler.RegisterResolutionService;
import usa.lafleur.cincospenguinos.model.RandomAccessMemory;
import usa.lafleur.cincospenguinos.model.RegisterArray;

public class AddInstruction extends TsvetokInstruction {
    public AddInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    public void execute(RegisterArray registerArray, RandomAccessMemory memory) {
        int endValue;
        int accumulatorIndex = RegisterResolutionService.resolveRegister(
                RegisterResolutionService.ACCUMULATOR_REGISTER_NAME
        );

        if (isAddImmediate()) {
            byte value = registerArray.getValueOf(accumulatorIndex);
            endValue = value + getParameterByte();
        } else {
            int leftValue = registerArray.getValueOf(leftRegisterIndex());
            int rightValue = registerArray.getValueOf(rightRegisterIndex());
            endValue = leftValue + rightValue;
        }

        registerArray.setOverflowFlag(endValue > 127);
        registerArray.setZeroFlag(endValue == 0);
        registerArray.setValueOf(accumulatorIndex, (byte) endValue);
    }

    private boolean isAddImmediate() {
        return (getOperationByte() >> 4) == OpCodes.ADD_IMMEDIATE;
    }
}
