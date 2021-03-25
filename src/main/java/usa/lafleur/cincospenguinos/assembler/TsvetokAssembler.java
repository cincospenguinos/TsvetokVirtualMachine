package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

public class TsvetokAssembler {
    private final FlagResolutionService flagResolver;
    private final RegisterResolutionService registerResolver;
    private final InstructionBuilder instructionBuilder;

    public TsvetokAssembler() {
        flagResolver = new FlagResolutionService();
        registerResolver = new RegisterResolutionService();
        instructionBuilder = new InstructionBuilder();
    }

    public TsvetokInstruction createInstruction(String line) {
        String[] pieces = line.trim().toLowerCase().split("\\s+");
        return instructionBuilder
                .clear()
                .setOpcode(pieces[0])
                .setOperationFlags(flagResolver.flagBitsFor(pieces[0]))
                .setLeftRegister(registerResolver.resolve(pieces[1]))
                .setRightRegister(registerResolver.resolve(pieces[2]))
                .build();
    }
}
