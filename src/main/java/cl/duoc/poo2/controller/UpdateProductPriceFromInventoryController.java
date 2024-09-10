package cl.duoc.poo2.controller;

import cl.duoc.poo2.model.dto.UpdateProductQuantityDTO;
import cl.duoc.poo2.model.operation.OperationEnum;

public class UpdateProductPriceFromInventoryController extends ControllerBase<UpdateProductQuantityDTO, Void> {

    public UpdateProductPriceFromInventoryController() {
        super(OperationEnum.UPDATE_PRODUCT_PRICE);
    }

}
