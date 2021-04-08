package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.assembler.RegisterResolutionService;
import usa.lafleur.cincospenguinos.model.RegisterArray;

public class AddInstruction extends TsvetokInstruction {
    public AddInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    public void execute(RegisterArray registerArray) {
        int accumulatorIndex = RegisterResolutionService.resolveRegister(
                RegisterResolutionService.ACCUMULATOR_REGISTER_NAME
        );

        if (addImmediate()) {
            byte value = registerArray.getValueOf(accumulatorIndex);
            byte endValue = (byte) (value + getParameterByte());
            registerArray.setValueOf(accumulatorIndex, endValue);
        } else {
            int leftIndex = (getParameterByte() & 0xf0) >> 4;
            int rightIndex = (getParameterByte() & 0x0f) >> 4;

            int leftValue = registerArray.getValueOf(leftIndex);
            int rightValue = registerArray.getValueOf(rightIndex);
            byte endValue = (byte) (leftValue + rightValue);
            registerArray.setValueOf(accumulatorIndex, endValue);
        }
    }

    private boolean addImmediate() {
        return (getOperationByte() >> 4) == OpCodes.ADD_IMMEDIATE;
    }
}
