package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

public class TsvetokAssembler {
    public TsvetokAssembler() {}

    public TsvetokInstruction createInstruction(String line) {
        String[] pieces = line.trim().split("\\s+");

        if (pieces.length == 1) {
            if (pieces[0].equals("noup")) {
                return new TsvetokInstruction((byte) 0x0, (byte) 0x0);
            }

            return new TsvetokInstruction((byte) 0xf0, (byte) 0x0);
        }

        return null;
    }
}
