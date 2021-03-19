package usa.lafleur.cincospenguinos.model.instructions;

public class MemoryAccessInstruction extends Instruction {
    public MemoryAccessInstruction(byte raw) {
        super(raw);
    }

    @Override
    public void execute(byte[] memory, byte[] registerArray) {
        if (opFlagSet()) {

        } else {
            byte upper = registerArray[firstRegisterIndex()];
            byte lower = registerArray[secondRegisterIndex()];
            int memoryAddress = (upper << 8) | lower;
            byte value = memory[memoryAddress];
            registerArray[Instruction.ACCUMULATOR_REGISTER_INDEX] = value;
        }
    }
}
