package Model.Expression;

import Exceptions.MyException;
import Exceptions.MyInvalidOperatorException;
import Model.Type.BooleanType;
import Model.Value.BooleanValue;
import Model.Value.Value;
import Utils.MyIDictionary;

public class LogicIExpression implements IExpression {
    private IExpression left, right;
    int operator;

    LogicIExpression(IExpression left, IExpression right, int operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> SymbolTable) throws MyException {
        Value v1 = left.evaluate(SymbolTable);
        if(!v1.getType().equals(new BooleanType()))
            throw new MyInvalidOperatorException("First operand is not Boolean.");
        Value v2 = right.evaluate(SymbolTable);
        if(!v2.getType().equals(new BooleanType()))
            throw new MyInvalidOperatorException("Second operand is not Boolean.");

        BooleanValue b1 = (BooleanValue) v1;
        BooleanValue b2 = (BooleanValue) v2;

        Boolean n1 = b1.getValue();
        Boolean n2 = b2.getValue();

        if(operator == 0)
            return new BooleanValue(n1 && n2);
        if(operator == 1)
            return new BooleanValue(n2 || n1);
        else
            throw new MyInvalidOperatorException("Invalid operator.");
    }

    @Override
    public IExpression deepCopy() {
        return new LogicIExpression(left.deepCopy(), right.deepCopy(), operator);
    }

    @Override
    public String toString() {
        return left.toString() + " " + operator + " " + right.toString();
    }
}
