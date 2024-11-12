import Controller.Service;
import Model.Expression.VariableIExpression;
import Model.ProgState;
import Model.Statement.*;
import Model.Type.IntType;
import Model.Value.IntValue;
import Model.Value.Value;
import Repository.IRepository;
import Repository.Repository;
import Utils.*;
import Model.Expression.ValueIExpression;

public class Main {
    public static void main(String[] args) {
        // int v;
        // print(v);
        IStatement ex1= new CompStatement(new VarDeclStatement("v", new IntType()), new CompStatement(new AssignStatement("v", new ValueIExpression(new IntValue(2))),
                new PrintStatement(new VariableIExpression("v"))));

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