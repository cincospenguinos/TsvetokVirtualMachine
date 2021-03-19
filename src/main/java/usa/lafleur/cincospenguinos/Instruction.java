package usa.lafleur.cincospenguinos;

public abstract class Instruction {
    private byte _rawByte;

    public Instruction (byte raw) {
        _rawByte = raw;
    }

    public static Instruction instructionFor(byte rawByte) {
        int opcode = (rawByte & 0b11110000) >> 4;

        switch(opcode) {
            case 0:
                return new NoOpInstruction(rawByte);
            case 4:
                return new MoveImmediateInstruction(rawByte);
            case 15:
                return new HaltInstruction(rawByte);
        }

        return new HaltInstruction(rawByte);
    }

    public abstract void execute(TsvetokExecutable executable, byte[] registerArray);

    public boolean shouldHalt() {
        return false;
    }
}
