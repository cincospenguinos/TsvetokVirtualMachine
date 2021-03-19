package usa.lafleur.cincospenguinos.model.instructions;

public class AddInstruction extends Instruction {
    public AddInstruction(byte raw) {
        super(raw);
    }

    @Override
    public void execute(byte[] memory, byte[] registerArray) {
        if (opFlagSet()) {
            byte immediate = getImmediate();
            registerArray[Instruction.ACCUMULATOR_REGISTER_INDEX] += immediate;
        } else {
            byte first = registerArray[firstRegisterIndex()];
            byte second = registerArray[secondRegisterIndex()];

            // TODO: Carry bit?
            // TODO: Zero bit?
            byte result = (byte) (first + second);
            registerArray[Instruction.ACCUMULATOR_REGISTER_INDEX] = result;
        }
    }
}
