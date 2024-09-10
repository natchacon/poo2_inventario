package cl.duoc.poo2.view.validation;

import java.util.InputMismatchException;

public class PositiveIntegerValidator implements UserInputValidator {

    @Override
    public void validate(String input) throws InputMismatchException {
        try {
            Integer.valueOf(input);
        }
        catch (Exception e){
            throw new InputMismatchException();
        }
    }

}
