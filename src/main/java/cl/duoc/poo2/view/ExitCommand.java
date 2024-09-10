package cl.duoc.poo2.view;

import cl.duoc.poo2.exception.AbortInputDataException;

public class ExitCommand extends UserCommand<Void, Void> {


    public ExitCommand(Integer optionMenuNumber, String optionMenuName) {
        super(optionMenuNumber, optionMenuName, null);
    }

    @Override
    public Void createViewObject() throws AbortInputDataException {
        System.exit(1);
        return null;
    }

    @Override
    public void printResponse(Void response) {

    }
}
