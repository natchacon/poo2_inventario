package cl.duoc.poo2.model.dto;

public class UpdateProductQuantityDTO {

    private String id;
    private int quantity;

    public UpdateProductQuantityDTO(String id, int quantity){
        this.id = id;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
