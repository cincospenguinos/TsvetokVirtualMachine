package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.assembler.exceptions.DuplicateLabelException;
import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TsvetokExecutable {
    public class SymbolTable {
        private final Map<String, Integer> labels;

        public SymbolTable() {
            labels = new HashMap<>();
        }

        public void addLabelAtPosition(String labelName, int position) {
            String label = labelName.replaceAll("\\.", "");

            if (labels.containsKey(label)) {
                throw new DuplicateLabelException(label);
            }

            labels.put(label, position);
        }

        public int positionFor(String label) {
            if (labels.containsKey(label)) {
                return labels.get(label);
            }

            return -1;
        }
    }

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
