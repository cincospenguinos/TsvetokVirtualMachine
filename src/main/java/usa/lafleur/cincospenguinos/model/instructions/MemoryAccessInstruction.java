package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.assembler.RegisterResolutionService;
import usa.lafleur.cincospenguinos.model.RandomAccessMemory;
import usa.lafleur.cincospenguinos.model.RegisterArray;

public class MemoryAccessInstruction extends TsvetokInstruction {
    private static final int ACCUMULATOR_INDEX = RegisterResolutionService.resolveRegister("$ak");

    public MemoryAccessInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    public void execute(RegisterArray registerArray, RandomAccessMemory memory) {
        int upperAddressRegisterIndex = (getParameterByte() & 0xf0) >> 4;
        int lowerAddressRegisterIndex = getParameterByte() & 0x0f;

        byte upperAddress = registerArray.getValueOf(upperAddressRegisterIndex);
        byte lowerAddress = registerArray.getValueOf(lowerAddressRegisterIndex);
        byte value = memory.readAt(upperAddress, lowerAddress);

        registerArray.setValueOf(ACCUMULATOR_INDEX, value);
    }
}
