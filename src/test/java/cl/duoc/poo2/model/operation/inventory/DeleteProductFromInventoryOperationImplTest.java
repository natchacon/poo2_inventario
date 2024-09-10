package cl.duoc.poo2.model.operation.inventory;

import cl.duoc.poo2.exception.ModelExceptionEnum;
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

import static org.junit.jupiter.api.Assertions.*;

@Isolated
@Execution(ExecutionMode.SAME_THREAD)
@Order(2)
class DeleteProductFromInventoryOperationImplTest {

    private static DeleteProductFromInventoryOperationImpl deleteProductFromInventoryOperation;

    @BeforeAll
    public static void setup() {
        InventoryDTO.getInstance().getProductMap().remove("1");
        InventoryDTO.getInstance().getProductMap().remove("2");
        deleteProductFromInventoryOperation = (DeleteProductFromInventoryOperationImpl) OperationFactory.getInstance().getModelService(OperationEnum.DELETE_PRODUCT);
    }

    @Test
    public void when_deleteProduct_then_ProductIsDeletedFromInventory() throws ModelLayerException {
        AddProductToInventoryOperationImpl addProductToInventoryOperation = (AddProductToInventoryOperationImpl) OperationFactory.getInstance().getModelService(OperationEnum.ADD_PRODUCT);
        addProductToInventoryOperation.execute(new ProductDTO("1", "product_name", 1500, 100));
        assertEquals(1, InventoryDTO.getInstance().getProductMap().values().size());
        deleteProductFromInventoryOperation.execute("1");
        assertEquals(0, InventoryDTO.getInstance().getProductMap().values().size());
    }

    @Test
    public void whenDeleteProductOutOfInventory_then_ThrowModelException() throws ModelLayerException {
        ModelLayerException thrown = assertThrows(
                ModelLayerException.class,
                () -> deleteProductFromInventoryOperation.execute("2")
        );
        assertNotNull(thrown);
        assertEquals(ModelExceptionEnum.PRODUCT_NOT_FOUND, thrown.getCauseEnum());
    }

}