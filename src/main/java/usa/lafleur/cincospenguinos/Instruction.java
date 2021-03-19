package usa.lafleur.cincospenguinos;

public abstract class Instruction {
    private byte _rawByte;

    public Instruction (byte raw) {
        _rawByte = raw;
    }

    public abstract void execute(TsvetokExecutable executable, byte[] registerArray);

    public boolean shouldHalt() {
        return false;
    }
}
