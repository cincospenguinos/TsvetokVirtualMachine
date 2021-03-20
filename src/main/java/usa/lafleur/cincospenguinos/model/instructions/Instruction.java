package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RegisterArray;

public abstract class Instruction {
    private final byte _rawByte;

    public Instruction (byte raw) {
        _rawByte = raw;
    }

    public static Instruction instructionFor(byte rawByte) {
        int opcode = (rawByte & 0b11110000) >> 4;

        switch(opcode) {
            case OpCode.NO_OP:
                return new NoOpInstruction(rawByte);
            case OpCode.MOVE_IMMEDIATE:
            case OpCode.MOVE_REGISTER:
                return new MoveInstruction(rawByte);
            case OpCode.LOAD_MEMORY:
            case OpCode.STORE_MEMORY:
                return new MemoryAccessInstruction(rawByte);
            case OpCode.ADD_REGISTER:
            case OpCode.ADD_IMMEDIATE:
                return new AddInstruction(rawByte);
            case OpCode.MULTIPLY_REGISTER:
            case OpCode.MULTIPLY_IMMEDIATE:
                return new MultiplyInstruction(rawByte);
            case OpCode.DIVIDE_REGISTER:
            case OpCode.DIVIDE_IMMEDIATE:
                return new DivisionInstruction(rawByte);
            case OpCode.LOGICAL_AND:
            case OpCode.LOGICAL_OR:
                return new LogicInstruction(rawByte);
            case OpCode.SYSTEM_CALL:
                return new SysCallInstruction(rawByte);
            case OpCode.JUMP_ON_ZERO:
                return new JumpOnZeroInstruction(rawByte);
            case OpCode.HALT:
            default:
                return new HaltInstruction(rawByte);
        }
    }

    public abstract void execute(byte[] memory, RegisterArray registerArray);

    public boolean shouldHalt() {
        return false;
    }

    protected byte getRawByte() {
        return _rawByte;
    }

    protected int firstRegisterIndex() {
        return (getRawByte() & 0b00001100) >> 2;
    }

    protected int secondRegisterIndex() {
        return (getRawByte() & 0b00000011);
    }

    protected byte getImmediate() {
        return (byte) (getRawByte() & 0x0f);
    }

    protected boolean opFlagSet() {
        byte rawByte = getRawByte();
        rawByte &= 0b00010000;
        rawByte = (byte) (rawByte >> 4);

        return rawByte == (byte) 0x01;
    }

    protected void setFlags(int operationResult, RegisterArray registerArray) {
        if (operationResult >= 127) {
            registerArray.setFlag(RegisterArray.OVERFLOW_FLAG);
        } else {
            registerArray.clearFlag(RegisterArray.OVERFLOW_FLAG);
        }

        if ((operationResult & 0xff) == 0) {
            registerArray.setFlag(RegisterArray.ZERO_FLAG);
        } else {
            registerArray.clearFlag(RegisterArray.ZERO_FLAG);
        }
    }
}
