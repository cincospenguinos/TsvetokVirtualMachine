package usa.lafleur.cincospenguinos.model.instructions;

public class AddInstruction extends ArithmeticInstruction {
    public AddInstruction(byte raw) {
        super(raw);
    }

    @Override
    protected int performOperation(byte left, byte right) {
        return left + right;
    }
}
