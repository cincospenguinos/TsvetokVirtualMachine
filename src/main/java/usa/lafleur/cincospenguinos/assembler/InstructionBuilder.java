package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

public class InstructionBuilder {
    private byte _opcode;
    private byte _operationFlags;
    private byte _leftRegister;
    private byte _rightRegister;

    private final OpCodeResolutionService opCodeResolver;
    private final FlagResolutionService flagResolver;
    private final RegisterResolutionService registerResolver;

    InstructionBuilder() {
        opCodeResolver = new OpCodeResolutionService();
        flagResolver = new FlagResolutionService();
        registerResolver = new RegisterResolutionService();
    }

    public InstructionBuilder setOperationByte(String operation) {
        _opcode = opCodeResolver.codeFor(operation);
        _operationFlags = flagResolver.flagBitsFor(operation);
        return this;
    }

    public InstructionBuilder setLeftRegister(String leftRegister) {
        _leftRegister = registerResolver.resolve(leftRegister);
        return this;
    }

    public InstructionBuilder setRightRegister(String rightRegister) {
        _rightRegister = registerResolver.resolve(rightRegister);
        return this;
    }

    public InstructionBuilder clear() {
        _opcode = 0;
        _operationFlags = 0;
        _leftRegister = 0;
        _rightRegister = 0;

        return this;
    }

    public TsvetokInstruction build() {
        byte upper = (byte) ((_opcode << 4) | (_operationFlags));
        byte lower = (byte) ((_leftRegister << 4) | (_rightRegister));

        return new TsvetokInstruction(upper, lower);
    }
}
