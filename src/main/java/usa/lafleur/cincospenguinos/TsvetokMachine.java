package usa.lafleur.cincospenguinos;

public class TsvetokMachine {
    public static final byte RETURN_CODE_OK = 0;
    public static final byte RETURN_CODE_BAD_EXECUTABLE = -1;

    private TsvetokExecutable _executable;

    public TsvetokMachine(TsvetokExecutable executable) {
        _executable = executable;
    }

    public byte execute() {
        if (!_executable.isValid()) {
            return RETURN_CODE_BAD_EXECUTABLE;
        }

        return 0;
    }
}
