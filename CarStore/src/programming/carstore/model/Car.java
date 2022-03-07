package programming.carstore.model;

public class Car {

    private int id;
    private String model;
    private double price;
    private Colors colors;

    public Car(int id, String model, double price, Colors colors) {
        this.id = id;
        this.model = model;
        this.price = price;
        this.colors = colors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Colors getColors() {
        return colors;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }
}
