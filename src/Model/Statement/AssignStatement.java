package Model.Statement;

import Exceptions.MyException;
import Exceptions.MyInvalidTypeException;
import Model.ProgState;
import Model.Type.Type;
import Model.Value.Value;
import Utils.MyIDictionary;

import Model.Expression.IExpression;

public class AssignStatement implements IStatement {
    private String id;
    IExpression IExpression;

    public AssignStatement(String id, IExpression IExpression) {
        this.id = id;
        this.IExpression = IExpression;
    }

    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyIDictionary<String,Value> SymbolTable = state.getSymbolTable();
        if(SymbolTable.isDefined(id)) {
            Value val = this.IExpression.evaluate(SymbolTable);
            Type typeId = (SymbolTable.lookUp(id)).getType();
            if ((val.getType()).equals(typeId)) {
                SymbolTable.update(id, val);
            } else
                throw new MyInvalidTypeException("Declared type of variable " + id + " and type of the assigned expression do not match.");
        } else throw new MyInvalidTypeException("The used variable " + id + " was not declared before.");

        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new AssignStatement(id, IExpression.deepCopy());
    }

    public String toString() {
        return this.id + " = " + this.IExpression.toString();
    }
}
