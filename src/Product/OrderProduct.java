package Product;

public class OrderProduct {
    private Integer productId;
    private Double priceOfProduct;
    private Integer quantity;

    public OrderProduct(Integer productId, Double priceOfProduct, Integer quantity) {
        this.productId = productId;
        this.priceOfProduct = priceOfProduct;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getPriceOfProduct() {
        return priceOfProduct;
    }

    public void setPriceOfProduct(Double priceOfProduct) {
        this.priceOfProduct = priceOfProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

