package cl.duoc.poo2.view;

import cl.duoc.poo2.controller.Controller;
import cl.duoc.poo2.controller.ControllerDTO;
import cl.duoc.poo2.controller.ControllerEnum;
import cl.duoc.poo2.controller.ControllerFactory;
import cl.duoc.poo2.exception.AbortInputDataException;
import cl.duoc.poo2.exception.ControllerException;
import cl.duoc.poo2.exception.ModelLayerException;
import cl.duoc.poo2.view.validation.UserInputValidator;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class UserCommand<I, O> {

    private static final String EXIT_MESSAGE = " (ingrese 'S' para salir y volver al menú principal)";
    protected Scanner scanner = new Scanner(System.in);
    private Integer optionMenuNumber;
    private String optionMenuName;
    private Controller controller;

    public UserCommand(Integer optionMenuNumber, String optionMenuName, ControllerEnum controllerEnum) {
        this.optionMenuNumber = optionMenuNumber;
        this.optionMenuName = optionMenuName;
        if (controllerEnum != null) {
            this.controller = ControllerFactory.getInstance().getController(controllerEnum);
        }
    }

    public Integer getOptionMenuNumber() {
        return optionMenuNumber;
    }

    public String getOptionMenuName() {
        return optionMenuName;
    }

    protected String requestInputData(String requestText, String textError, UserInputValidator userInputValidator) throws AbortInputDataException {

        String inputData = null;
        Boolean inputDataIsValid = null;
        do {
            System.out.println(requestText + EXIT_MESSAGE);
            inputData = scanner.nextLine();
            if (inputData.equalsIgnoreCase("s")) {
                throw new AbortInputDataException();
            }
            try {
                userInputValidator.validate(inputData);
                inputDataIsValid = Boolean.TRUE;
            } catch (InputMismatchException e) {
                System.out.println(textError);
                inputDataIsValid = Boolean.FALSE;
            }
        } while (!inputDataIsValid);
        return inputData;
    }

    public void executeCommand() {
        try {
            ControllerDTO controllerDTO = controller.execute(createViewObject());
            if (controllerDTO.getSuccessfully()) {
                printResponse((O)controllerDTO.getObject());
            } else {
                ControllerException ce = controllerDTO.getE();
                if (ce.getCause() instanceof ModelLayerException) {
                    ModelLayerException me = (ModelLayerException) ce.getCause();
                    if (me.getCauseEnum() != null) {
                        UserExceptionMessage.printExceptionMessage(me.getCauseEnum());
                    }
                }

            }
        } catch (AbortInputDataException e) {
            //continue and back to main menu
        } catch (RuntimeException e) {
            System.out.println("Error no controlado: " + e.getCause());
        }
    }

    public abstract I createViewObject() throws AbortInputDataException;

    public abstract void printResponse(O response);

}
