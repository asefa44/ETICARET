package model;

import ui.CartDialog;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private final List<CartItem> cartItems = new ArrayList<>();
    private final List<CartObserver> observers = new ArrayList<>();

    public void addToCart(CartItem item) {
        cartItems.add(item);
    }

    public void removeFromCart(CartItem item) {
        cartItems.remove(item);
    }

    public void clearCart() {
        cartItems.clear();
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public double calculateTotal() {
        return cartItems.stream().mapToDouble(CartItem::getPrice).sum();
    }

    public void addObserver(CartObserver observer) {
        observers.add(observer);
    }

    public void checkout() {
        if(!isCartEmpty()){
            for (CartObserver observer : observers) {
                observer.onCartCheckout(new ArrayList<>(cartItems));
            }
            clearCart();
        }
    }

    // Sepetin boş olup olmadığını kontrol eden metot
    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }
}
