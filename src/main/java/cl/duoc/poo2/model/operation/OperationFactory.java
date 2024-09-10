package cl.duoc.poo2.model.operation;

import cl.duoc.poo2.model.operation.inventory.*;

import java.util.HashMap;
import java.util.Map;

public class OperationFactory {


    private static OperationFactory singleton;
    private Map<OperationEnum, OperationInterface> modelServiceMap;

    private OperationFactory(){
        modelServiceMap = new HashMap<>();
        modelServiceMap.put(OperationEnum.ADD_PRODUCT, new AddProductToInventoryOperationImpl());
        modelServiceMap.put(OperationEnum.UPDATE_PRODUCT_QUANTITY, new UpdateProductQuantityFromInventoryOperationImpl());
        modelServiceMap.put(OperationEnum.UPDATE_PRODUCT_PRICE, new UpdateProductPriceFromInventoryOperationImpl());
        modelServiceMap.put(OperationEnum.GET_INVENTORY, new GetInventoryOperationImpl());
        modelServiceMap.put(OperationEnum.FIND_PRODUCT, new FindProductFromInventoryOperationImpl());
        modelServiceMap.put(OperationEnum.DELETE_PRODUCT, new DeleteProductFromInventoryOperationImpl());
    }

    public static OperationFactory getInstance(){
        if(singleton==null){
            singleton = new OperationFactory();
        }
        return singleton;
    }

    public OperationInterface getModelService(OperationEnum modelServiceEnum){
        return modelServiceMap.get(modelServiceEnum);
    }


}
