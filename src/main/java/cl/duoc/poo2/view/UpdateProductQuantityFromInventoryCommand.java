package cl.duoc.poo2.view;

import cl.duoc.poo2.controller.ControllerEnum;
import cl.duoc.poo2.exception.AbortInputDataException;
import cl.duoc.poo2.model.dto.UpdateProductQuantityDTO;
import cl.duoc.poo2.view.validation.UserInputValidationEnum;
import cl.duoc.poo2.view.validation.UserInputValidatorFactory;

public class UpdateProductQuantityFromInventoryCommand extends UserCommand<UpdateProductQuantityDTO, Void> {

    public UpdateProductQuantityFromInventoryCommand(Integer numeroMenu, String nombreMenu) {
        super(numeroMenu, nombreMenu, ControllerEnum.UPDATE_PRODUCT_QUANTITY);
    }

    @Override
    public UpdateProductQuantityDTO createViewObject() throws AbortInputDataException {
        String productId = requestInputData("Ingrese el codigo del producto a actualizar", "Codigo no puede ser vacio", UserInputValidatorFactory.getInstance().getValidator(UserInputValidationEnum.NOT_EMPTY_STRING));
        String newProductQuantity = requestInputData("Ingrese la cantidad del producto a actualizar", "cantidad debe ser un numero entero", UserInputValidatorFactory.getInstance().getValidator(UserInputValidationEnum.IS_INTEGER));
        return new UpdateProductQuantityDTO(productId, Integer.valueOf(newProductQuantity));
    }

    @Override
    public void printResponse(Void response) {
        System.out.println("Cantidad actualizada con exito");
    }
}
