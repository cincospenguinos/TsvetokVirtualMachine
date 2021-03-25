package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.OpCodes;

import java.util.HashMap;
import java.util.Map;

public class OpCodeResolutionService {
    private final Map<String, Byte> _opCodeMap;

    public OpCodeResolutionService() {
        _opCodeMap = new HashMap<>();
        _opCodeMap.put("noup", (byte) OpCodes.NO_OP);
        _opCodeMap.put("nens", (byte) OpCodes.MOVE);
        _opCodeMap.put("nensou", (byte) OpCodes.MOVE);
        _opCodeMap.put("adr", (byte) OpCodes.ADD_REGISTERS);
        _opCodeMap.put("adf", (byte) OpCodes.ADD_IMMEDIATE);
        _opCodeMap.put("nultr", (byte) OpCodes.MULTIPLY_REGISTERS);
        _opCodeMap.put("nultf", (byte) OpCodes.MULTIPLY_IMMEDIATE);
        _opCodeMap.put("difr", (byte) OpCodes.DIVIDE_REGISTERS);
        _opCodeMap.put("diff", (byte) OpCodes.DIVIDE_IMMEDIATE);
        _opCodeMap.put("jnp", (byte) OpCodes.JUMP_UNCONDITIONAL);
        _opCodeMap.put("jnps", (byte) OpCodes.JUMP_ON_ZERO);
        _opCodeMap.put("jnpns", (byte) OpCodes.JUMP_ON_NON_ZERO);
        _opCodeMap.put("retr", (byte) OpCodes.RETURN);
        _opCodeMap.put("stoup", (byte) OpCodes.HALT);
    }

    public byte codeFor(String operation) {
        if (_opCodeMap.containsKey(operation)) {
            return (byte) (_opCodeMap.get(operation) << 4);
        }

        throw new InvalidOperationException("\"" + operation + "\" is not a valid operation");
    }
}
