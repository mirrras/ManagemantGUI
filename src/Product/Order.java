package Product;

import java.util.ArrayList;

public class Order {
    private Integer id;
    private Integer clientId;
    private ArrayList<OrderProduct> productIds;
    private Double totalPrice;

    public Order(Integer id, Integer clientId, ArrayList<OrderProduct> productIds, Double totalPrice) {
        this.id = id;
        this.clientId = clientId;
        this.productIds = productIds;
        this.totalPrice = calculateTotalPrice();
    }

    public Order(int id, Integer id1, OrderProduct orderProduct) {
    }

    private Double calculateTotalPrice() {
        Double total = 0.0;
        for (OrderProduct orderProduct : productIds) {
            total += orderProduct.getPriceOfProduct() * orderProduct.getQuantity();
        }
        return total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public ArrayList<OrderProduct> getProductIds() {
        return productIds;
    }

    public void setProductIds(ArrayList<OrderProduct> productIds) {
        this.productIds = productIds;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

