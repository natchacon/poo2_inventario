package cl.duoc.poo2.view;

import cl.duoc.poo2.controller.ControllerEnum;
import cl.duoc.poo2.exception.AbortInputDataException;
import cl.duoc.poo2.model.dto.ProductDTO;
import cl.duoc.poo2.view.validation.UserInputValidationEnum;
import cl.duoc.poo2.view.validation.UserInputValidatorFactory;

public class AddProductToInventoryCommand extends UserCommand<ProductDTO, Void> {


    public AddProductToInventoryCommand(Integer numeroMenu, String nombreMenu) {
        super(numeroMenu, nombreMenu, ControllerEnum.ADD_PRODUCT);
    }

    @Override
    public ProductDTO createViewObject() throws AbortInputDataException {
        String productId = requestInputData("Ingrese el codigo del producto", "Codigo no puede ser vacio", UserInputValidatorFactory.getInstance().getValidator(UserInputValidationEnum.NOT_EMPTY_STRING));
        String productName = requestInputData("Ingrese el nombre del producto", "Nombre no puede ser vacio", UserInputValidatorFactory.getInstance().getValidator(UserInputValidationEnum.NOT_EMPTY_STRING));
        String initialProductQuantity = requestInputData("Ingrese la cantidad del producto", "cantidad debe ser un numero entero", UserInputValidatorFactory.getInstance().getValidator(UserInputValidationEnum.IS_INTEGER));
        String productPrice = requestInputData("Ingrese el precio del producto", "precio debe ser un numero entero", UserInputValidatorFactory.getInstance().getValidator(UserInputValidationEnum.IS_INTEGER));
        return new ProductDTO(productId, productName, Integer.valueOf(productPrice), Integer.valueOf(initialProductQuantity));
    }

    @Override
    public void printResponse(Void response) {
        System.out.println("producto agregado al inventario");
    }
}
