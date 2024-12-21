package Model.State;

import Model.Exception.GenericException;
import Model.Statement.*;

public class ProgramState {
    private IExecutionStack executionStack;
    private ISymbolTable symbolTable;
    private IFileTable fileTable;
    private IOutput output;
    private IHeapTable heapTable;
    private static Integer ID;

    public ProgramState(IExecutionStack newExecutionStack,
                        ISymbolTable newSymbolTable,
                        IOutput newOutput,
                        IFileTable newFileTable,
                        IHeapTable newHeapTable,
                        IStatement newProgram) {
                            
        this.executionStack = newExecutionStack;
        this.symbolTable = newSymbolTable;
        this.output = newOutput;
        this.fileTable = newFileTable;
        this.heapTable = newHeapTable;

        // originalProgram = newProgram;
        this.executionStack.push(newProgram);
    }

    private static synchronized int getNextID() {
        return ID++;
    }

    public ProgramState oneStep() {
        if(executionStack.isEmpty())
            throw new GenericException("ProgramState error: executionStack is empty");

        IStatement statement = executionStack.pop();
        return statement.execute(this);
    }

    public Boolean isComplete() {
        return this.executionStack.isEmpty();
    }

    public IExecutionStack getExecutionStack() {
        return this.executionStack;
    }

    public ISymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public IOutput getOutput() {
        return this.output;
    }

    public IFileTable getFileTable() {
        return this.fileTable;
    }

    public IHeapTable getHeapTable() {
        return this.heapTable;
    }

    @Override
    public String toString() {
        return "\n---------- Program State <" + id + ">----------" +
               "\n\n=== Execution Stack ===" +
               "\n" + executionStack.toString() + 
               "\n\n===== Symbol Table ====" +
               "\n" + symbolTable.toString() +
               "\n\n====== File Table =====" +
               "\n" + fileTable.toString() +
               "\n\n====== Heap Table =====" + 
               "\n" + heapTable.toString() +
               "\n\n======== Output =======" +
               "\n" + output.toString() +
               "\n-----------------------------------\n";
    }
}
