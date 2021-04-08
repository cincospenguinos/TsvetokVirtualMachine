package usa.lafleur.cincospenguinos.model.instructions;

public class AddInstruction extends TsvetokInstruction {
    public AddInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    public void execute(byte[] registerArray) {
        if ((getOperationByte() >> 4) == OpCodes.ADD_IMMEDIATE) {
            registerArray[0] += getParameterByte();
        } else {

        }
    }
}
