import Client.Client;
import Delivery.DeliverySystem;
import Delivery.DirectDelivery;
import Delivery.TastamatDelivery;
import Product.Product;
import Product.OrderProduct;
import Product.Order;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextField;


public class OnlineStoreGUI extends JFrame {
    private ArrayList<Product> products;
    private ArrayList<Client> clients;
    private ArrayList<Order> orders;
    private JComboBox<DirectDelivery> deliveryTypeComboBox;
    private JComboBox<Product> productComboBox;
    private JComboBox<Client> clientComboBox;
    private JComboBox<Order> orderComboBox;
    private JTextField quantityField;
    private JButton addProductButton;
    private JButton deleteProductButton;
    private JButton updateProductButton;
    private JButton orderButton;
    private JButton calculatePaymentButton;
    private JButton organizeDeliveryButton;
    private JButton addClientButton;
    private JButton deleteClientButton;
    private JButton directDeliveryButton;
    private JButton tastamatDeliveryButton;

    public OnlineStoreGUI() {
        // Инициализация списков
        products = new ArrayList<>();
        clients = new ArrayList<>();
        orders = new ArrayList<>();

        // Создание компонентов GUI
        productComboBox = new JComboBox<>();
        clientComboBox = new JComboBox<>();
        orderComboBox = new JComboBox<>();
        deliveryTypeComboBox = new JComboBox<>();
        directDeliveryButton = new JButton("Прямая доставка");
        tastamatDeliveryButton = new JButton("Доставка через Tastamat");
        quantityField = new JTextField(10);
        addProductButton = new JButton("Добавить продукт");
        deleteProductButton = new JButton("Удалить продукт");
        updateProductButton = new JButton("Изменить продукт");
        orderButton = new JButton("Оформить заказ");
        calculatePaymentButton = new JButton("Рассчитать платеж");
        organizeDeliveryButton = new JButton("Организовать доставку");
        addClientButton = new JButton("Добавить клиента");
        deleteClientButton = new JButton("Удалить клиента");

        // Установка макета для основной панели
        JPanel mainPanel = new JPanel(new GridLayout(6, 2));
        mainPanel.add(new JLabel("Выберите продукт:"));
        mainPanel.add(productComboBox);
        mainPanel.add(new JLabel("Выберите клиента:"));
        mainPanel.add(clientComboBox);
        mainPanel.add(new JLabel("Количество:"));
        mainPanel.add(quantityField);
        mainPanel.add(new JLabel("Выберите тип доставки:"));
        mainPanel.add(directDeliveryButton);
        mainPanel.add(tastamatDeliveryButton);
        mainPanel.add(addProductButton);
        mainPanel.add(deleteProductButton);
        mainPanel.add(updateProductButton);
        mainPanel.add(orderButton);
        mainPanel.add(calculatePaymentButton);
        mainPanel.add(organizeDeliveryButton);
        mainPanel.add(addClientButton);
        mainPanel.add(deleteClientButton);

        // Добавление основной панели на форму
        add(mainPanel);

        // Настройка основных параметров окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setTitle("Online Store Management");

        // Добавление обработчиков событий для кнопок
        addProductButton.addActionListener(e -> addProduct());
        deleteProductButton.addActionListener(e -> deleteProduct());
        updateProductButton.addActionListener(e -> updateProduct());
        orderButton.addActionListener(e -> placeOrder());
        calculatePaymentButton.addActionListener(e -> calculatePayment());
        addClientButton.addActionListener(e -> addClient());
        deleteClientButton.addActionListener(e -> deleteClient());
        directDeliveryButton.addActionListener(e -> organizeDelivery("direct"));
        tastamatDeliveryButton.addActionListener(e -> organizeDelivery("tastamat"));

        // Загрузка начальных данных (если нужно)
        loadInitialData();
    }

//    private void setDeliveryType(String deliveryType) {
//        // Здесь можно добавить логику обработки выбранного типа доставки
//        JOptionPane.showMessageDialog(this, "Выбран тип доставки: " + deliveryType);
//        // Например, можно сохранить выбранный тип доставки в базу данных или использовать его для оформления заказа
//    }
    private void addProduct() {
        String name = JOptionPane.showInputDialog("Введите название продукта:");
        if (name != null && !name.isEmpty()) {
            double price = Double.parseDouble(JOptionPane.showInputDialog("Введите цену продукта:"));
            int stock = Integer.parseInt(JOptionPane.showInputDialog("Введите количество на складе:"));
            Product newProduct = new Product(products.size() + 1, name, price, stock);
            products.add(newProduct);
            updateProductComboBox(); // Обновить список продуктов в выпадающем списке
            JOptionPane.showMessageDialog(this, "Продукт успешно добавлен!");
        } else {
            JOptionPane.showMessageDialog(this, "Пожалуйста, введите корректное название продукта.");
        }
    }


    private void deleteProduct() {
        int selectedIndex = productComboBox.getSelectedIndex();
        if (selectedIndex != -1) {
            products.remove(selectedIndex);
            updateProductComboBox(); // Обновить список продуктов в выпадающем списке
            JOptionPane.showMessageDialog(this, "Продукт успешно удален!");
        } else {
            JOptionPane.showMessageDialog(this, "Пожалуйста, выберите продукт для удаления.");
        }
    }

