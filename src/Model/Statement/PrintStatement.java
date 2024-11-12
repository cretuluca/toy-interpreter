package Model.Statement;

import Exceptions.MyException;
import Model.Expression.IExpression;
import Model.ProgState;

public class PrintStatement implements IStatement {
    private IExpression IExpression;

    public PrintStatement(IExpression IExpression) {
        this.IExpression = IExpression;
    }

    @Override
    public ProgState execute(ProgState state) throws MyException {
        state.getOutput().add(IExpression.evaluate(state.getSymbolTable()));
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(IExpression.deepCopy());
    }

    public String toString() {
        return "print(" + IExpression.toString() + ")";
    }
}
