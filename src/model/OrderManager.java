package model;

import java.util.ArrayList;
import java.util.List;

public class OrderManager implements CartObserver {
    private final List<List<String>> orders = new ArrayList<>();

    @Override
    public void onCartCheckout(List<CartItem> items) {
        List<String> order = new ArrayList<>();
        for (CartItem item : items) {
            order.add(item.getDescription());
        }
        orders.add(order);
    }

    public List<List<String>> getOrders() {
        return orders;
    }
}
