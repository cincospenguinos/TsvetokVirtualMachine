package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RegisterArray;

public class AddInstruction extends TsvetokInstruction {
    public AddInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    public void execute(RegisterArray registerArray) {
        if ((getOperationByte() >> 4) == OpCodes.ADD_IMMEDIATE) {
            byte value = registerArray.getValueOf(0);
            byte endValue = (byte) (value + getParameterByte());
            registerArray.setValueOf(0, endValue);
        } else {}
    }
}
