package cl.duoc.poo2.model.dto;

import java.util.HashMap;
import java.util.Map;

public class InventoryDTO {

    private static InventoryDTO singleton;
    private Map<String, ProductDTO> productMap;

    private InventoryDTO() {
        productMap = new HashMap<>();
    }

    public static InventoryDTO getInstance(){
        if(singleton==null){
            singleton = new InventoryDTO();
        }
        return singleton;
    }

    public Map<String, ProductDTO> getProductMap() {
        return productMap;
    }


}
