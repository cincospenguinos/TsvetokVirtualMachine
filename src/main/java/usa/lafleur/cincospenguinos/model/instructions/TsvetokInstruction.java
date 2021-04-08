package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.core.Tuple;

public class TsvetokInstruction {
    private byte _operation;
    private byte _params;

    public TsvetokInstruction(byte operation, byte params) {
        _operation = operation;
        _params = params;
    }

    public Tuple<Byte, Byte> toBytes() {
        return new Tuple<>(_operation, _params);
    }

    public static TsvetokInstruction construct(byte operation, byte params) {
        return new TsvetokInstruction(operation, params);
    }
}
