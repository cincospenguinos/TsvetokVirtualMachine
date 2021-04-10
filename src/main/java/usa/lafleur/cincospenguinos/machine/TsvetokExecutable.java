package usa.lafleur.cincospenguinos.machine;

import usa.lafleur.cincospenguinos.machine.instructions.TsvetokInstruction;

import java.util.ArrayList;
import java.util.List;

public class TsvetokExecutable {
    private final List<TsvetokInstruction> instructions;
    private final SymbolTable symbolTable;

    public TsvetokExecutable() {
        instructions = new ArrayList<>();
        symbolTable = new SymbolTable();
    }

    public void addInstruction(TsvetokInstruction instruction) {
        instructions.add(instruction);
    }

    public void addLabelAtCurrentPosition(String labelName) {
        symbolTable.addLabelAtPosition(labelName, instructions.size());
    }

    public List<TsvetokInstruction> getInstructions() {
        return instructions;
    }

    public SymbolTable getLabelSymbolTable() {
        return symbolTable;
    }
}
