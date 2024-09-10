package cl.duoc.poo2.view.validation;

import java.util.HashMap;
import java.util.Map;

public class UserInputValidatorFactory {

    private static Map<UserInputValidationEnum, UserInputValidator> validatorsMap = null;
    private static UserInputValidatorFactory singleton;

    private UserInputValidatorFactory() {
        validatorsMap = new HashMap<>();
        validatorsMap.put(UserInputValidationEnum.IS_INTEGER, new PositiveIntegerValidator());
        validatorsMap.put(UserInputValidationEnum.NOT_EMPTY_STRING, new NotEmptyStringValidator());
        validatorsMap.put(UserInputValidationEnum.SEARCH_CHOICES, new OptionsValidator("1", "2"));
    }

    public static UserInputValidatorFactory getInstance() {
        if (singleton == null) {
            singleton = new UserInputValidatorFactory();
        }
        return singleton;
    }

    public UserInputValidator getValidator(UserInputValidationEnum validationEnum) {
        return validatorsMap.get(validationEnum);
    }
}
