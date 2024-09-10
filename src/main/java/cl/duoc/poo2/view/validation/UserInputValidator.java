package cl.duoc.poo2.view.validation;

import java.util.InputMismatchException;

public interface UserInputValidator {

    void validate(String input) throws InputMismatchException;

}
