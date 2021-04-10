package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.assembler.RegisterResolutionService;
import usa.lafleur.cincospenguinos.model.RandomAccessMemory;
import usa.lafleur.cincospenguinos.model.RegisterArray;

public class MemoryAccessInstruction extends TsvetokInstruction {
    private enum AccessType {
        LOAD, STORE, MOVE_VALUE, MOVE_REGISTER;
    }

    private static final int ACCUMULATOR_INDEX = RegisterResolutionService.resolveRegister("$ak");

    public MemoryAccessInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    public void execute(RegisterArray registerArray, RandomAccessMemory memory) {
        AccessType accessType = getAccessType();

        int upperAddressRegisterIndex = (getParameterByte() & 0xf0) >> 4;
        int lowerAddressRegisterIndex = getParameterByte() & 0x0f;

        byte upperAddress = registerArray.getValueOf(upperAddressRegisterIndex);
        byte lowerAddress = registerArray.getValueOf(lowerAddressRegisterIndex);

        if (accessType == AccessType.LOAD) {
            byte value = memory.readAt(upperAddress, lowerAddress);
            registerArray.setValueOf(ACCUMULATOR_INDEX, value);
        } else if (accessType == AccessType.STORE) {
            byte value = registerArray.getValueOf(ACCUMULATOR_INDEX);
            memory.writeTo(upperAddress, lowerAddress, value);
        }
    }

    private AccessType getAccessType() {
        boolean storeFlagSet = firstFlagSet();

        if (storeFlagSet) {
            return AccessType.STORE;
        }
        return AccessType.LOAD;
    }
}
