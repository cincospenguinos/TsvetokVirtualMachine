package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.assembler.exceptions.DuplicateLabelException;
import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TsvetokExecutable {
    private final List<TsvetokInstruction> instructions;
    private final Map<String, Integer> labels;

    public TsvetokExecutable() {
        instructions = new ArrayList<>();
        labels = new HashMap<>();
    }

    public void addInstruction(TsvetokInstruction instruction) {
        instructions.add(instruction);
    }

    public void addLabelAtCurrentPosition(String labelName) {
        String label = labelName.replaceAll("\\.", "");

        if (labels.containsKey(label)) {
            throw new DuplicateLabelException(labelName);
        }

        labels.put(label, instructions.size());
    }

    public List<TsvetokInstruction> getInstructions() {
        return instructions;
    }

    public Map<String, Integer> getLabels() {
        return labels;
    }
}
