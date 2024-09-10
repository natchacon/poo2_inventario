package cl.duoc.poo2.model.operation.inventory;

import cl.duoc.poo2.exception.ModelExceptionEnum;
import cl.duoc.poo2.exception.ModelLayerException;
import cl.duoc.poo2.model.dto.InventoryDTO;
import cl.duoc.poo2.model.dto.ProductDTO;
import cl.duoc.poo2.model.dto.UpdateProductQuantityDTO;
import cl.duoc.poo2.model.operation.OperationEnum;
import cl.duoc.poo2.model.operation.OperationFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.Isolated;

import static org.junit.jupiter.api.Assertions.*;

@Isolated
@Execution(ExecutionMode.SAME_THREAD)
@Order(6)
class UpdateProductQuantityFromInventoryOperationImplTest {

    private static UpdateProductQuantityFromInventoryOperationImpl updateProductQuantityFromInventoryOperation;

    @BeforeAll
    public static void setup() {
        InventoryDTO.getInstance().getProductMap().remove("1");
        InventoryDTO.getInstance().getProductMap().remove("2");
        updateProductQuantityFromInventoryOperation = (UpdateProductQuantityFromInventoryOperationImpl) OperationFactory.getInstance().getModelService(OperationEnum.UPDATE_PRODUCT_QUANTITY);
    }

    @Test
    public void whenUpdateProductQuantity_then_QuantityIsUpdated() throws ModelLayerException {
        AddProductToInventoryOperationImpl addProductToInventoryOperation = (AddProductToInventoryOperationImpl) OperationFactory.getInstance().getModelService(OperationEnum.ADD_PRODUCT);
        addProductToInventoryOperation.execute(new ProductDTO("1", "product_name", 1500, 100));
        assertEquals(100, InventoryDTO.getInstance().getProductMap().get("1").getQuantity());
        updateProductQuantityFromInventoryOperation.execute(new UpdateProductQuantityDTO("1", 50));
        assertEquals(50, InventoryDTO.getInstance().getProductMap().get("1").getQuantity());
    }


    @Test
    public void whenUpdateProductOutOfInventory_then_ThrowModelException() throws ModelLayerException {
        ModelLayerException thrown = assertThrows(
                ModelLayerException.class,
                () -> updateProductQuantityFromInventoryOperation.execute(new UpdateProductQuantityDTO("2", 100))
        );
        assertNotNull(thrown);
        assertEquals(ModelExceptionEnum.PRODUCT_NOT_FOUND, thrown.getCauseEnum());
    }
}