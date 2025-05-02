package ui;

import javax.swing.*;
import java.awt.*;
import model.CartManager;

public class AdminPanel extends JFrame {
    public AdminPanel() {
        setTitle("Admin Paneli");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // CartManager'ı oluşturuyoruz
        CartManager cartManager = new CartManager();

        // ProductTablePanel'e role ve cartManager parametrelerini geçiyoruz
        mainPanel.add(new ProductTablePanel("admin", cartManager), BorderLayout.CENTER);

        JButton exitButton = new JButton("Çıkış Yap");
        exitButton.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });
        mainPanel.add(exitButton, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}
