package usa.lafleur.cincospenguinos.model.instructions;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import usa.lafleur.cincospenguinos.core.Tuple;
import usa.lafleur.cincospenguinos.model.RandomAccessMemory;
import usa.lafleur.cincospenguinos.model.RegisterArray;

public abstract class TsvetokInstruction {
    private byte _operation;
    private byte _params;

    public TsvetokInstruction(byte operation, byte params) {
        _operation = operation;
        _params = params;
    }

    public static TsvetokInstruction construct(byte operation, byte params) {
        int opcode = (operation & 0xf0) >> 4;

        if (opcode == OpCodes.MOVE) {
            return new MemoryRegisterMovementInstruction(operation, params);
        }

        if (opcode == OpCodes.ADD_IMMEDIATE || opcode == OpCodes.ADD_REGISTERS) {
            return new AddInstruction(operation, params);
        }

        if (opcode == OpCodes.MULTIPLY_IMMEDIATE || opcode == OpCodes.MULTIPLY_REGISTERS) {
            return new MultiplyInstruction(operation, params);
        }

        return new NoOperationInstruction(operation, params);
    }

    public abstract void execute(RegisterArray registerArray, RandomAccessMemory memory);

    public Tuple<Byte, Byte> toBytes() {
        return new Tuple<>(_operation, _params);
    }

    protected byte getOperationByte() {
        return _operation;
    }

    protected byte getParameterByte() {
        return _params;
    }

    protected boolean firstFlagSet() {
        return (_operation & 0b00001000) >> 3 == 1;
    }

    protected boolean secondFlagSet() {
        return (_operation & 0b00000100) >> 2 == 1;
    }

    protected int leftRegisterIndex() {
        return (getParameterByte() & 0xf0) >> 4;
    }

    protected int rightRegisterIndex() {
        return getParameterByte() & 0x0f;
    }
}
