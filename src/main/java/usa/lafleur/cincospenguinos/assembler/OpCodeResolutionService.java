package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.assembler.exceptions.InvalidOperationException;
import usa.lafleur.cincospenguinos.machine.instructions.OpCodes;

import java.util.HashMap;
import java.util.Map;

public class OpCodeResolutionService {
    private final Map<String, Byte> _opCodeMap;

    public OpCodeResolutionService() {
        _opCodeMap = new HashMap<>();
        _opCodeMap.put("noup", (byte) OpCodes.NO_OP);
        _opCodeMap.put("nens", (byte) OpCodes.MOVE);
        _opCodeMap.put("nensou", (byte) OpCodes.MOVE);
        _opCodeMap.put("boujr", (byte) OpCodes.MOVE);
        _opCodeMap.put("boujf", (byte) OpCodes.MOVE);
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
        _opCodeMap.put("pous", (byte) OpCodes.PUSH_STACK);
        _opCodeMap.put("pap", (byte) OpCodes.POP_STACK);
        _opCodeMap.put("sis", (byte) OpCodes.SYSTEM_CALL);
        _opCodeMap.put("stoup", (byte) OpCodes.HALT);
    }

    /**
     * Responds with the opcode for the operation requested. Note that it responds only with the lower
     * four bits--this method DOES NOT do bit shifting to prepare for an operation.
     *
     * @param operation - Operation to get opcode for
     * @return the nibble matching the operation
     * @throws InvalidOperationException when operation does not exist
     */
    public byte codeFor(String operation) {
        if (_opCodeMap.containsKey(operation)) {
            return _opCodeMap.get(operation);
        }

        throw new InvalidOperationException("\"" + operation + "\" nul etree ouperaseeoun");
    }
}
