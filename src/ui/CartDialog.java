package ui;

import model.CartItem;
import model.CartManager;
import javax.swing.*;
import java.awt.*;

public class CartDialog extends JDialog {
    private final CartManager cartManager;
    private final DefaultListModel<CartItem> listModel = new DefaultListModel<>();
    private final JLabel totalLabel = new JLabel();

    public CartDialog(CartManager cartManager) {
        this.cartManager = cartManager;
        setTitle("Sepet");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JList<CartItem> itemList = new JList<>(listModel);
        itemList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value.getDescription() + " - " + value.getPrice() + "₺");
            label.setOpaque(true);
            if (isSelected) label.setBackground(Color.LIGHT_GRAY);
            return label;
        });

        JScrollPane scrollPane = new JScrollPane(itemList);
        add(scrollPane, BorderLayout.CENTER);

        JButton removeBtn = new JButton("Seçili Ürünü Çıkar");
        removeBtn.addActionListener(e -> {
            CartItem selected = itemList.getSelectedValue();
            if (selected != null) {
                cartManager.removeFromCart(selected);
                updateCart();
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(removeBtn, BorderLayout.WEST);
        bottomPanel.add(totalLabel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        JButton checkoutButton = new JButton("Siparişi Tamamla");
        checkoutButton.addActionListener(e -> {
            cartManager.checkout();
            updateCart();
            JOptionPane.showMessageDialog(this, "Sipariş oluşturuldu.");
        });
        bottomPanel.add(checkoutButton, BorderLayout.CENTER);

        updateCart();
        setModal(true);
        setVisible(true);
    }

    private void updateCart() {
        listModel.clear();
        for (CartItem item : cartManager.getCartItems()) {
            listModel.addElement(item);
        }
        totalLabel.setText("Toplam: " + cartManager.calculateTotal() + "₺");
    }
}
