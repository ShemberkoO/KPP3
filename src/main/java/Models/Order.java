package Models;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private int orderID;
    private Client client;
    private transient List<Dish> dishes; // не буде серіалізоване

    public Order(int orderID, Client client, List<Dish> dishes) {
        this.orderID = orderID;
        this.client = client;
        this.dishes = dishes;
    }

    public Order() {
    }


    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Client getClient() {
        return client;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
