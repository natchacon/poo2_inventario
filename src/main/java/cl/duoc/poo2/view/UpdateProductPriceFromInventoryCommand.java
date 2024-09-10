package cl.duoc.poo2.view;

import cl.duoc.poo2.controller.ControllerEnum;
import cl.duoc.poo2.exception.AbortInputDataException;
import cl.duoc.poo2.model.dto.UpdateProductPriceDTO;
import cl.duoc.poo2.view.validation.UserInputValidationEnum;
import cl.duoc.poo2.view.validation.UserInputValidatorFactory;

public class UpdateProductPriceFromInventoryCommand extends UserCommand<UpdateProductPriceDTO, Void> {

    public UpdateProductPriceFromInventoryCommand(Integer numeroMenu, String nombreMenu) {
        super(numeroMenu, nombreMenu, ControllerEnum.UPDATE_PRODUCT_PRICE);
    }

    @Override
    public UpdateProductPriceDTO createViewObject() throws AbortInputDataException {
        String productId = requestInputData("Ingrese el codigo del producto a actualizar", "Codigo no puede ser vacio", UserInputValidatorFactory.getInstance().getValidator(UserInputValidationEnum.NOT_EMPTY_STRING));
        String newProductPrice = requestInputData("Ingrese el precio del producto a actualizar", "precio debe ser un numero entero", UserInputValidatorFactory.getInstance().getValidator(UserInputValidationEnum.IS_INTEGER));
        return new UpdateProductPriceDTO(productId, Integer.valueOf(newProductPrice));
    }

    @Override
    public void printResponse(Void response) {
        System.out.println("Precio actualizado con exito");
    }
}
