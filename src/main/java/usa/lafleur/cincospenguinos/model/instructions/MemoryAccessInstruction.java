package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.assembler.RegisterResolutionService;
import usa.lafleur.cincospenguinos.core.Tuple;
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
        Tuple<Byte, Byte> address = getAddress(registerArray);

        if (accessType == AccessType.LOAD) {
            byte value = memory.readAt(address.getA(), address.getB());
            registerArray.setValueOf(ACCUMULATOR_INDEX, value);
        } else if (accessType == AccessType.STORE) {
            byte value = registerArray.getValueOf(ACCUMULATOR_INDEX);
            memory.writeTo(address.getA(), address.getB(), value);
        }
    }

    private AccessType getAccessType() {
        boolean storeFlagSet = firstFlagSet();

        if (storeFlagSet) {
            return AccessType.STORE;
        }
        return AccessType.LOAD;
    }

    private Tuple<Byte, Byte> getAddress(RegisterArray registerArray) {
        int upperAddressRegisterIndex = (getParameterByte() & 0xf0) >> 4;
        int lowerAddressRegisterIndex = getParameterByte() & 0x0f;

        byte upperAddress = registerArray.getValueOf(upperAddressRegisterIndex);
        byte lowerAddress = registerArray.getValueOf(lowerAddressRegisterIndex);

        return new Tuple<>(upperAddress, lowerAddress);
    }
}
