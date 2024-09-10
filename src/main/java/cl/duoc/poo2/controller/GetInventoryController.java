package cl.duoc.poo2.controller;

import cl.duoc.poo2.model.dto.ProductDTO;
import cl.duoc.poo2.model.operation.OperationEnum;

import java.util.List;

public class GetInventoryController extends ControllerBase<Void, List<ProductDTO>> {

    public GetInventoryController() {
        super(OperationEnum.GET_INVENTORY);
    }

}
