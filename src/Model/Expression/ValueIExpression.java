package Model.Expression;

import Exceptions.MyException;
import Model.Value.Value;
import Utils.MyIDictionary;

public class ValueIExpression implements IExpression {
    private Value value;

    public ValueIExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> SymbolTable) throws MyException {
        return value;
    }

    @Override
    public IExpression deepCopy() {
        return new ValueIExpression(value.deepCopy());
    }

    public String toString() {
        return this.value.toString();
    }
}
