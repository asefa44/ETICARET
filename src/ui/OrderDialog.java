package ui;

import model.OrderManager;

import javax.swing.*;
import java.awt.*;

public class OrderDialog extends JDialog {
    public OrderDialog(OrderManager orderManager) {
        setTitle("Siparişlerim");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();

        // Siparişleri alt alta ekleyelim
        for (var order : orderManager.getOrders()) {
            // Eğer order bir liste ise, her bir öğeyi alt alta ekleyebiliriz.
            // Örnek: order -> [sipariş1, sipariş2] gibi bir yapı olduğunu varsayalım.
            // Her bir öğeyi tek tek alt alta ekliyoruz.
            listModel.addElement("Sipariş: ");
            for (String item : order) {
                listModel.addElement(" - " + item);  // Her öğeyi alt alta yazıyoruz
            }
        }

        // JList içinde her bir öğe alt alta listelenecek
        JList<String> orderList = new JList<>(listModel);
        add(new JScrollPane(orderList), BorderLayout.CENTER);

        setModal(true);
        setVisible(true);
    }
}
