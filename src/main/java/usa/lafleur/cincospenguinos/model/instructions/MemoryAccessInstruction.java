package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RegisterArray;

public class MemoryAccessInstruction extends Instruction {
    public MemoryAccessInstruction(byte raw) {
        super(raw);
    }

    @Override
    public void execute(byte[] memory, RegisterArray registerArray) {
        byte upper = registerArray.getRegister(firstRegisterIndex());
        byte lower = registerArray.getRegister(secondRegisterIndex());
        int memoryAddress = (upper << 8) | lower;

        if (opFlagSet()) {
            memory[memoryAddress] = registerArray.getRegister(Instruction.ACCUMULATOR_REGISTER_INDEX);
        } else {
            byte value = memory[memoryAddress];
            registerArray.setRegister(Instruction.ACCUMULATOR_REGISTER_INDEX, value);
        }
    }
}
