package usa.lafleur.cincospenguinos;

public class MoveImmediateInstruction extends Instruction {
    public MoveImmediateInstruction(byte raw) {
        super(raw);
    }

    public void execute(TsvetokExecutable executable, byte[] registerArray) {
        registerArray[0] = 15;
    }
}
