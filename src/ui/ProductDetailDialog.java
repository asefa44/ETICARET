package ui;

import javax.swing.*;
import java.awt.*;
import model.Product;


public class ProductDetailDialog extends JDialog
{
    public ProductDetailDialog(Product product)
    {
        setTitle("Ürün Detayı");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        add(new JLabel("ID: " + product.getId()));
        add(new JLabel("Ad: " + product.getName()));
        add(new JLabel("Açıklama: " + product.getDescription()));
        add(new JLabel("Fiyat: " + product.getPrice() + "₺"));

        JLabel stockLabel = new JLabel("Stok: " +
                (product.getStock() > 0 ? "Var (" + product.getStock() + " adet)" : "Tükendi"));
        stockLabel.setForeground(product.getStock() > 0 ? Color.GREEN : Color.RED);
        add(stockLabel);

        setModal(true);
        setVisible(true);
    }
}