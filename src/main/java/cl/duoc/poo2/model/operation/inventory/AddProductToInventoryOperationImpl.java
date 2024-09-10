package cl.duoc.poo2.model.operation.inventory;

import cl.duoc.poo2.exception.ModelLayerException;
import cl.duoc.poo2.model.dto.InventoryDTO;
import cl.duoc.poo2.model.dto.ProductDTO;
import cl.duoc.poo2.exception.ModelExceptionEnum;
import cl.duoc.poo2.model.operation.OperationInterface;

public class AddProductToInventoryOperationImpl implements OperationInterface<ProductDTO, Void> {


    @Override
    public Void execute(ProductDTO input) throws ModelLayerException {
        if (InventoryDTO.getInstance().getProductMap().containsKey(input.getId())) {
            throw new ModelLayerException(ModelExceptionEnum.PRODUCT_ALREADY_EXIST);
        } else {
            InventoryDTO.getInstance().getProductMap().put(input.getId(), input);
        }
        return null;
    }
}
