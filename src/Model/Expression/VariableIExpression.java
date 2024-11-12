package Model.Expression;

import Exceptions.MyException;
import Model.Value.Value;
import Utils.MyIDictionary;

public class VariableIExpression implements IExpression {
    private String variable;

    public VariableIExpression(String variable) {
        this.variable = variable;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> SymbolTable) throws MyException {
        return SymbolTable.lookUp(this.variable);
    }

    @Override
    public IExpression deepCopy() {
        return new VariableIExpression(this.variable);
    }

    public String toString() {
        return this.variable;
    }
}
