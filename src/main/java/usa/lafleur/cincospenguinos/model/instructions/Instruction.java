package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.TsvetokExecutable;

public abstract class Instruction {
    public static final int NO_OP = 0;
    public static final int MOVE_IMMEDIATE = 4;
    public static final int HALT = 15;

    private final byte _rawByte;

    public Instruction (byte raw) {
        _rawByte = raw;
    }

    public static Instruction instructionFor(byte rawByte) {
        int opcode = (rawByte & 0b11110000) >> 4;

        switch(opcode) {
            case NO_OP:
                return new NoOpInstruction(rawByte);
            case MOVE_IMMEDIATE:
                return new MoveImmediateInstruction(rawByte);
            case HALT:
                return new HaltInstruction(rawByte);
        }

        return new HaltInstruction(rawByte);
    }

    public abstract void execute(TsvetokExecutable executable, byte[] registerArray);

    public boolean shouldHalt() {
        return false;
    }

    protected byte getRawByte() {
        return _rawByte;
    }
}
