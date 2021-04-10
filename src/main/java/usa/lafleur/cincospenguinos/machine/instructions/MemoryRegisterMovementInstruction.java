package usa.lafleur.cincospenguinos.machine.instructions;

import usa.lafleur.cincospenguinos.assembler.RegisterResolutionService;
import usa.lafleur.cincospenguinos.core.Tuple;
import usa.lafleur.cincospenguinos.machine.RandomAccessMemory;
import usa.lafleur.cincospenguinos.machine.RegisterArray;

public class MemoryRegisterMovementInstruction extends TsvetokInstruction {
    private enum MemoryRegisterAccessType {
        LOAD, STORE, MOVE_VALUE, MOVE_REGISTER;

        boolean requiresMemory() {
            return this == LOAD || this == STORE;
        }
    }

    private static final int ACCUMULATOR_INDEX = RegisterResolutionService.resolveRegister("$ak");

    public MemoryRegisterMovementInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    public void execute(RegisterArray registerArray, RandomAccessMemory memory) {
        MemoryRegisterAccessType accessType = getAccessType();

        if (accessType.requiresMemory()) {
            handleMemoryExecution(accessType, registerArray, memory);
        } else if (accessType == MemoryRegisterAccessType.MOVE_REGISTER) {
            byte value = registerArray.getValueOf(leftRegisterIndex());
            registerArray.setValueOf(rightRegisterIndex(), value);
        } else {
            byte value = getParameterByte();
            registerArray.setValueOf(ACCUMULATOR_INDEX, value);
        }
    }

    private MemoryRegisterAccessType getAccessType() {
        boolean storeFlagSet = firstFlagSet();
        boolean valueRegisterFlagSet = secondFlagSet();

        if (!storeFlagSet && !valueRegisterFlagSet) {
            return MemoryRegisterAccessType.LOAD;
        }

        if (storeFlagSet && !valueRegisterFlagSet) {
            return MemoryRegisterAccessType.STORE;
        }

        if (!storeFlagSet) {
            return MemoryRegisterAccessType.MOVE_REGISTER;
        }

        return MemoryRegisterAccessType.MOVE_VALUE;
    }

    private void handleMemoryExecution(MemoryRegisterAccessType accessType, RegisterArray registerArray, RandomAccessMemory memory) {
        Tuple<Byte, Byte> address = getAddress(registerArray);

        if (accessType == MemoryRegisterAccessType.LOAD) {
            byte value = memory.readAt(address.getA(), address.getB());
            registerArray.setValueOf(ACCUMULATOR_INDEX, value);
        } else if (accessType == MemoryRegisterAccessType.STORE) {
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
