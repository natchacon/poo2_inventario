package cl.duoc.poo2.controller;

import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {

    private static ControllerFactory singleton;
    private Map<ControllerEnum, Controller> controllerMap;

    private ControllerFactory() {
        controllerMap = new HashMap<>();
        controllerMap.put(ControllerEnum.ADD_PRODUCT, new AddProductToInventoryController());
        controllerMap.put(ControllerEnum.UPDATE_PRODUCT_QUANTITY, new UpdateProductQuantityFromInventoryController());
        controllerMap.put(ControllerEnum.UPDATE_PRODUCT_PRICE, new UpdateProductPriceFromInventoryController());
        controllerMap.put(ControllerEnum.GET_INVENTORY, new GetInventoryController());
        controllerMap.put(ControllerEnum.FIND_PRODUCT, new FindProductFromInventoryController());
        controllerMap.put(ControllerEnum.DELETE_PRODUCT, new DeleteProductFromInventoryController());
    }

    public static ControllerFactory getInstance() {
        if (singleton == null) {
            singleton = new ControllerFactory();
        }
        return singleton;
    }

    public Controller getController(ControllerEnum controllerEnum) {
        return controllerMap.get(controllerEnum);
    }

}
