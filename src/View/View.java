package View;

import Model.Expression.VariableIExpression;
import Model.Statement.VarDeclStatement;
import Model.Type.IntType;
import Utils.*;
import Controller.Service;
import Model.ProgState;
import Model.Statement.CompStatement;
import Model.Statement.IStatement;
import Model.Statement.PrintStatement;
import Model.Value.Value;
import Repository.IRepository;
import Repository.Repository;

public class View {
    public static void main(String[] args) {

        // int v;
        // print(v);
        IStatement ex1= new CompStatement(new VarDeclStatement("v", new IntType()),
                new PrintStatement(new VariableIExpression("v")));

        MyIStack<IStatement> stack = new MyStack<>();
        MyIDictionary<String, Value> symT = new MyDictionary<>();
        MyIList<Value> out = new MyList<>();

        ProgState progState = new ProgState(stack, symT, out, ex1);

        IRepository repo = new Repository("logFile.txt");
        repo.add(progState);
        Service serv = new Service(repo);
        serv.allStep();
    }
}
