package model;

import java.util.List;

public interface CartObserver {
    void onCartCheckout(List<CartItem> items);
}