    private void updateProduct() {
        int selectedIndex = productComboBox.getSelectedIndex();
        if (selectedIndex != -1) {
            Product selectedProduct = products.get(selectedIndex);
            String name = JOptionPane.showInputDialog("Введите новое название продукта:", selectedProduct.getName());
            if (name != null && !name.isEmpty()) {
                double price = Double.parseDouble(JOptionPane.showInputDialog("Введите новую цену продукта:", selectedProduct.getPrice()));
                int stock = Integer.parseInt(JOptionPane.showInputDialog("Введите новое количество на складе:", selectedProduct.getStock()));
                selectedProduct.setName(name);
                selectedProduct.setPrice(price);
                selectedProduct.setStock(stock);
                updateProductComboBox(); // Обновить список продуктов в выпадающем списке
                JOptionPane.showMessageDialog(this, "Продукт успешно обновлен!");
            } else {
                JOptionPane.showMessageDialog(this, "Пожалуйста, введите корректные данные для обновления продукта.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Пожалуйста, выберите продукт для обновления.");
        }
    }

    private void placeOrder() {
        int productIndex = productComboBox.getSelectedIndex();
        int clientIndex = clientComboBox.getSelectedIndex();
        int quantity = Integer.parseInt(quantityField.getText());
        if (productIndex != -1 && clientIndex != -1 && quantity > 0) {
            Product selectedProduct = products.get(productIndex);
            Client selectedClient = clients.get(clientIndex);
            Order newOrder = new Order(orders.size() + 1, selectedClient.getId(),
                    new OrderProduct(selectedProduct.getId(), selectedProduct.getPrice(), quantity));
            orders.add(newOrder);
            JOptionPane.showMessageDialog(this, "Заказ успешно оформлен!");
        } else {
            JOptionPane.showMessageDialog(this, "Пожалуйста, выберите продукт и клиента, а также укажите количество.");
        }
    }

    private void calculatePayment() {
        int selectedIndex = clientComboBox.getSelectedIndex();
        if (selectedIndex != -1) {
            Client selectedClient = clients.get(selectedIndex);
            double totalPayment = 0;

            // Считаем общую сумму заказов выбранного клиента
            for (Order order : orders) {
                if (order.getClientId() == selectedClient.getId()) {
                    totalPayment += order.getTotalPrice();
                }
            }

            JOptionPane.showMessageDialog(this, "Общая сумма платежа для клиента " + selectedClient.getName() + ": " + totalPayment);
        } else {
            JOptionPane.showMessageDialog(this, "Пожалуйста, выберите клиента для расчета платежа.");
        }
    }

    private void organizeDelivery(String deliveryType) {
        if (deliveryType.equals("direct")) {
            // Логика для прямой доставки (например, создание объекта DirectDelivery и организация доставки)
            DirectDelivery directDelivery = new DirectDelivery();
            directDelivery.processDelivery(); // Пример вызова метода для прямой доставки
        } else if (deliveryType.equals("tastamat")) {
            // Логика для доставки через Tastamat (например, создание объекта TastamatDelivery и организация доставки)
            TastamatDelivery tastamatDelivery = new TastamatDelivery();
            tastamatDelivery.processDelivery(); // Пример вызова метода для доставки через Tastamat
        }
    }


    private void addClient() {
        String name = JOptionPane.showInputDialog("Введите имя клиента:");
        String surname = JOptionPane.showInputDialog("Введите фамилию клиента:");
        if (name != null && !name.isEmpty() && surname != null && !surname.isEmpty()) {
            Client newClient = new Client(clients.size() + 1, name, surname);
            clients.add(newClient);
            updateClientComboBox(); // Обновить список клиентов в выпадающем списке
            JOptionPane.showMessageDialog(this, "Клиент успешно добавлен!");
        } else {
            JOptionPane.showMessageDialog(this, "Пожалуйста, введите корректные данные клиента.");
        }
    }

    private void deleteClient() {
        int selectedIndex = clientComboBox.getSelectedIndex();
        if (selectedIndex != -1) {
            clients.remove(selectedIndex);
            updateClientComboBox(); // Обновить список клиентов в выпадающем списке
            JOptionPane.showMessageDialog(this, "Клиент успешно удален!");
        } else {
            JOptionPane.showMessageDialog(this, "Пожалуйста, выберите клиента для удаления.");
        }
    }

    private void updateClientComboBox() {
        clientComboBox.removeAllItems();
        for (Client client : clients) {
            clientComboBox.addItem(client);
        }
    }

    private void updateProductComboBox() {
        productComboBox.removeAllItems();
        for (Product product : products) {
            productComboBox.addItem(product);
        }
    }

    private void loadInitialData() {
        // Логика загрузки начальных данных (если нужно)
        // Например, загружаем списки продуктов, клиентов и заказов из базы данных
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OnlineStoreGUI storeGUI = new OnlineStoreGUI();
            storeGUI.setVisible(true);
        });
    }
}
