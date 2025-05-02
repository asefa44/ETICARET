package model;

public abstract class CartItemDecorator implements CartItem {
    protected CartItem decoratedItem;  // Diğer CartItem'ı tutan değişken

    public CartItemDecorator(CartItem item) {
        this.decoratedItem = item;
    }

    @Override
    public String getDescription() {
        return decoratedItem.getDescription();  // Dekoratörden gelen açıklama
    }

    @Override
    public double getPrice() {
        return decoratedItem.getPrice();  // Dekoratörden gelen fiyat
    }

    @Override
    public int getQuantity() {
        return decoratedItem.getQuantity();  // Dekoratörden gelen miktar
    }
}

