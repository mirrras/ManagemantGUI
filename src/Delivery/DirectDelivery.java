package Delivery;

public class DirectDelivery extends DeliverySystem {
    private String city;
    private String streetName;
    private Integer houseNumber;
    private Integer flatNumber;

    public DirectDelivery() {

    }

    public DirectDelivery(Integer orderId, Integer clientId, Integer invoiceNumber,
                          String city, String streetName, Integer houseNumber, Integer flatNumber) {
        super(orderId, clientId, invoiceNumber); // Вызов конструктора суперкласса DeliverySystem
        this.city = city;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }

    @Override
    public void processDelivery() {
        // Логика обработки прямой доставки
        System.out.println("Обработка прямой доставки для заказа: " + orderId);
        System.out.println("Адрес доставки: " + city + ", " + streetName + ", дом " + houseNumber + ", квартира " + flatNumber);
        // Дополнительная логика, например, сохранение информации о доставке в базу данных
    }

    // Геттеры и сеттеры для дополнительных полей класса DirectDelivery

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }
}
