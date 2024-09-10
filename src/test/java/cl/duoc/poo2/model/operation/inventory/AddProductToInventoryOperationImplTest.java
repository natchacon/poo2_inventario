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
@Order(1)
class AddProductToInventoryOperationImplTest {

    private static AddProductToInventoryOperationImpl addProductToInventoryOperation;

    @BeforeAll
    public static void setup() {
        InventoryDTO.getInstance().getProductMap().remove("1");
        InventoryDTO.getInstance().getProductMap().remove("2");
        addProductToInventoryOperation = (AddProductToInventoryOperationImpl) OperationFactory.getInstance().getModelService(OperationEnum.ADD_PRODUCT);
    }

    @Test
    public void when_addNewProduct_then_InventoryHasNewProduct() throws ModelLayerException {
        addProductToInventoryOperation.execute(new ProductDTO("1", "product_name", 1500, 100));
        assertEquals(1, InventoryDTO.getInstance().getProductMap().values().size());
        assertEquals("1", InventoryDTO.getInstance().getProductMap().get("1").getId());
        assertEquals("product_name", InventoryDTO.getInstance().getProductMap().get("1").getName());
        assertEquals(1500, InventoryDTO.getInstance().getProductMap().get("1").getPrice());
        assertEquals(100, InventoryDTO.getInstance().getProductMap().get("1").getQuantity());
    }

    @Test
    public void when_addDuplicatedProduct_then_InventoryThrowModelException() throws ModelLayerException {
        addProductToInventoryOperation.execute(new ProductDTO("2", "product_name_2", 10000, 10));
        ModelLayerException thrown = assertThrows(
                ModelLayerException.class,
                () -> addProductToInventoryOperation.execute(new ProductDTO("2", "product_name_same_code", 9000, 5))
        );
        assertNotNull(thrown);
        assertEquals(ModelExceptionEnum.PRODUCT_ALREADY_EXIST, thrown.getCauseEnum());
    }

    @Test
    public void when_addNullProduct_then_ThrowNullPointerException() throws ModelLayerException {
        NullPointerException thrown = assertThrows(
                NullPointerException.class,
                () -> addProductToInventoryOperation.execute(null)
        );
        assertNotNull(thrown);
        assertEquals(NullPointerException.class, thrown.getClass());
    }
}