package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

public class TsvetokAssembler {
    public TsvetokAssembler() {}

    public TsvetokInstruction createInstruction(String line) {
        return new TsvetokInstruction();
    }
}
