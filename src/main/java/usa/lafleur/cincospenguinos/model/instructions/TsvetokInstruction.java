package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.core.Tuple;

public abstract class TsvetokInstruction {
    private byte _operation;
    private byte _params;

    public TsvetokInstruction(byte operation, byte params) {
        _operation = operation;
        _params = params;
    }

    public static TsvetokInstruction construct(byte operation, byte params) {
        return new AddInstruction(operation, params);
    }

    public abstract void execute(byte[] registerArray);

    public Tuple<Byte, Byte> toBytes() {
        return new Tuple<>(_operation, _params);
    }

    protected byte getOperationByte() {
        return _operation;
    }

    protected byte getParameterByte() {
        return _params;
    }
}
