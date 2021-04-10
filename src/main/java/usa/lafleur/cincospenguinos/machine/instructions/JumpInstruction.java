package usa.lafleur.cincospenguinos.machine.instructions;

import usa.lafleur.cincospenguinos.machine.RandomAccessMemory;
import usa.lafleur.cincospenguinos.machine.RegisterArray;

public class JumpInstruction extends TsvetokInstruction {
    private boolean _handledJump;

    public JumpInstruction(byte operation, byte params) {
        super(operation, params);
        _handledJump = false;
    }

    @Override
    public void execute(RegisterArray registerArray, RandomAccessMemory memory) {
        int opcode = getOpcode();

        if (opcode == OpCodes.JUMP_ON_ZERO && !registerArray.isZeroFlagSet()) {
            return;
        }

        if (opcode == OpCodes.JUMP_ON_NON_ZERO && registerArray.isZeroFlagSet()) {
            return;
        }

        registerArray.setProgramCounter(getParameterByte());
        _handledJump = true;
    }

    @Override
    public boolean incrementProgramCounter() {
        return !_handledJump;
    }
}
