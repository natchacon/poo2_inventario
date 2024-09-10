package cl.duoc.poo2.model.operation.inventory;

import cl.duoc.poo2.exception.ModelExceptionEnum;
import cl.duoc.poo2.exception.ModelLayerException;
import cl.duoc.poo2.model.dto.InventoryDTO;
import cl.duoc.poo2.model.dto.UpdateProductPriceDTO;
import cl.duoc.poo2.model.dto.UpdateProductQuantityDTO;
import cl.duoc.poo2.model.operation.OperationInterface;

public class UpdateProductPriceFromInventoryOperationImpl implements OperationInterface<UpdateProductPriceDTO, Void> {

    @Override
    public Void execute(UpdateProductPriceDTO input) throws ModelLayerException {
        if (InventoryDTO.getInstance().getProductMap().containsKey(input.getId())) {
            InventoryDTO.getInstance().getProductMap().get(input.getId()).setPrice(input.getPrice());
            return null;
        } else {
            throw new ModelLayerException(ModelExceptionEnum.PRODUCT_NOT_FOUND);
        }
    }
}
