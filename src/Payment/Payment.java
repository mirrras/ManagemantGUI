package Payment;

import Client.Client;
import Client.LoyalClient;
import Client.PrimeClient;

import java.util.Date;

public class Payment {
    private Integer id;
    private Integer clientId;
    private Integer orderId;
    private Date date;
    private Boolean status;
    private Double orderTotalPrice;

    public Payment(Integer id, Integer clientId, Integer orderId, Date date, boolean b, Double orderTotalPrice) {
        this.id = id;
        this.clientId = clientId;
        this.orderId = orderId;
        this.date = date;
        this.status = false; // Изначально статус оплаты false
        this.orderTotalPrice = orderTotalPrice;
    }

    public void calculatePayment(Client client) {
        // Логика расчета платежа в зависимости от типа клиента
        if (client instanceof PrimeClient) {
            this.orderTotalPrice *= 0.95; // Применение скидки 5% для PrimeClient
        } else if (client instanceof LoyalClient) {
            this.orderTotalPrice *= 0.90; // Применение скидки 10% для LoyalClient
        }
    }

    public void generateReceipt() {
        // Логика генерации чека в текстовый файл
        // Например, использование FileWriter для записи информации о платеже в файл
    }

    // Геттеры и сеттеры для всех полей
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(Double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }
}


