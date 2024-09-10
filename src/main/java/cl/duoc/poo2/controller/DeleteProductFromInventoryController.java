package cl.duoc.poo2.controller;

import cl.duoc.poo2.model.operation.OperationEnum;

public class DeleteProductFromInventoryController extends ControllerBase<String, Void> {

    public DeleteProductFromInventoryController() {
        super(OperationEnum.DELETE_PRODUCT);
    }

}
