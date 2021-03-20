package usa.lafleur.cincospenguinos.model.instructions;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import usa.lafleur.cincospenguinos.model.RegisterArray;

public class SysCallInstruction extends Instruction {
    public SysCallInstruction(byte raw) {
        super(raw);
    }

    @Override
    public void execute(byte[] memory, RegisterArray registerArray) {
        throw new NotImplementedException();
    }
}
