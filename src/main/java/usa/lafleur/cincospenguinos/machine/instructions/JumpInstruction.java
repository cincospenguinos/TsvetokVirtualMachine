package usa.lafleur.cincospenguinos.machine.instructions;

import usa.lafleur.cincospenguinos.machine.RandomAccessMemory;
import usa.lafleur.cincospenguinos.machine.RegisterArray;

public class JumpInstruction extends TsvetokInstruction {
    public JumpInstruction(byte operation, byte params) {
        super(operation, params);
    }

    @Override
    public void execute(RegisterArray registerArray, RandomAccessMemory memory) {
        int opcode = getOpcode();

        if (opcode == OpCodes.JUMP_ON_ZERO && !registerArray.isZeroFlagSet()) {
            return;
        }

        registerArray.setProgramCounter(getParameterByte());
    }
}
