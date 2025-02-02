package Model.Statement;

import Model.Exception.*;
import Model.Expression.*;
import Model.State.*;
import Model.Type.BooleanType;
import Model.Type.IType;
import Model.Value.*;
import Utils.Dictionary.IMyDictionary;

public class IfStatement implements IStatement {
    private final IExpression condition;
    private final IStatement thenBranch;
    private final IStatement elseBranch;

    public IfStatement(IExpression newCondition, IStatement newThenBranch, IStatement newElseBranch) {
        this.condition = newCondition;
        this.thenBranch = newThenBranch;
        this.elseBranch = newElseBranch;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        IExecutionStack stack = state.getExecutionStack();
        ISymbolTable symbolTable = state.getSymbolTable();
        IHeapTable heapTable = state.getHeapTable();

        IValue conditionValue = condition.evaluate(symbolTable, heapTable);
        if(!(conditionValue instanceof BooleanValue)) {
            throw new IfStatementException("IfStatement execute: conditional expression is not a boolean: " + conditionValue.toString());
        }

        BooleanValue boolCondition = (BooleanValue) conditionValue;

        if(boolCondition.getValue()) {
            stack.push(thenBranch);
        } else {
            stack.push(elseBranch);
        }

        return null;
    }

    @Override
    public String toString() {
        return "(if("+ condition.toString()+") then(" +thenBranch.toString() +") else("+elseBranch.toString()+"))";
    }

    @Override
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnv) throws GenericException {
        IType expressionType = condition.typeCheck(typeEnv);
        if (expressionType.equals(new BooleanType())) {
            thenBranch.typeCheck(typeEnv);
            elseBranch.typeCheck(typeEnv);
            return typeEnv;
        } else throw new IfStatementException("The condition of IF has not the type bool");
    }
}
