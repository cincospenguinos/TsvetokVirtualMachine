package usa.lafleur.cincospenguinos;

public class Instruction {
    private byte _rawByte;

    public Instruction (byte raw) {
        _rawByte = raw;
    }

    public void execute(TsvetokExecutable executable, byte[] registerArray) {
        registerArray[0] = 15;
    }
}
