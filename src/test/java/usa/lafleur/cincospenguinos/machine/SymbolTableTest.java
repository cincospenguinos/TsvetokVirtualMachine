package usa.lafleur.cincospenguinos.machine;

import org.junit.Test;
import usa.lafleur.cincospenguinos.assembler.exceptions.DuplicateLabelException;

public class SymbolTableTest {
    @Test(expected = DuplicateLabelException.class)
    public void test_executableCannotHaveCopiesOfLabels() {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.addLabelAtPosition(".main", 0);
        symbolTable.addLabelAtPosition(".main", 1);
    }
}