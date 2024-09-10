package cl.duoc.poo2.controller;

import cl.duoc.poo2.model.dto.ProductDTO;
import cl.duoc.poo2.model.operation.OperationEnum;

public class AddProductToInventoryController extends ControllerBase<ProductDTO, Void> {
    public AddProductToInventoryController() {
        super(OperationEnum.ADD_PRODUCT);
    }
}
