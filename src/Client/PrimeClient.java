package Client;
import java.util.ArrayList; // Импорт ArrayList для списков
import Product.Order; // Импорт класса Order

public class PrimeClient extends Client {
    private Integer discount;
    private ArrayList<Order> orders;

    public PrimeClient(Integer id, String name, String surname, Integer discount) {
        super(id, name, surname);
        this.discount = discount;
        this.orders = orders;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public int getOrdersCount() {
        // Предположим, что у клиента есть список его заказов, например, orders
        if (orders!= null) {
            return orders.size();
        } else {
            return 0; // Возвращаем 0, если список заказов пуст
        }
    }
}

