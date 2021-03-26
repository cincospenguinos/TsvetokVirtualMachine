package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

import java.util.ArrayList;
import java.util.List;

public class TsvetokExecutable {
    private final List<TsvetokInstruction> instructions;

    public TsvetokExecutable() {
        instructions = new ArrayList<>();
    }

    public void addInstruction(TsvetokInstruction instruction) {
        instructions.add(instruction);
    }

    public List<TsvetokInstruction> getInstructions() {
        return instructions;
    }
}
