package usa.lafleur.cincospenguinos.model.instructions;

public class AddInstruction extends TsvetokInstruction {
    public AddInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    public void execute(byte[] registerArray) {
        registerArray[0] += 12;
    }
}
