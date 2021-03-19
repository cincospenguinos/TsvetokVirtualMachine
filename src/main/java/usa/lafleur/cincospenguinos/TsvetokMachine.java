package usa.lafleur.cincospenguinos;

public class TsvetokMachine {
    public static final byte RETURN_CODE_OK = 0;
    public static final byte RETURN_CODE_BAD_EXECUTABLE = -1;
    public static final int EXECUTABLE_START_INDEX = 6;

    private TsvetokExecutable _executable;
    private byte[] _registerArray;

    public TsvetokMachine(TsvetokExecutable executable) {
        _executable = executable;
        _registerArray = new byte[4];
    }

    public byte execute() {
        if (!_executable.isValid()) {
            return RETURN_CODE_BAD_EXECUTABLE;
        }

        Instruction instruction = _executable.getAt(EXECUTABLE_START_INDEX);
        instruction.execute(_executable, _registerArray);

        return RETURN_CODE_OK;
    }

    public byte valueInRegister(int register) {
        return _registerArray[register];
    }
}
