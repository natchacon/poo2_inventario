package cl.duoc.poo2.controller;

import cl.duoc.poo2.model.dto.FindProductDTO;
import cl.duoc.poo2.model.dto.ProductDTO;
import cl.duoc.poo2.model.operation.OperationEnum;

import java.util.List;

public class FindProductFromInventoryController extends ControllerBase<FindProductDTO, List<ProductDTO>> {
    public FindProductFromInventoryController() {
        super(OperationEnum.FIND_PRODUCT);
    }
}
