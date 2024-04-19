import java.util.ArrayList;
import Client.*;
import Product.Product;
import Product.OrderProduct;
import Product.Order;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new OnlineStoreGUI();
        });
    }
}
