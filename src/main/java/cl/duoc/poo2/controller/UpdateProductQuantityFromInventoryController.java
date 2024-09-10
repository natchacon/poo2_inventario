package cl.duoc.poo2.controller;

import cl.duoc.poo2.model.dto.UpdateProductQuantityDTO;
import cl.duoc.poo2.model.operation.OperationEnum;

public class UpdateProductQuantityFromInventoryController extends ControllerBase<UpdateProductQuantityDTO, Void> {

    public UpdateProductQuantityFromInventoryController() {
        super(OperationEnum.UPDATE_PRODUCT_QUANTITY);
    }

}
