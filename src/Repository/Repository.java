package Repository;

import Exceptions.MyException;
import Model.ProgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<ProgState> Programs;
    String LogFilePath;

    public Repository(String NewLogFilePath) {
        this.Programs = new ArrayList<>();
        this.LogFilePath = NewLogFilePath;
    }

    @Override
    public ProgState getCurrentProgram() {
        return this.Programs.get(0);
    }

    @Override
    public void add(ProgState s) {
        this.Programs.add(s);
    }

    @Override
    public void logProgState() throws MyException {
        if(this.LogFilePath != null) {
            PrintWriter logFile;
            try {
                logFile = new PrintWriter(new BufferedWriter(new FileWriter(LogFilePath, true)));
            } catch (IOException e) {
                throw new MyException(e.getMessage());
            }
        }
    }
}
