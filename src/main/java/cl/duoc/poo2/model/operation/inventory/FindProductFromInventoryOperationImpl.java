package cl.duoc.poo2.model.operation.inventory;

import cl.duoc.poo2.exception.ModelLayerException;
import cl.duoc.poo2.model.dto.FindProductDTO;
import cl.duoc.poo2.model.dto.InventoryDTO;
import cl.duoc.poo2.model.dto.ProductDTO;
import cl.duoc.poo2.exception.ModelExceptionEnum;
import cl.duoc.poo2.model.operation.OperationInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindProductFromInventoryOperationImpl implements OperationInterface<FindProductDTO, List<ProductDTO>> {

    @Override
    public List<ProductDTO> execute(FindProductDTO input) throws ModelLayerException {
        List<ProductDTO> queryResult = new ArrayList();
        if (input.getId() != null && !input.getId().equals("")) {
            ProductDTO productDTO = InventoryDTO.getInstance().getProductMap().get(input.getId());
            if (productDTO != null) {
                queryResult.add(productDTO);
            } else {
                throw new ModelLayerException(ModelExceptionEnum.PRODUCT_NOT_FOUND);
            }
        } else {
            queryResult = InventoryDTO.getInstance().getProductMap().values().stream().filter(productDTO -> productDTO.getName().equalsIgnoreCase(input.getName())).collect(Collectors.toList());
            if (queryResult == null || queryResult.isEmpty()) {
                throw new ModelLayerException(ModelExceptionEnum.PRODUCT_NOT_FOUND);
            }
        }
        return queryResult;
    }
}
