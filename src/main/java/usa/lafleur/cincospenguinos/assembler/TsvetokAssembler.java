package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

public class TsvetokAssembler {
    private final InstructionBuilder instructionBuilder;

    public TsvetokAssembler() {
        instructionBuilder = new InstructionBuilder();
    }

    public TsvetokInstruction createInstruction(String line) {
        String[] pieces = line.trim().toLowerCase().split("\\s+");

        return instructionBuilder
                .clear()
                .setOperationByte(pieces[0])
                .setLeftRegister(pieces[1])
                .setRightRegister(pieces[2])
                .build();
    }
}
