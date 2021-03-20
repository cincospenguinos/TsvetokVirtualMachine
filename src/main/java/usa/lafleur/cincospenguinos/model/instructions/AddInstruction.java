package usa.lafleur.cincospenguinos.model.instructions;

public class AddInstruction extends Instruction {
    public AddInstruction(byte raw) {
        super(raw);
    }

    @Override
    public void execute(byte[] memory, byte[] registerArray) {
        RegisterArray array = new RegisterArray(registerArray);
        byte first, second;

        if (opFlagSet()) {
            first = array.getRegister(RegisterArray.ACCUMULATOR_INDEX);
            second = getImmediate();
        } else {
            first = array.getRegister(firstRegisterIndex());
            second = array.getRegister(secondRegisterIndex());
        }

        int result = (first + second);

        if (result >= 128) {
            array.setFlag(RegisterArray.OVERFLOW_FLAG);
        }

        if (result == 0) {
            array.setFlag(RegisterArray.ZERO_FLAG);
        }

        array.setRegister(RegisterArray.ACCUMULATOR_INDEX, (byte) result);
        registerArray = array.getArray();
    }
}
