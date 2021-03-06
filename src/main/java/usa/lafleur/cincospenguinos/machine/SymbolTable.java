package usa.lafleur.cincospenguinos.machine;

import usa.lafleur.cincospenguinos.assembler.exceptions.DuplicateLabelException;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Integer> labels;

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

    public void clear() {
        labels = new HashMap<>();
    }
}
