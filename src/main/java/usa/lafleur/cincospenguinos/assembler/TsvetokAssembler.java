package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

public class TsvetokAssembler {
    private OpCodeResolutionService opCodeResolver;
    private FlagResolutionService flagResolver;

    public TsvetokAssembler() {
        opCodeResolver = new OpCodeResolutionService();
        flagResolver = new FlagResolutionService();
    }

    public TsvetokInstruction createInstruction(String line) {
        String[] pieces = line.trim().split("\\s+");
        byte flags = flagResolver.flagBitsFor(pieces[0]);
        byte operationNibble = opCodeResolver.codeFor(pieces[0]);
        byte opcode = (byte) ((operationNibble << 4) | flags);

        return new TsvetokInstruction(opcode, (byte) 0x0);
    }
}
