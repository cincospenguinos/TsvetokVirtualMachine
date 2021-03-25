package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.OpCodes;

import java.util.HashMap;
import java.util.Map;

public class OpCodeResolutionService {
    private final Map<String, Byte> _opCodeMap;

    public OpCodeResolutionService() {
        _opCodeMap = new HashMap<>();
        _opCodeMap.put("noup", (byte) OpCodes.NO_OP);
        _opCodeMap.put("nens", (byte) OpCodes.MEMORY);
        _opCodeMap.put("nensou", (byte) OpCodes.MEMORY);
        _opCodeMap.put("stoup", (byte) OpCodes.HALT);
    }

    public byte codeFor(String operation) {
        if (_opCodeMap.containsKey(operation)) {
            return (byte) (_opCodeMap.get(operation) << 4);
        }

        throw new InvalidOperationException("\"" + operation + "\" is not a valid operation");
    }
}
