package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

public class TsvetokAssembler {
    private OpCodeResolutionService opCodeResolver;

    public TsvetokAssembler() {
        opCodeResolver = new OpCodeResolutionService();
    }

    public TsvetokInstruction createInstruction(String line) {
        String[] pieces = line.trim().split("\\s+");

        if (pieces.length == 1) {
            if (pieces[0].equals("noup")) {
                byte opcode = opCodeResolver.codeFor(pieces[0]);
                return new TsvetokInstruction(opcode, (byte) 0x0);
            }

            return new TsvetokInstruction((byte) 0xf0, (byte) 0x0);
        }

        return null;
    }
}
