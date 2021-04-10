package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.assembler.RegisterResolutionService;
import usa.lafleur.cincospenguinos.core.Tuple;
import usa.lafleur.cincospenguinos.model.RandomAccessMemory;
import usa.lafleur.cincospenguinos.model.RegisterArray;

public class MemoryRegisterMoveInstruction extends TsvetokInstruction {
    private enum AccessType {
        LOAD, STORE, MOVE_VALUE, MOVE_REGISTER;

        boolean requiresMemory() {
            return this == LOAD || this == STORE;
        }
    }

    private static final int ACCUMULATOR_INDEX = RegisterResolutionService.resolveRegister("$ak");

    public MemoryRegisterMoveInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    public void execute(RegisterArray registerArray, RandomAccessMemory memory) {
        AccessType accessType = getAccessType();

        if (accessType.requiresMemory()) {
            handleMemoryExecution(accessType, registerArray, memory);
        } else if (accessType == AccessType.MOVE_REGISTER) {
            byte value = registerArray.getValueOf(leftRegisterIndex());
            registerArray.setValueOf(rightRegisterIndex(), value);
        } else {
            byte value = getParameterByte();
            registerArray.setValueOf(ACCUMULATOR_INDEX, value);
        }
    }

    private AccessType getAccessType() {
        boolean storeFlagSet = firstFlagSet();
        boolean valueRegisterFlagSet = secondFlagSet();

        if (!storeFlagSet && !valueRegisterFlagSet) {
            return AccessType.LOAD;
        }

        if (storeFlagSet && !valueRegisterFlagSet) {
            return AccessType.STORE;
        }

        if (!storeFlagSet) {
            return AccessType.MOVE_REGISTER;
        }

        return AccessType.MOVE_VALUE;
    }

    private void handleMemoryExecution(AccessType accessType, RegisterArray registerArray, RandomAccessMemory memory) {
        Tuple<Byte, Byte> address = getAddress(registerArray);

        if (accessType == AccessType.LOAD) {
            byte value = memory.readAt(address.getA(), address.getB());
            registerArray.setValueOf(ACCUMULATOR_INDEX, value);
        } else if (accessType == AccessType.STORE) {
            byte value = registerArray.getValueOf(ACCUMULATOR_INDEX);
            memory.writeTo(address.getA(), address.getB(), value);
        }
    }

    private Tuple<Byte, Byte> getAddress(RegisterArray registerArray) {
        int upperAddressRegisterIndex = (getParameterByte() & 0xf0) >> 4;
        int lowerAddressRegisterIndex = getParameterByte() & 0x0f;

        byte upperAddress = registerArray.getValueOf(upperAddressRegisterIndex);
        byte lowerAddress = registerArray.getValueOf(lowerAddressRegisterIndex);

        return new Tuple<>(upperAddress, lowerAddress);
    }
}
