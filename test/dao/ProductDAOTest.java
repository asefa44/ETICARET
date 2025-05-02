package dao;

import model.Product;
import org.junit.jupiter.api.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductDAOTest {

    ProductDAO dao;

    @BeforeEach
    void setUp() {
        dao = new ProductDAO(); // Burada veritabanı bağlantısı yapılır
    }

    @Test
    void testUrunListeleme() {
        List<Product> urunler = dao.getAllProducts();
        assertNotNull(urunler);
        assertTrue(urunler.size() > 0); // En az bir ürün varsa başarılı
    }

    @Test
    void testUrunDetaylari() {
        Product p = dao.getProductById(6); // ID 1 olan ürün varsa
        assertNotNull(p);
        assertEquals("Laptop", p.getName()); // Örnek veri
    }

    @Test
    void testStokKontrolu() {
        Product p = dao.getProductById(6);
        assertTrue(p.getStock() >= 0); // Negatif stok olamaz
    }
}
