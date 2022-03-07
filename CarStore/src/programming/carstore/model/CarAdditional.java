package programming.carstore.model;

public class CarAdditional {
    private Colors colors;
    private int count;

    public CarAdditional(Colors colors, int count) {
        this.colors = colors;
        this.count = count;
    }

    public Colors getColors() {
        return colors;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
