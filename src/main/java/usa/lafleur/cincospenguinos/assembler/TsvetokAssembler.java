package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.assembler.exceptions.LabelDoesNotExistException;
import usa.lafleur.cincospenguinos.machine.SymbolTable;
import usa.lafleur.cincospenguinos.machine.TsvetokExecutable;
import usa.lafleur.cincospenguinos.machine.instructions.TsvetokInstruction;

public class TsvetokAssembler {
    private static final String COMMENT_REGEX = "#.*";
    private static final String NEWLINE_REGEX = "(\\n|\\r\\n)";
    public static final String LABEL_PREFIX = "\\.\\w+";
    private final InstructionBuilder instructionBuilder;
    private final SymbolTable symbolTable;

    public TsvetokAssembler() {
        instructionBuilder = new InstructionBuilder();
        symbolTable = new SymbolTable();
    }

    public TsvetokExecutable assemble(String inputSource) {
        symbolTable.clear();
        TsvetokExecutable executable = new TsvetokExecutable();

        String sourceCode = clearCommentsFrom(inputSource);
        extractLabels(sourceCode);

        for (String sourceLine : sourceCode.split(NEWLINE_REGEX)) {
            if (sourceLine.matches(LABEL_PREFIX)) {
                continue;
            }

            TsvetokInstruction instruction = createInstruction(sourceLine);
            executable.addInstruction(instruction);
        }

        executable.setSymbolTable(symbolTable);
        return executable;
    }

    private String clearCommentsFrom(String inputSource) {
        StringBuilder builder = new StringBuilder();

        for (String sourceLine : inputSource.split(NEWLINE_REGEX)) {
            String line = sourceLine.replaceAll(COMMENT_REGEX, "").trim();

            if (line.isEmpty()) {
                continue;
            }

            builder.append(line);
            builder.append("\n");
        }

        return builder.toString();
    }

    private void extractLabels(String sourceCode) {
        int currentPosition = 0;

        for (String sourceLine : sourceCode.split(NEWLINE_REGEX)) {
            String line = sourceLine.replaceAll(COMMENT_REGEX, "").trim();

            if (line.matches(LABEL_PREFIX)) {
                symbolTable.addLabelAtPosition(line.replaceAll("\\.", ""), currentPosition);
            } else {
                currentPosition += 1;
            }
        }
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
