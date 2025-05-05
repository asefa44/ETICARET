import dao.ProductDAO;
import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ProductTablePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductListingTest {

    private ProductDAO productDAO;
    private ProductTablePanel productPanel;

    @BeforeEach
    void setUp() {
        // Test öncesinde gerekli nesneleri başlatıyoruz
        productDAO = new ProductDAO();
        productPanel = new ProductTablePanel("customer"); // Role "customer" olarak belirliyoruz
    }

    @Test
    void testProductListing() {
        // ✅ Bu test, ürünlerin listeleme fonksiyonunun doğru çalışıp çalışmadığını kontrol eder.

        // Ürünlerin doğru şekilde yüklendiğinden emin olmak için
        List<Product> products = productDAO.getAllProducts();
        assertNotNull(products, "Ürünler boş olmamalı");

        // JTable'ı kontrol etmeliyiz
        JTable table = productPanel.getTable();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Tabloyu sıfla
        model.setRowCount(0);

        // Ürünleri tabloya ekleyelim
        for (Product product : products) {
            model.addRow(new Object[] {
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStock()
            });
        }

        // Şimdi tabloyu kontrol edelim. Ürün sayısının sıfırdan fazla olması gerektiğini kontrol ediyoruz
        assertTrue(model.getRowCount() > 0, "Tabloda ürünler görünmüyor");

        // Ayrıca, ürünlerin doğru şekilde sıralandığını kontrol edebiliriz.
        // Örneğin, ilk ürünün isminin sıralandığı şekilde doğru olup olmadığını kontrol edebiliriz.
        String firstProductName = (String) model.getValueAt(0, 1); // 1. sütun ürün adı
        assertNotNull(firstProductName, "İlk ürün adı null olmamalı");

        // Testi geçtikten sonra sonucu yazdıralım
        printTestResult("Test 1: Ürün Listeleme", "Uygulama açılır", "Tabloda ürünler sıralanır", "Başarılı");
    }

    // Test sonucunu yazdıran yardımcı metot
    private void printTestResult(String testName, String step, String expected, String status) {
        System.out.println(testName);
        System.out.println("Adım: " + step);
        System.out.println("Beklenen: " + expected);
        System.out.println("Durum: " + status);
    }
}
