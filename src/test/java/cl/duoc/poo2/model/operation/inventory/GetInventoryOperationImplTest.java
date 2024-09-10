package cl.duoc.poo2.model.operation.inventory;

import cl.duoc.poo2.exception.ModelLayerException;
import cl.duoc.poo2.model.dto.InventoryDTO;
import cl.duoc.poo2.model.dto.ProductDTO;
import cl.duoc.poo2.model.operation.OperationEnum;
import cl.duoc.poo2.model.operation.OperationFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.Isolated;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Isolated
@Execution(ExecutionMode.SAME_THREAD)
@Order(4)
class GetInventoryOperationImplTest {

    private static GetInventoryOperationImpl getInventoryOperation;

    @BeforeAll
    public static void setup() {
        getInventoryOperation = (GetInventoryOperationImpl) OperationFactory.getInstance().getModelService(OperationEnum.GET_INVENTORY);
    }

    @Test
    public void when_GetInventoryIsEmpty_then_InventorySizeIsZero() throws ModelLayerException {
        List<ProductDTO> list = getInventoryOperation.execute(null);
        assertEquals(0, list.size());
    }

    @Test
    public void when_GetInventoryHasOneElement_then_InventorySizeIsOne() throws ModelLayerException {
        AddProductToInventoryOperationImpl addProductToInventoryOperation = (AddProductToInventoryOperationImpl) OperationFactory.getInstance().getModelService(OperationEnum.ADD_PRODUCT);
        addProductToInventoryOperation.execute(new ProductDTO("1", "product_name", 1500, 100));
        List<ProductDTO> list = getInventoryOperation.execute(null);
        assertEquals(1, list.size());
        assertEquals("1", InventoryDTO.getInstance().getProductMap().get("1").getId());
        assertEquals("product_name", InventoryDTO.getInstance().getProductMap().get("1").getName());
        assertEquals(1500, InventoryDTO.getInstance().getProductMap().get("1").getPrice());
        assertEquals(100, InventoryDTO.getInstance().getProductMap().get("1").getQuantity());
    }
}