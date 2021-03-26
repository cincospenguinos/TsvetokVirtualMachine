package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

public class TsvetokAssembler {
    private static final String COMMENT_REGEX = "#.*";
    private final InstructionBuilder instructionBuilder;

    public TsvetokAssembler() {
        instructionBuilder = new InstructionBuilder();
    }

    public TsvetokExecutable assemble(String sourceCode) {
        TsvetokExecutable executable = new TsvetokExecutable();

        for (String line : sourceCode.split("(\\n|\\r\\n)")) {
            String operation = line.replaceAll(COMMENT_REGEX, "");

            if (!operation.isEmpty()) {
                TsvetokInstruction instruction = createInstruction(operation);
                executable.addInstruction(instruction);
            }
        }

        return executable;
    }

    public TsvetokInstruction createInstruction(String line) {
        String[] pieces = instructionPiecesFrom(line);

        instructionBuilder
                .clear()
                .setOperationByte(pieces[0]);

        if (pieces.length == 2) {
            instructionBuilder.setImmediate(pieces[1]);
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
