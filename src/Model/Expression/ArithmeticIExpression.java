package Model.Expression;

import Exceptions.MyDivisionByZeroException;
import Exceptions.MyInvalidOperatorException;
import Exceptions.MyException;
import Model.Type.IntType;
import Model.Value.IntValue;
import Model.Value.Value;
import Utils.MyIDictionary;

public class ArithmeticIExpression implements IExpression {
    IExpression left, right;
    int operator;

    public ArithmeticIExpression(IExpression left, IExpression right, int operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> SymbolTable) throws MyException {
        Value v1 = left.evaluate(SymbolTable);
        if (!v1.getType().equals(new IntType()))
            throw new MyInvalidOperatorException("First operand is not an int.");

        Value v2 = right.evaluate(SymbolTable);
        if (!v2.getType().equals(new IntType()))
            throw new MyInvalidOperatorException("Second operand is not an int.");

        IntValue i1 = (IntValue) v1;
        IntValue i2 = (IntValue) v2;

        int n1 = i1.getValue();
        int n2 = i2.getValue();

        if (operator == 0)
            return new IntValue(n1 + n2);
        if (operator == 1)
            return new IntValue(n1 - n2);
        if (operator == 2)
            return new IntValue(n1 * n2);
        if (operator == 3)
            if (n2 == 0)
                throw new MyDivisionByZeroException("Division by zero.");
            else
                return new IntValue(n1 / n2);

        throw new MyInvalidOperatorException("Invalid operator.");
    }

    @Override
    public IExpression deepCopy() {
        return new ArithmeticIExpression(left.deepCopy(), right.deepCopy(), operator);
    }
}
