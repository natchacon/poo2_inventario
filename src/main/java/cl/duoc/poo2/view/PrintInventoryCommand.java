package cl.duoc.poo2.view;

import cl.duoc.poo2.controller.ControllerEnum;
import cl.duoc.poo2.exception.AbortInputDataException;
import cl.duoc.poo2.model.dto.ProductDTO;

import java.util.List;

public class PrintInventoryCommand extends UserCommand<Void, List<ProductDTO>> {


    public PrintInventoryCommand(Integer numeroMenu, String nombreMenu) {
        super(numeroMenu, nombreMenu, ControllerEnum.GET_INVENTORY);
    }


    @Override
    public Void createViewObject() throws AbortInputDataException {
        return null;
    }

    @Override
    public void printResponse(List<ProductDTO> response) {
        if(response==null || response.isEmpty()){
            System.out.println("No se han encontrado productos en el inventario");
        } else {
            System.out.println("Inventario:");
            response.forEach(productDTO -> System.out.println("Código: " + productDTO.getId() + " Nombre: " + productDTO.getName() + " Precio: " + productDTO.getPrice() + " Cantidad Disponible: " + productDTO.getQuantity()));
        }
    }
}
