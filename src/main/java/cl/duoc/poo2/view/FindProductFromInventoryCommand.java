package cl.duoc.poo2.view;

import cl.duoc.poo2.controller.ControllerEnum;
import cl.duoc.poo2.exception.AbortInputDataException;
import cl.duoc.poo2.model.dto.FindProductDTO;
import cl.duoc.poo2.model.dto.ProductDTO;
import cl.duoc.poo2.view.validation.UserInputValidationEnum;
import cl.duoc.poo2.view.validation.UserInputValidatorFactory;

import java.util.List;

public class FindProductFromInventoryCommand extends UserCommand<FindProductDTO, List<ProductDTO>> {


    public FindProductFromInventoryCommand(Integer numeroMenu, String nombreMenu) {
        super(numeroMenu, nombreMenu, ControllerEnum.FIND_PRODUCT);
    }

    @Override
    public FindProductDTO createViewObject() throws AbortInputDataException {
        FindProductDTO findProductDTO = new FindProductDTO();
        String searchType = requestInputData("Desea busca el producto por:  1.código o 2.nombre", "Debe ingresar 1 o 2", UserInputValidatorFactory.getInstance().getValidator(UserInputValidationEnum.SEARCH_CHOICES));
        if (searchType.equalsIgnoreCase("1")) {
            String codigoProducto = requestInputData("Ingrese el código del producto", "código no puede ser vacio", UserInputValidatorFactory.getInstance().getValidator(UserInputValidationEnum.NOT_EMPTY_STRING));
            findProductDTO.setId(codigoProducto);
        } else {
            String nombreProducto = requestInputData("Ingrese el nombre del producto", "nombre no puede ser vacio", UserInputValidatorFactory.getInstance().getValidator(UserInputValidationEnum.NOT_EMPTY_STRING));
            findProductDTO.setName(nombreProducto);
        }
        return findProductDTO;
    }

    @Override
    public void printResponse(List<ProductDTO> response) {
        if(response==null || response.isEmpty()){
            System.out.println("No se han encontrado producto");
        } else {
            System.out.println("Resultado de su busqueda:");
            response.forEach(productDTO -> System.out.println("Código: " + productDTO.getId() + " Nombre: " + productDTO.getName() + " Precio: " + productDTO.getPrice() + " Cantidad Disponible: " + productDTO.getQuantity()));
        }

    }
}
