package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RegisterArray;

public abstract class Instruction {
    public static final int ACCUMULATOR_REGISTER_INDEX = 0;
    private final byte _rawByte;

    public Instruction (byte raw) {
        _rawByte = raw;
    }

    public static Instruction instructionFor(byte rawByte) {
        int opcode = (rawByte & 0b11110000) >> 4;

        switch(opcode) {
            case OpCode.NO_OP:
                return new NoOpInstruction(rawByte);
            case OpCode.MOVE:
                return new MoveInstruction(rawByte);
            case OpCode.ACCESS_MEMORY:
                return new MemoryAccessInstruction(rawByte);
            case OpCode.ADD:
                return new AddInstruction(rawByte);
            case OpCode.MULTIPLY:
                return new MultiplyInstruction(rawByte);
        }

        return new HaltInstruction(rawByte);
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
}
