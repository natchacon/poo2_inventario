package cl.duoc.poo2.view;

import cl.duoc.poo2.controller.ControllerEnum;
import cl.duoc.poo2.controller.ControllerFactory;
import cl.duoc.poo2.exception.AbortInputDataException;
import cl.duoc.poo2.model.dto.FindProductDTO;
import cl.duoc.poo2.model.dto.InventoryDTO;
import cl.duoc.poo2.model.dto.ProductDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.Isolated;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@Isolated
@Execution(ExecutionMode.SAME_THREAD)
@Order(7)
class InventoryIntegrationTest {

    private static AddProductToInventoryCommand addProductToInventoryCommand = null;
    private static FindProductFromInventoryCommand findProductFromInventoryCommand = null;

    private static DeleteProductFromInventoryCommand deleteProductFromInventoryCommand = null;

    @BeforeAll
    public static void setup() throws AbortInputDataException, IllegalAccessException {
        InventoryDTO.getInstance().getProductMap().remove("1");
        InventoryDTO.getInstance().getProductMap().remove("2");
        addProductToInventoryCommand = mock(AddProductToInventoryCommand.class);
        overWriteSuperClassField(addProductToInventoryCommand, "controller", ControllerFactory.getInstance().getController(ControllerEnum.ADD_PRODUCT));
        Mockito.when(addProductToInventoryCommand.createViewObject()).thenReturn(new ProductDTO("1", "prod_name", 1000, 100));
        Mockito.doCallRealMethod().when(addProductToInventoryCommand).executeCommand();
        Mockito.doCallRealMethod().when(addProductToInventoryCommand).printResponse(any());

        findProductFromInventoryCommand = mock(FindProductFromInventoryCommand.class);
        FindProductDTO findProductDTO = new FindProductDTO();
        findProductDTO.setId("1");
        Mockito.when(findProductFromInventoryCommand.createViewObject()).thenReturn(findProductDTO);
        Mockito.doCallRealMethod().when(findProductFromInventoryCommand).executeCommand();
        Mockito.doCallRealMethod().when(findProductFromInventoryCommand).printResponse(any());
        overWriteSuperClassField(findProductFromInventoryCommand, "controller", ControllerFactory.getInstance().getController(ControllerEnum.FIND_PRODUCT));

        deleteProductFromInventoryCommand = mock(DeleteProductFromInventoryCommand.class);
        Mockito.when(deleteProductFromInventoryCommand.createViewObject()).thenReturn("1");
        Mockito.doCallRealMethod().when(deleteProductFromInventoryCommand).executeCommand();
        Mockito.doCallRealMethod().when(deleteProductFromInventoryCommand).printResponse(any());
        overWriteSuperClassField(deleteProductFromInventoryCommand, "controller", ControllerFactory.getInstance().getController(ControllerEnum.DELETE_PRODUCT));
    }

    private static void overWriteSuperClassField(Object o, String fieldName, Object value) throws IllegalAccessException {
        Optional<Field> fieldOptional = Arrays.stream(o.getClass().getSuperclass().getDeclaredFields()).filter(field -> field.getName().equals(fieldName)).findFirst();
        if (fieldOptional.isPresent()) {
            Field field = fieldOptional.get();
            field.setAccessible(true);
            field.set(o, value);
        }
    }

    @Test
    public void integrationTest() throws AbortInputDataException {
        //Se agrega producto al inventario
        addProductToInventoryCommand.executeCommand();
        //Se valida que el producto fue agregado correctamente
        assertEquals(1, InventoryDTO.getInstance().getProductMap().values().size());
        assertEquals("1", InventoryDTO.getInstance().getProductMap().get("1").getId());
        assertEquals("prod_name", InventoryDTO.getInstance().getProductMap().get("1").getName());
        Mockito.verify(addProductToInventoryCommand, times(1)).printResponse(any());
        //Se vuelve a agregar el mismo producto
        addProductToInventoryCommand.executeCommand();
        //Se valida que no se agrega y no se ejecuta metodo de exito de la operacion, es decir, muestra error
        assertEquals(1, InventoryDTO.getInstance().getProductMap().values().size());
        Mockito.verify(addProductToInventoryCommand, times(1)).printResponse(any());
        //Se busca el producto ingresado al inventario y se valida que se encuentra con la llamada al metodo exitoso
        findProductFromInventoryCommand.executeCommand();
        assertEquals(1, InventoryDTO.getInstance().getProductMap().values().size());
        Mockito.verify(findProductFromInventoryCommand, times(1)).printResponse(any());
        //Se busca un producto que no esta en el inventario y se valida que no se llama al metodo de exito
        FindProductDTO findProductDTO = new FindProductDTO();
        findProductDTO.setId("2");
        Mockito.when(findProductFromInventoryCommand.createViewObject()).thenReturn(findProductDTO);
        assertEquals(1, InventoryDTO.getInstance().getProductMap().values().size());
        Mockito.verify(findProductFromInventoryCommand, times(1)).printResponse(any());
        //Se elimina el producto del inventario y se valida que no tiene elementos
        deleteProductFromInventoryCommand.executeCommand();
        assertEquals(0, InventoryDTO.getInstance().getProductMap().values().size());
        Mockito.verify(deleteProductFromInventoryCommand, times(1)).printResponse(any());
        //Se vuelve a elimina el producto del inventario y no se llama al metodo de exito de la operacion
        deleteProductFromInventoryCommand.executeCommand();
        assertEquals(0, InventoryDTO.getInstance().getProductMap().values().size());
        Mockito.verify(deleteProductFromInventoryCommand, times(1)).printResponse(any());

    }

}