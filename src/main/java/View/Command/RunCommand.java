package View.Command;

import Controller.Controller;
import Model.Exception.GenericException;

public class RunCommand extends ACommand {
    private final Controller controller;
    
    public RunCommand(String newKey, String newDescription, Controller controller) {
        super(newKey, newDescription);
        this.controller = controller;
    }
    
    @Override
    public void execute() {
        try {
            controller.allSteps();
        } catch (GenericException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}