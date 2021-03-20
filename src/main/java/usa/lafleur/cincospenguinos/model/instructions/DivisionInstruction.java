package usa.lafleur.cincospenguinos.model.instructions;

public class DivisionInstruction extends ArithmeticInstruction {
    public DivisionInstruction(byte raw) {
        super(raw);
    }

    @Override
    protected int performOperation(byte left, byte right) {
        return left / right;
    }
}
