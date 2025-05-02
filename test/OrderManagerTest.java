import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderManagerTest {

    private CartManager cartManager;
    private OrderManager orderManager;

    @BeforeEach
    void setUp() {
        // Her testten önce yeni bir CartManager ve OrderManager oluşturuluyor
        cartManager = new CartManager();
        orderManager = new OrderManager();
        cartManager.addObserver(orderManager);  // Observer ilişkisi kuruluyor
    }

    @Test
    void testCheckoutAddsOrder() {
        // ✅ Bu test, sepete ürün eklendikten sonra checkout çağrıldığında
        // OrderManager'ın siparişi doğru şekilde eklediğini test eder.

        Product product = new Product.ProductBuilder()
                .setId(1)
                .setName("Test Ürünü")
                .setDescription("Açıklama")
                .setPrice(100.0)
                .setStock(10)
                .build();

        CartItem item = new ProductCartItem(product);
        cartManager.addToCart(item);

        cartManager.checkout();  // Siparişi tamamla

        List<List<String>> orders = orderManager.getOrders();

        // Sipariş sayısı 1 olmalı
        assertEquals(1, orders.size(), "Bir sipariş olmalı");

        // Sipariş içeriği doğru mu
        assertTrue(orders.get(0).get(0).contains("Test Ürünü"), "Sipariş içeriği doğru olmalı");
    }

    @Test
    void testMultipleItemsInOrder() {
        // ✅ Bu test, sepete birden fazla ürün (biri hediye paketli) eklendiğinde
        // OrderManager'ın her iki ürünü de sipariş olarak kaydettiğini doğrular.

        Product p1 = new Product.ProductBuilder().setId(1).setName("Ürün 1").setDescription("A").setPrice(10).setStock(5).build();
        Product p2 = new Product.ProductBuilder().setId(2).setName("Ürün 2").setDescription("B").setPrice(20).setStock(5).build();

        cartManager.addToCart(new ProductCartItem(p1));
        cartManager.addToCart(new GiftWrapDecorator(new ProductCartItem(p2)));  // Hediye paketi eklenmiş ürün

        cartManager.checkout();

        List<List<String>> orders = orderManager.getOrders();

        // Sadece 1 sipariş olmalı ama içinde 2 ürün yer almalı
        assertEquals(1, orders.size(), "1 sipariş kaydedilmeli");
        assertEquals(2, orders.get(0).size(), "Siparişte 2 ürün olmalı");
    }
}
