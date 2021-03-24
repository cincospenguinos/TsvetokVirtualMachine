package usa.lafleur.cincospenguinos.assembler;

public class RegisterResolutionService {
    private static final String ACCUMULATOR_REGISTER_NAME = "ak";
    private static final String TEMPORARY_REGISTER_NAMESPACE = "tnp";
    private static final String ARGUMENT_REGISTER_NAMESPACE = "arj";
    private static final String SUBROUTINE_REGISTER_NAMESPACE = "retr";
    private static final String STACK_POINTER_REGISTER_NAME = "fn";
    private static final String PROGRAM_COUNTER_REGISTER_NAME = "pn";
    private static final String FLAG_REGISTER_NAME = "flj";

    public RegisterResolutionService() {}

    public byte resolve(String registerString) {
        String register = registerString.replace("$", "");

        if (register.equals(ACCUMULATOR_REGISTER_NAME)) {
            return 0x0;
        }

        if (register.contains(TEMPORARY_REGISTER_NAMESPACE)) {
            int offsetAmount = 0x6;
            byte registerByte = Byte.parseByte(register.replace(TEMPORARY_REGISTER_NAMESPACE, ""));
            return (byte) (registerByte + offsetAmount);
        }

        if (register.contains(ARGUMENT_REGISTER_NAMESPACE)) {
            int offsetAmount = 0x8;
            byte registerByte = Byte.parseByte(register.replace(ARGUMENT_REGISTER_NAMESPACE, ""));
            return (byte) (registerByte + offsetAmount);
        }

        if (register.contains(SUBROUTINE_REGISTER_NAMESPACE)) {
            int offsetAmount = 0xb;
            String letter = register.replace(SUBROUTINE_REGISTER_NAMESPACE, "");
            return letter.equals("f") ? (byte) offsetAmount : (byte) (offsetAmount + 1);
        }

        if (register.equals(FLAG_REGISTER_NAME)) {
            return 0xd;
        }

        if (register.equals(STACK_POINTER_REGISTER_NAME)) {
            return 0xe;
        }

        if (register.equals(PROGRAM_COUNTER_REGISTER_NAME)) {
            return 0xf;
        }

        return Byte.parseByte(register.replace("rej", ""));
    }
}
