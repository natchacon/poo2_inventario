package cl.duoc.poo2.view;

import cl.duoc.poo2.controller.ControllerEnum;
import cl.duoc.poo2.exception.AbortInputDataException;
import cl.duoc.poo2.model.dto.UpdateProductQuantityDTO;
import cl.duoc.poo2.view.validation.UserInputValidationEnum;
import cl.duoc.poo2.view.validation.UserInputValidatorFactory;

public class DeleteProductFromInventoryCommand extends UserCommand<String, Void> {

    public DeleteProductFromInventoryCommand(Integer numeroMenu, String nombreMenu) {
        super(numeroMenu, nombreMenu, ControllerEnum.DELETE_PRODUCT);
    }

    @Override
    public String createViewObject() throws AbortInputDataException {
        String productId = requestInputData("Ingrese el codigo del producto a eliminar", "Codigo no puede ser vacio", UserInputValidatorFactory.getInstance().getValidator(UserInputValidationEnum.NOT_EMPTY_STRING));
        return productId;
    }

    @Override
    public void printResponse(Void response) {
        System.out.println("producto eliminado con exito");
    }
}
