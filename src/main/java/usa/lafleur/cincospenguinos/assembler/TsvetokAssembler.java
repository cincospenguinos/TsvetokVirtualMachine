package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.TsvetokInstruction;

public class TsvetokAssembler {
    private final OpCodeResolutionService opCodeResolver;
    private final FlagResolutionService flagResolver;
    private final RegisterResolutionService registerResolver;
    private final InstructionBuilder instructionBuilder;

    public TsvetokAssembler() {
        opCodeResolver = new OpCodeResolutionService();
        flagResolver = new FlagResolutionService();
        registerResolver = new RegisterResolutionService();
        instructionBuilder = new InstructionBuilder();
    }

    class InstructionBuilder {
        private byte _opcode;
        private byte _operationFlags;
        private byte _leftRegister;
        private byte _rightRegister;

        InstructionBuilder() {}

        public InstructionBuilder setOpcode(byte opcode) {
            _opcode = opcode;
            return this;
        }

        public InstructionBuilder setOperationFlags(byte flags) {
            _operationFlags = flags;
            return this;
        }

        public InstructionBuilder setLeftRegister(byte lefty) {
            _leftRegister = lefty;
            return this;
        }

        public InstructionBuilder setRightRegister(byte righty) {
            _rightRegister = righty;
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

    public TsvetokInstruction createInstruction(String line) {
        String[] pieces = line.trim().split("\\s+");
        return instructionBuilder
                .clear()
                .setOpcode(opCodeResolver.codeFor(pieces[0]))
                .setOperationFlags(flagResolver.flagBitsFor(pieces[0]))
                .setLeftRegister(registerResolver.resolve(pieces[1]))
                .setRightRegister(registerResolver.resolve(pieces[2]))
                .build();
    }
}
