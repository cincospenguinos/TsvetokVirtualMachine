package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

public class TsvetokAssembler {
    private final OpCodeResolutionService opCodeResolver;
    private final FlagResolutionService flagResolver;
    private final RegisterResolutionService registerResolver;

    public TsvetokAssembler() {
        opCodeResolver = new OpCodeResolutionService();
        flagResolver = new FlagResolutionService();
        registerResolver = new RegisterResolutionService();
    }

    public TsvetokInstruction createInstruction(String line) {
        String[] pieces = line.trim().split("\\s+");
        byte flags = flagResolver.flagBitsFor(pieces[0]);
        byte operationNibble = opCodeResolver.codeFor(pieces[0]);
        byte opcode = (byte) ((operationNibble << 4) | flags);

        byte leftArg = registerResolver.resolve(pieces[1]);
        byte rightArg = registerResolver.resolve(pieces[2]);
        byte arguments = (byte) ((leftArg << 4) | rightArg);

        return new TsvetokInstruction(opcode, arguments);
    }
}
