package cl.duoc.poo2.model.dto;

public class UpdateProductPriceDTO {

    private String id;
    private int price;

    public UpdateProductPriceDTO(String id, int price){
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int quantity) {
        this.price = quantity;
    }
}
