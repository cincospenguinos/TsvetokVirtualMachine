package usa.lafleur.cincospenguinos.machine.instructions;

import usa.lafleur.cincospenguinos.core.Tuple;
import usa.lafleur.cincospenguinos.machine.RandomAccessMemory;
import usa.lafleur.cincospenguinos.machine.RegisterArray;

public abstract class TsvetokInstruction {
    private final byte _operation;
    private final byte _params;

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

        if (opcode == OpCodes.DIVIDE_IMMEDIATE || opcode == OpCodes.DIVIDE_REGISTERS) {
            return new DivisionInstruction(operation, params);
        }

        if (opcode == OpCodes.JUMP_UNCONDITIONAL || opcode == OpCodes.JUMP_ON_ZERO || opcode == OpCodes.JUMP_ON_NON_ZERO) {
            return new JumpInstruction(operation, params);
        }

        if (opcode == OpCodes.HALT) {
            return new HaltInstruction(operation, params);
        }

        return new NoOperationInstruction(operation, params);
    }

    public abstract void execute(RegisterArray registerArray, RandomAccessMemory memory);

    public boolean incrementProgramCounter() {
        return !(this instanceof JumpInstruction);
    }

    public boolean endExecution() {
        return this instanceof HaltInstruction;
    }

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

    protected int getOpcode() {
        // Turns out java keeps the sign when shifting right, meaning we have to
        // drop the sign bit to get the operation we want
        return ((getOperationByte() >> 1) & 0x7f) >> 3;
    }
}
