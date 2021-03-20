package usa.lafleur.cincospenguinos.model.instructions;

public class AddInstruction extends Instruction {
    public AddInstruction(byte raw) {
        super(raw);
    }

    @Override
    public void execute(byte[] memory, byte[] registerArray) {
        byte first, second;

        if (opFlagSet()) {
            first = registerArray[Instruction.ACCUMULATOR_REGISTER_INDEX];
            second = getImmediate();
        } else {
            first = registerArray[firstRegisterIndex()];
            second = registerArray[secondRegisterIndex()];
        }

        int result = (first + second);

        if (result >= 128) {
            registerArray[4] |= (byte) 0b00000010;
        }

        if (result == 0) {
            registerArray[4] |= (byte) 0b00000100;
        }

        registerArray[Instruction.ACCUMULATOR_REGISTER_INDEX] = (byte) result;
    }
}
