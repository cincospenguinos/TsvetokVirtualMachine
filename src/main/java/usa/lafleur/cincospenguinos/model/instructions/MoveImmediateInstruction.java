package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.model.TsvetokExecutable;

public class MoveImmediateInstruction extends Instruction {
    public MoveImmediateInstruction(byte raw) {
        super(raw);
    }

    public void execute(TsvetokExecutable executable, byte[] registerArray) {
        registerArray[0] = immediateValue();
    }

    private byte immediateValue() {
        return (byte) (getRawByte() & 0x0f);
    }
}