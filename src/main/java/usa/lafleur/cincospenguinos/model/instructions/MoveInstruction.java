package usa.lafleur.cincospenguinos.model.instructions;

public class MoveInstruction extends Instruction {
    public MoveInstruction(byte raw) {
        super(raw);
    }

    public void execute(byte[] memory, byte[] registerArray) {
        if (opFlagSet()) {
            registerArray[Instruction.ACCUMULATOR_REGISTER_INDEX] = getImmediate();
        } else {
            registerArray[secondRegisterIndex()] = registerArray[firstRegisterIndex()];
        }
    }
}
