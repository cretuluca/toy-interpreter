package Model.Statement;

import Exceptions.MyException;
import Model.ProgState;

public class NOPStatement implements IStatement {

    @Override
    public ProgState execute(ProgState state) throws MyException {
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new NOPStatement();
    }

    @Override
    public String toString() {
        return "NOP";
    }
}
