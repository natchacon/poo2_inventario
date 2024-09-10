package cl.duoc.poo2.model.operation.inventory;

import cl.duoc.poo2.exception.ModelLayerException;
import cl.duoc.poo2.model.dto.InventoryDTO;
import cl.duoc.poo2.model.dto.ProductDTO;
import cl.duoc.poo2.model.operation.OperationInterface;

import java.util.ArrayList;
import java.util.List;

public class GetInventoryOperationImpl implements OperationInterface<Void, List<ProductDTO>> {

    @Override
    public List<ProductDTO> execute(Void input) throws ModelLayerException {

        return new ArrayList<>(InventoryDTO.getInstance().getProductMap().values());
    }
}
