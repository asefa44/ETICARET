package model;

public class GiftWrapDecorator extends CartItemDecorator {
    public GiftWrapDecorator(CartItem item) {
        super(item);  // Yani, temel ürünü alıyoruz
    }

    @Override
    public String getDescription() {
        return decoratedItem.getDescription() + " + Hediye Paketi"; // Ürüne hediye paketi açıklaması ekliyoruz
    }

    @Override
    public double getPrice() {
        return decoratedItem.getPrice() + 10.0;  // Fiyata sadece 10 TL ekliyoruz
    }

    @Override
    public int getQuantity() {
        return decoratedItem.getQuantity();  // Miktar değişmiyor, aynı kalıyor
    }
}

