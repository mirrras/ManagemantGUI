import Client.Client;
import Client.PrimeClient;
import Client.LoyalClient;
import Client.OrdinaryClient;
import Payment.Payment;
import Product.Product;
import Product.Order;
import Product.OrderProduct;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

public class OnlineStoreManagement {
    private ArrayList<Product> products;
    private ArrayList<Client> clients;
    private ArrayList<Order> orders;

    public OnlineStoreManagement() {
        this.products = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    // Методы для управления продуктами (CRUD операции)

    public void addProduct(Product product) {
        products.add(product);
    }

    public void updateProduct(Product updatedProduct) {
        for (Product product : products) {
            if (product.getId().equals(updatedProduct.getId())) {
                // Найден нужный продукт по ID, обновляем его
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                product.setStock(updatedProduct.getStock());
                break;
            }
        }
    }

    public void deleteProduct(Integer productId) {
        products.removeIf(product -> product.getId().equals(productId));
    }

    public Product findProductById(Integer productId) {
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null; // Продукт с указанным ID не найден
    }

    // Методы для управления клиентами (CRUD операции)

    public void addClient(Client client) {
        clients.add(client);
    }

    public void updateClient(Client updatedClient) {
        for (Client client : clients) {
            if (client.getId().equals(updatedClient.getId())) {
                // Найден нужный клиент по ID, обновляем его
                client.setName(updatedClient.getName());
                client.setSurname(updatedClient.getSurname());
                break;
            }
        }
    }

    public void deleteClient(Integer clientId) {
        clients.removeIf(client -> client.getId().equals(clientId));
    }

    public Client findClientById(Integer clientId) {
        for (Client client : clients) {
            if (client.getId().equals(clientId)) {
                return client;
            }
        }
        return null; // Клиент с указанным ID не найден
    }

    // Методы для управления заказами (CRUD операции)
    public ArrayList<Order> getOrders() {
        return orders;
    }
    public void addOrder(Order order) {
        orders.add(order);
    }

    public void deleteOrder(Integer orderId) {
        orders.removeIf(order -> order.getId().equals(orderId));
    }

    public Order findOrderById(Integer orderId) {
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                return order;
            }
        }
        return null; // Заказ с указанным ID не найден
    }


    public boolean createOrder(Integer clientId, ArrayList<Integer> productIds) {
        // Находим клиента по ID
        Client client = findClientById(clientId);
        if (client == null) {
            System.out.println("Ошибка: Клиент с ID " + clientId + " не найден.");
            return false;
        }

        // Проверяем наличие товаров в наличии
        boolean productsAvailable = true;
        for (Integer productId : productIds) {
            Product product = findProductById(productId);
            if (product == null || product.getStock() <= 0) {
                System.out.println("Ошибка: Товар с ID " + productId + " отсутствует в наличии.");
                productsAvailable = false;
                break;
            }
        }

        if (!productsAvailable) {
            return false;
        }

        // Создаем новый заказ
        ArrayList<OrderProduct> orderProducts = new ArrayList<>();
        Double totalPrice = 0.0;
        for (Integer productId : productIds) {
            Product product = findProductById(productId);
            if (product != null) {
                OrderProduct orderProduct = new OrderProduct(productId, product.getPrice(), 1);
                orderProducts.add(orderProduct);
                product.setStock(product.getStock() - 1); // Уменьшаем количество товара
                totalPrice += product.getPrice();
            }
        }

        Order newOrder = new Order(generateOrderId(), clientId, orderProducts, totalPrice);
        addOrder(newOrder);

        // Применяем скидку и кэшбэк
        applyDiscountAndCashback(client, newOrder);

        // Обновляем статус клиента (например, переход обычного клиента в премиум)
        updateClientStatus(client);

        // Генерируем платеж и создаем чек
        generatePaymentAndReceipt(client, newOrder);

        return true;
    }

    private Integer generateOrderId() {
        // Реализация генерации уникального ID для заказа (например, на основе текущего времени)
        return (int) (System.currentTimeMillis() % 100000);
    }

    private void applyDiscountAndCashback(Client client, Order order) {
        if (client instanceof PrimeClient) {
            // Применяем скидку для премиум-клиента
            order.setTotalPrice(order.getTotalPrice() * 0.95); // Скидка 5%
        } else if (client instanceof LoyalClient) {
            // Применяем скидку и кэшбэк для лояльного клиента
            order.setTotalPrice(order.getTotalPrice() * 0.9); // Скидка 10%
            Double cashbackAmount = order.getTotalPrice() * 0.1; // Кэшбэк 10%
            ((LoyalClient) client).setCashback(((LoyalClient) client).getCashback() + cashbackAmount);
        }
    }

    private void updateClientStatus(Client client) {
        if (client instanceof OrdinaryClient) {
            // Проверяем условие перехода обычного клиента в премиум
            // Например, если сделано более одного заказа одного и того же продукта сразу
            // Меняем статус клиента на PrimeClient
            client = new PrimeClient(client.getId(), client.getName(), client.getSurname(), 5); // Пример скидки для премиум-клиента
            updateClient(client);
        } else if (client instanceof PrimeClient && ((PrimeClient) client).getOrdersCount() >= 10) {
            // Проверяем условие перехода премиум-клиента в лояльного
            // Например, если сделано 10 заказов
            client = new LoyalClient(client.getId(), client.getName(), client.getSurname(), 5, 0.0); // Пример скидки и кэшбэка для лояльного клиента
            updateClient(client);
        }
    }

    private void generatePaymentAndReceipt(Client client, Order order) {
        Payment payment = new Payment(generatePaymentId(), client.getId(), order.getId(), new Date(), false, order.getTotalPrice());
        // Расчет и генерация платежа
        payment.calculatePayment(client); // Расчет платежа в соответствии с типом клиента
        // Создание чека в формате txt
        generateReceiptTxt(order);
    }

    private Integer generatePaymentId() {
        // Реализация генерации уникального ID для платежа (например, на основе текущего времени)
        return (int) (System.currentTimeMillis() % 100000);
    }

    private void generateReceiptTxt(Order order) {
        // Реализация создания чека в формате txt
        // Запись информации о заказе и платеже в текстовый файл receipt.txt
    }

}
