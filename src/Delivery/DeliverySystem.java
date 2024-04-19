package Delivery;

public abstract class DeliverySystem {
    protected Integer orderId;
    protected Integer clientId;
    protected Integer invoiceNumber;

    public DeliverySystem(Integer orderId, Integer clientId, Integer invoiceNumber) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.invoiceNumber = invoiceNumber;
    }

    protected DeliverySystem() {
    }

    // Абстрактный метод для обработки доставки
    public abstract void processDelivery();

    // Геттеры и сеттеры для полей класса DeliverySystem

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}

