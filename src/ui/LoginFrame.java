package ui;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame
{
    public LoginFrame()
    {
        setTitle("Giriş");
        setSize(400, 300); // Boyut büyütüldü
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

        JLabel label = new JLabel("Kullanıcı rolünü seç:", SwingConstants.CENTER);
        panel.add(label);

        String[] roles = {"Müşteri", "Satıcı", "Admin"};
        JComboBox<String> roleBox = new JComboBox<>(roles);
        panel.add(roleBox);

        JButton loginBtn = new JButton("Giriş Yap");
        panel.add(loginBtn);

        loginBtn.addActionListener(e ->
        {
            String selectedRole = (String) roleBox.getSelectedItem();
            dispose();
            switch (selectedRole)
            {
                case "Müşteri":
                    new CustomerPanel();
                    break;
                case "Satıcı":
                    new SellerPanel();
                    break;
                case "Admin":
                    new AdminPanel();
                    break;
            }
        });

        add(panel);
        setVisible(true);
    }
}
