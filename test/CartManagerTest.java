import model.CartItem;
import model.CartManager;
import model.Product;
import model.ProductCartItem;
import model.GiftWrapDecorator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartManagerTest {

    private CartManager cartManager;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        cartManager = new CartManager();
        // Ürünleri test için oluşturuyoruz
        product1 = new Product.ProductBuilder()
                .setId(1)
                .setName("Ürün 1")
                .setDescription("Açıklama 1")
                .setPrice(100.0)
                .setStock(10)
                .build();

        product2 = new Product.ProductBuilder()
                .setId(2)
                .setName("Ürün 2")
                .setDescription("Açıklama 2")
                .setPrice(150.0)
                .setStock(5)
                .build();
    }

    @Test
    void testAddProductToCart() {
        // Sepete ürün ekleme testi
        CartItem item = new ProductCartItem(product1);
        cartManager.addToCart(item);

        // Sepette sadece bir ürün olmalı
        assertEquals(1, cartManager.getCartItems().size(), "Sepette bir ürün olmalı");
        assertTrue(cartManager.getCartItems().contains(item), "Sepette ürün bulunmuyor");
    }

    @Test
    void testRemoveProductFromCart() {
        // Ürün ekle
        CartItem item = new ProductCartItem(product1);
        cartManager.addToCart(item);

        // Ürün çıkarma işlemi
        cartManager.removeFromCart(item);

        // Sepet boş olmalı
        assertEquals(0, cartManager.getCartItems().size(), "Sepet boş olmalı");
    }

    @Test
    void testCalculateTotalWithoutGiftWrap() {
        // Ürünleri sepete ekle
        CartItem item1 = new ProductCartItem(product1);
        CartItem item2 = new ProductCartItem(product2);
        cartManager.addToCart(item1);
        cartManager.addToCart(item2);

        // Toplam fiyatı kontrol et (100 + 150)
        double expectedTotal = 100.0 + 150.0;
        assertEquals(expectedTotal, cartManager.calculateTotal(), "Toplam fiyat yanlış hesaplandı");
    }


    @Test
    void testEmptyCart() {
        // Sepet boş olmalı
        assertTrue(cartManager.getCartItems().isEmpty(), "Sepet boş değil");
    }

    @Test
    void testCartWithMultipleItems() {
        // Ürünleri sepete ekle
        CartItem item1 = new ProductCartItem(product1);
        CartItem item2 = new ProductCartItem(product2);
        cartManager.addToCart(item1);
        cartManager.addToCart(item2);

        // Sepette 2 ürün olmalı
        assertEquals(2, cartManager.getCartItems().size(), "Sepette iki ürün olmalı");
    }
}
