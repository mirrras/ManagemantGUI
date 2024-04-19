package Delivery;

import java.util.ArrayList;

import java.util.ArrayList;

public class TastamatDelivery extends DeliverySystem {
    private ArrayList<Tastamat> listOfPostmats;

    public TastamatDelivery() {
    }

    public TastamatDelivery(Integer orderId, Integer clientId, Integer invoiceNumber,
                            ArrayList<Tastamat> listOfPostmats) {
        super(orderId, clientId, invoiceNumber); // Вызов конструктора суперкласса DeliverySystem
        this.listOfPostmats = listOfPostmats;
    }

    @Override
    public void processDelivery() {
        // Логика обработки доставки через терминалы Tastamat
        if (listOfPostmats.isEmpty()) {
            System.out.println("Ошибка: список терминалов Tastamat пуст.");
            return;
        }

        // Например, выбор ближайшего терминала из списка listOfPostmats
        Tastamat nearestPostmat = findNearestPostmat(); // Метод для поиска ближайшего терминала
        System.out.println("Доставка через терминал Tastamat " + nearestPostmat.getIndex());
    }

    private Tastamat findNearestPostmat() {
        // Здесь можно реализовать логику поиска ближайшего терминала
        // Например, вычисление расстояний и выбор наименьшего расстояния
        // В данном примере просто возвращаем первый доступный терминал (для целей демонстрации)
        return listOfPostmats.get(0);
    }

    // Геттеры и сеттеры для списка listOfPostmats

    public ArrayList<Tastamat> getListOfPostmats() {
        return listOfPostmats;
    }

    public void setListOfPostmats(ArrayList<Tastamat> listOfPostmats) {
        this.listOfPostmats = listOfPostmats;
    }
}

