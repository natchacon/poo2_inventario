package cl.duoc.poo2.model.operation.inventory;

import cl.duoc.poo2.exception.ModelExceptionEnum;
import cl.duoc.poo2.exception.ModelLayerException;
import cl.duoc.poo2.model.dto.InventoryDTO;
import cl.duoc.poo2.model.dto.UpdateProductPriceDTO;
import cl.duoc.poo2.model.operation.OperationInterface;

public class DeleteProductFromInventoryOperationImpl implements OperationInterface<String, Void> {

    @Override
    public Void execute(String input) throws ModelLayerException {
        if (InventoryDTO.getInstance().getProductMap().containsKey(input)) {
            InventoryDTO.getInstance().getProductMap().remove(input);
            return null;
        } else {
            throw new ModelLayerException(ModelExceptionEnum.PRODUCT_NOT_FOUND);
        }
    }
}
