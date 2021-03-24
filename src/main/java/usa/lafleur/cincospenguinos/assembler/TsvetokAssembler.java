package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

public class TsvetokAssembler {
    private OpCodeResolutionService opCodeResolver;

    public TsvetokAssembler() {
        opCodeResolver = new OpCodeResolutionService();
    }

    public TsvetokInstruction createInstruction(String line) {
        String[] pieces = line.trim().split("\\s+");
        byte opcode = opCodeResolver.codeFor(pieces[0]);

        if (pieces.length == 1) {
            return new TsvetokInstruction(opcode, (byte) 0x0);
        }

        return null;
    }
}
