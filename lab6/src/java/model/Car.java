package model;

public class Car {

    private static int static_id = 0;
    private int id;
    private String model;
    private int price;

    public Car() {
        static_id++;
    }
    
    public Car(String model, int price) {
        static_id++;
        this.id = static_id;
        this.model = model;
        this.price = price;
    }
    
    public int getId() {
        return id;
    }
    
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
