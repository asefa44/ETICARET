package model;

public class ProductCartItem implements CartItem {
    private final Product product;
    private final int quantity; // Miktar bilgisini ekliyoruz

    public ProductCartItem(Product product) {
        this.product = product;
        this.quantity = 1; // Varsayılan miktar 1
    }

    // Fiyatı döndüren metot
    @Override
    public double getPrice() {
        return product.getPrice();
    }

    // Ürün açıklamasını döndüren metot
    @Override
    public String getDescription() {
        return product.getName();
    }

    // Miktarı döndüren metot
    @Override
    public int getQuantity() {
        return quantity;  // Ürünün miktarını döndür
    }

    public Product getProduct() {
        return product;
    }
}
