package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RegisterArray;

public class MultiplyInstruction extends LogicArithmeticInstruction {
    public MultiplyInstruction(byte raw) {
        super(raw);
    }

    @Override
    protected int performOperation(byte left, byte right) {
        return left * right;
    }
}
