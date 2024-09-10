package cl.duoc.poo2.view;

import cl.duoc.poo2.exception.ModelExceptionEnum;

public class UserExceptionMessage {

    public static void printExceptionMessage(ModelExceptionEnum modelExceptionEnum) {
        switch (modelExceptionEnum) {
            case PRODUCT_NOT_FOUND: {
                System.out.println("Producto no encontrado");
                break;
            }
            case PRODUCT_ALREADY_EXIST: {
                System.out.println("Producto ya existe");
                break;
            }
            default: {
            }
        }
    }

}
