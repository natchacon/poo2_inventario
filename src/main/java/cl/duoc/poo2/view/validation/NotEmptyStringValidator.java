package cl.duoc.poo2.view.validation;

import java.util.InputMismatchException;

public class NotEmptyStringValidator implements UserInputValidator {

    @Override
    public void validate(String input) throws InputMismatchException {
         if(!(input!=null && !input.equals(""))){
             throw new InputMismatchException();
         }
    }

}
