package cl.duoc.poo2.view.validation;

import java.util.Arrays;
import java.util.InputMismatchException;

public class OptionsValidator implements UserInputValidator {

    String[] options;

    public OptionsValidator(String... options) {
        this.options = options;
    }

    @Override
    public void validate(String input) throws InputMismatchException {
        try {

            if (!Arrays.stream(options).anyMatch(option -> option.equals(input))) {
                throw new InputMismatchException();
            }
        } catch (Exception e) {
            throw new InputMismatchException();
        }
    }

}
