package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

public class TsvetokAssembler {
    private final InstructionBuilder instructionBuilder;

    public TsvetokAssembler() {
        instructionBuilder = new InstructionBuilder();
    }

    public TsvetokInstruction createInstruction(String line) {
        String[] pieces = line.trim().toLowerCase().split("\\s+");

        instructionBuilder
                .clear()
                .setOperationByte(pieces[0]);

        if (pieces.length == 2) {
            instructionBuilder
                    .setImmediate(pieces[1]);
        } else if (pieces.length == 3) {
            instructionBuilder
                    .setLeftRegister(pieces[1])
                    .setRightRegister(pieces[2]);
        }

        return instructionBuilder.build();
    }
}
