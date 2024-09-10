package cl.duoc.poo2.model.operation.inventory;

import cl.duoc.poo2.exception.ModelExceptionEnum;
import cl.duoc.poo2.exception.ModelLayerException;
import cl.duoc.poo2.model.dto.FindProductDTO;
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

import static org.junit.jupiter.api.Assertions.*;

@Isolated
@Execution(ExecutionMode.SAME_THREAD)
@Order(3)
class FindProductFromInventoryOperationImplTest {

    private static FindProductFromInventoryOperationImpl findProductFromInventoryOperation;

    @BeforeAll
    public static void setup() {
        InventoryDTO.getInstance().getProductMap().remove("1");
        InventoryDTO.getInstance().getProductMap().remove("2");
        findProductFromInventoryOperation = (FindProductFromInventoryOperationImpl) OperationFactory.getInstance().getModelService(OperationEnum.FIND_PRODUCT);
    }

    @Test
    public void when_FindProductByIdExist_then_ReturnProduct() throws ModelLayerException {
        AddProductToInventoryOperationImpl addProductToInventoryOperation = (AddProductToInventoryOperationImpl) OperationFactory.getInstance().getModelService(OperationEnum.ADD_PRODUCT);
        addProductToInventoryOperation.execute(new ProductDTO("1", "product_name", 1500, 100));
        FindProductDTO findProductDTO = new FindProductDTO();
        findProductDTO.setId("1");
        List<ProductDTO> list = findProductFromInventoryOperation.execute(findProductDTO);
        assertEquals("1", InventoryDTO.getInstance().getProductMap().get("1").getId());
        assertEquals("product_name", InventoryDTO.getInstance().getProductMap().get("1").getName());
        assertEquals(1500, InventoryDTO.getInstance().getProductMap().get("1").getPrice());
        assertEquals(100, InventoryDTO.getInstance().getProductMap().get("1").getQuantity());
    }


    @Test
    public void when_FindProductByNameExist_then_ReturnProduct() throws ModelLayerException {
        AddProductToInventoryOperationImpl addProductToInventoryOperation = (AddProductToInventoryOperationImpl) OperationFactory.getInstance().getModelService(OperationEnum.ADD_PRODUCT);
        addProductToInventoryOperation.execute(new ProductDTO("2", "product_name_2", 1500, 100));
        FindProductDTO findProductDTO = new FindProductDTO();
        findProductDTO.setName("product_name_2");
        List<ProductDTO> list = findProductFromInventoryOperation.execute(findProductDTO);
        assertEquals("2", InventoryDTO.getInstance().getProductMap().get("2").getId());
        assertEquals("product_name_2", InventoryDTO.getInstance().getProductMap().get("2").getName());
        assertEquals(1500, InventoryDTO.getInstance().getProductMap().get("2").getPrice());
        assertEquals(100, InventoryDTO.getInstance().getProductMap().get("2").getQuantity());
    }

    @Test
    public void when_FindProductByIdNotExist_then_ThrowModelExpetion() throws ModelLayerException {
        FindProductDTO findProductDTO = new FindProductDTO();
        findProductDTO.setId("3");
        ModelLayerException thrown = assertThrows(
                ModelLayerException.class,
                () -> findProductFromInventoryOperation.execute(findProductDTO)
        );
        assertNotNull(thrown);
        assertEquals(ModelExceptionEnum.PRODUCT_NOT_FOUND, thrown.getCauseEnum());
    }

    @Test
    public void when_FindProductByNameNotFound_then_ThrowModelExpetion() throws ModelLayerException {
        FindProductDTO findProductDTO = new FindProductDTO();
        findProductDTO.setName("product_name_3");
        ModelLayerException thrown = assertThrows(
                ModelLayerException.class,
                () -> findProductFromInventoryOperation.execute(findProductDTO)
        );
        assertNotNull(thrown);
        assertEquals(ModelExceptionEnum.PRODUCT_NOT_FOUND, thrown.getCauseEnum());
    }
}