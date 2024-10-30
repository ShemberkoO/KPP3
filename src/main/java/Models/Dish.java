package Models;

import java.io.Serializable;

public class Dish implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private double cost;
    private int weightInGrams;

    public Dish(int id, String name, double cost, int weightInGrams) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.weightInGrams = weightInGrams;
    }
    public Dish() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getWeightInGrams() {
        return weightInGrams;
    }
    public void setWeightInGrams(int weightInGrams) {
        this.weightInGrams = weightInGrams;
    }
}
