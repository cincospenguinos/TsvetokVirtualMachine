package usa.lafleur.cincospenguinos.model.instructions;

public class MultiplyInstruction extends ArithmeticInstruction {
    public MultiplyInstruction(byte raw) {
        super(raw);
    }

    @Override
    protected int performOperation(byte left, byte right) {
        return left * right;
    }
}
