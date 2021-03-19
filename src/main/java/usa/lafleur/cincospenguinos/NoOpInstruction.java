package usa.lafleur.cincospenguinos;

public class NoOpInstruction extends Instruction {
    public NoOpInstruction(byte raw) {
        super(raw);
    }

    @Override
    public void execute(TsvetokExecutable executable, byte[] registerArray) {}
}
