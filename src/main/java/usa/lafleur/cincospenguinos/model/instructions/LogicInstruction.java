package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.RegisterArray;

public class LogicInstruction extends Instruction {
    public LogicInstruction(byte raw) {
        super(raw);
    }

    @Override
    public void execute(byte[] memory, RegisterArray registerArray) {
        byte first = registerArray.getRegister(firstRegisterIndex());
        byte second = registerArray.getRegister(secondRegisterIndex());
        byte result;

        if (opFlagSet()) {
            result = (byte) (first | second);
        } else {
            result = (byte) (first & second);
        }

        setFlags(result, registerArray);
        registerArray.accumulator(result);
    }
}
