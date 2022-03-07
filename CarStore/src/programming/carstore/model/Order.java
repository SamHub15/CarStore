package programming.carstore.model;

public class Order {
    private int id;
    private int sellerId;       //  продавец
    private int customerId;     //  покупатель
    private int[] cars;

    public Order(int id, int sellerId, int customerId, int[] cars) {
        this.id = id;
        this.sellerId = sellerId;
        this.customerId = customerId;
        this.cars = cars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int[] getCars() {
        return cars;
    }

    public void setCars(int[] cars) {
        this.cars = cars;
    }
}
