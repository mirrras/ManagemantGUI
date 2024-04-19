package Client;

public class LoyalClient extends Client {
    private Integer discount;
    private Double cashback;

    public LoyalClient(Integer id, String name, String surname, Integer discount, Double cashback) {
        super(id, name, surname);
        this.discount = discount;
        this.cashback = cashback;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getCashback() {
        return cashback;
    }

    public void setCashback(Double cashback) {
        this.cashback = cashback;
    }
}

