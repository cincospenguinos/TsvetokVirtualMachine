package usa.lafleur.cincospenguinos;

public class HaltInstruction extends Instruction {
    public HaltInstruction(byte raw) {
        super(raw);
    }

    @Override
    public void execute(TsvetokExecutable executable, byte[] registerArray) {}

    public boolean shouldHalt() {
        return true;
    }
}
