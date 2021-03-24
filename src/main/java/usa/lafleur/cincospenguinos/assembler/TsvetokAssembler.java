package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.core.Tuple;

public class TsvetokAssembler {
    public class TsvetokInstruction {

        public Tuple<Byte, Byte> toBytes() {
            return new Tuple<>((byte) 0, (byte) 0);
        }
    }

    public TsvetokAssembler() {}

    public TsvetokInstruction createInstruction(String noup) {
        return new TsvetokInstruction();
    }
}
