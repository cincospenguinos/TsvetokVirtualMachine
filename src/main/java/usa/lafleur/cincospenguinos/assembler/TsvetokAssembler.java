package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.assembler.exceptions.LabelDoesNotExistException;
import usa.lafleur.cincospenguinos.machine.SymbolTable;
import usa.lafleur.cincospenguinos.machine.TsvetokExecutable;
import usa.lafleur.cincospenguinos.machine.instructions.TsvetokInstruction;

public class TsvetokAssembler {
    private static final String COMMENT_REGEX = "#.*";
    public static final String LABEL_PREFIX = "\\.\\w+";
    private final InstructionBuilder instructionBuilder;
    private final SymbolTable symbolTable;

    public TsvetokAssembler() {
        instructionBuilder = new InstructionBuilder();
        symbolTable = new SymbolTable();
    }

    public TsvetokExecutable assemble(String sourceCode) {
        symbolTable.clear();
        TsvetokExecutable executable = new TsvetokExecutable();
        int position = 0;

        for (String sourceLine : sourceCode.split("(\\n|\\r\\n)")) {
            String line = sourceLine.replaceAll(COMMENT_REGEX, "").trim();

            if (line.isEmpty()) {
                continue;
            }

            if (line.matches(LABEL_PREFIX)) {
                symbolTable.addLabelAtPosition(line, position);
            } else {
                TsvetokInstruction instruction = createInstruction(line);
                executable.addInstruction(instruction);
                position += 1;
            }
        }

        executable.setSymbolTable(symbolTable);
        return executable;
    }

    public TsvetokInstruction createInstruction(String line) {
        String[] pieces = instructionPiecesFrom(line);

        instructionBuilder
                .clear()
                .setOperationByte(pieces[0]);

        if (pieces.length == 2) {
            if (pieces[1].matches("-?\\d+")) {
                instructionBuilder.setImmediate(pieces[1]);
            } else {
                String label = pieces[1].replaceAll("\\.", "");
                int position = symbolTable.positionFor(label);

                if (position == -1) {
                    throw new LabelDoesNotExistException(label);
                }

                instructionBuilder.setLabelIdentifier(position);
            }
        } else if (pieces.length == 3) {
            instructionBuilder
                    .setLeftRegister(pieces[1])
                    .setRightRegister(pieces[2]);
        }

        return instructionBuilder.build();
    }

    private String[] instructionPiecesFrom(String line) {
        return line
                .replaceAll(COMMENT_REGEX, "")
                .trim()
                .toLowerCase()
                .split("\\s+");
    }
}
