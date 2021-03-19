package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.TsvetokExecutable;

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
            case OpCode.MULTIPLY:
                return new MoveImmediateInstruction(rawByte);
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
