package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.TsvetokExecutable;
import usa.lafleur.cincospenguinos.model.TsvetokMachine;
import usa.lafleur.cincospenguinos.model.instructions.OpCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Assembler for Tsvetok Virtual Machine. Converts human-readable assembly into
 * bytes.
 */
public class TsvetokAssembler {
    public static final String COMMENT_CHARS = "#";

    private static final String WHITESPACE_PATTERN = "\\s+";
    private static final InstructionMap INSTRUCTION_MAP = new InstructionMap();

    private String _rawInput;
    private byte[] _bytes;

    public TsvetokAssembler(String input) {
        _rawInput = input;
    }

    public TsvetokAssembler(File assemblyFile) {
        try {
            Scanner s = new Scanner(assemblyFile);
            StringBuilder builder = new StringBuilder();

            while (s.hasNextLine()) {
                builder.append(s.nextLine());
                builder.append("\n");
            }

            s.close();

            _rawInput = builder.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No file \"" + assemblyFile.getAbsolutePath() + "\"");
        }
    }

    public TsvetokAssembler assemble() {
        _bytes = new byte[TsvetokMachine.MAXIMUM_MEMORY];
        System.arraycopy(TsvetokExecutable.VALID_HEADER, 0, _bytes, 0, 6);
        int index = TsvetokMachine.EXECUTABLE_START_INDEX;
        Scanner scanner = new Scanner(_rawInput);
        InstructionAssembly instructionAssembly = new InstructionAssembly();

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.startsWith(COMMENT_CHARS)) {
                continue;
            }

            byte instruction = instructionFor(line, instructionAssembly);
            _bytes[index] = instruction;
            instructionAssembly.clear();
            index += 1;
        }

        scanner.close();

        return this;
    }

    private byte instructionFor(String line, InstructionAssembly instructionAssembly) {
        String[] pieces = line.split(WHITESPACE_PATTERN);
        byte opcode = INSTRUCTION_MAP.opcodeFor(pieces[0]);
        instructionAssembly.setOpCode(opcode);

        switch(opcode) {
            case OpCode.SYSTEM_CALL:
                instructionAssembly.setImmediate(pieces[1]);
                break;
            case OpCode.JUMP_ON_ZERO:
                setRegisters(pieces, instructionAssembly);
                break;
            case OpCode.LOAD_MEMORY:
            case OpCode.STORE_MEMORY:
            case OpCode.LOGICAL_AND:
            case OpCode.LOGICAL_OR:
                boolean isMemory = opcode == OpCode.STORE_MEMORY;
                setRegisters(pieces, instructionAssembly);
                instructionAssembly.setOpFlag(pieces[0].contains(isMemory ? "ou" : "our"));
                break;
            case OpCode.MOVE_IMMEDIATE:
            case OpCode.MOVE_REGISTER:
            case OpCode.ADD_IMMEDIATE:
            case OpCode.ADD_REGISTER:
            case OpCode.MULTIPLY_REGISTER:
            case OpCode.MULTIPLY_IMMEDIATE:
            case OpCode.DIVIDE_REGISTER:
            case OpCode.DIVIDE_IMMEDIATE:
                boolean hasImmediate = pieces.length == 2;
                instructionAssembly.setOpFlag(hasImmediate);

                if (hasImmediate) {
                    instructionAssembly.setImmediate(pieces[1]);
                } else {
                    setRegisters(pieces, instructionAssembly);
                }
                break;
        }

        return instructionAssembly.build();
    }

    private void setRegisters(String[] pieces, InstructionAssembly instructionAssembly) {
        instructionAssembly.setFirstRegister(pieces[1]);
        instructionAssembly.setSecondRegister(pieces[2]);
    }

    public byte[] toByteArray() {
        return _bytes;
    }

    public TsvetokExecutable toExecutable() {
        return new TsvetokExecutable(_bytes);
    }
}
