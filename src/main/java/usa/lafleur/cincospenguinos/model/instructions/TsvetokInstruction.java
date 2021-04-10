package usa.lafleur.cincospenguinos.model.instructions;

import usa.lafleur.cincospenguinos.core.Tuple;
import usa.lafleur.cincospenguinos.model.RandomAccessMemory;
import usa.lafleur.cincospenguinos.model.RegisterArray;

public abstract class TsvetokInstruction {
    private byte _operation;
    private byte _params;

    public TsvetokInstruction(byte operation, byte params) {
        _operation = operation;
        _params = params;
    }

    public static TsvetokInstruction construct(byte operation, byte params) {
        if ((operation & 0xf0) >> 4 == OpCodes.MOVE) {
            return new MemoryAccessInstruction(operation, params);
        }

        return new AddInstruction(operation, params);
    }

    public abstract void execute(RegisterArray registerArray, RandomAccessMemory memory);

    public Tuple<Byte, Byte> toBytes() {
        return new Tuple<>(_operation, _params);
    }

    protected byte getOperationByte() {
        return _operation;
    }

    protected byte getParameterByte() {
        return _params;
    }

    /**
     * Returns true if the first flag from the left on the operation byte
     * is set.
     */
    protected boolean firstFlagSet() {
        return (_operation & 0b00001000) >> 3 == 1;
    }

    protected boolean secondFlagSet() {
        return (_operation & 0b00000100) >> 2 == 1;
    }
}
