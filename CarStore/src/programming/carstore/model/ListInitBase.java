package programming.carstore.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ListInitBase {

    public ArrayList<Car> cars = new ArrayList<>();
    public ArrayList<Customer> customers = new ArrayList<>();
    public ArrayList<Seller> sellers = new ArrayList<>();
    public ArrayList<Order> orders = new ArrayList<>();

    /**
     * Получить наиболее попудярный цвет автомобиля младше возраста @age
     * @param age   Требуемый возраст
     * @return      Популярный цвет
     */
    public Colors getPopularColorsMax(int age) {
        ArrayList<Integer> customerAge = new ArrayList<>();

        for (Customer customer: customers) {
            if (customer.getAge() > age) {
                customerAge.add(customer.getId());
            }
        }

        return getColors(customerAge);
    }

    /**
     * Получить наиболее попудярный цвет автомобиля старше возраста @age
     * @param age   Требуемый возраст
     * @return      Популярный цвет
     */
    public Colors getPopularColorsMin(int age) {
        ArrayList<Integer> customerAge = new ArrayList<>();

        for (Customer customer: customers) {
            if (customer.getAge() < age) {
                customerAge.add(customer.getId());
            }
        }

        return getColors(customerAge);
    }

    private Colors getColors(ArrayList<Integer> customerAge) {
        int countRed=0, countBlue=0, countBlack=0;

        for (Order order: orders) {
            if (customerAge.contains(order.getCustomerId())){
                countRed+= getCountOfSoldCarsColor(order, Colors.RED);
                countBlue+= getCountOfSoldCarsColor(order, Colors.BLUE);
                countBlack+= getCountOfSoldCarsColor(order, Colors.BLACK);
            }
        }

        ArrayList<CarAdditional> result = new ArrayList<>();
        result.add(new CarAdditional(Colors.RED, countRed));
        result.add(new CarAdditional(Colors.BLUE, countBlue));
        result.add(new CarAdditional(Colors.BLACK, countBlack));

        result.sort(new Comparator<CarAdditional>() {
            @Override
            public int compare(CarAdditional o1, CarAdditional o2) {
                return o2.getCount() - o1.getCount();
            }
        });

        return result.get(0).getColors();
    }

    // Получить количество проданных автомобилей по цвету
    public ArrayList<CarAdditional> getCountOfSoldCarsByColor() {
        ArrayList<CarAdditional> result = new ArrayList<>();
        int countRed=0, countBlue=0, countBlack=0;

        for (Order order: orders) {
            countRed+=getCountOfSoldCarsColor(order, Colors.RED);
            countBlue+=getCountOfSoldCarsColor(order, Colors.BLUE);
            countBlack+=getCountOfSoldCarsColor(order, Colors.BLACK);
        }

        result.add(new CarAdditional(Colors.RED, countRed));
        result.add(new CarAdditional(Colors.BLUE, countBlue));
        result.add(new CarAdditional(Colors.BLACK, countBlack));

        return result;
    }

    /**
     * Получить общее количество автомобилей по определенному цвету в одном заказе
     * @param order     Заказ
     * @param colors    Цвет
     * @return          Общая стоимость
     */
    public int getCountOfSoldCarsColor(Order order, Colors colors) {
        int count = 0;

        for (int carId: order.getCars()) {
            Car car = getCarId(carId);
            if (car != null && car.getColors()==colors)
                count ++;
        }
        return count;
    }


    // Получить общую стоимость проданных автомобилей по цвету
    public HashMap<Colors, Double> getPriceSoldCarColors() {
        HashMap<Colors, Double> result = new HashMap<>();

        double priceRed = 0, priceBlue = 0, priceBlack = 0;

        for (Order order: orders) {
            priceRed += getPriceOfSoldCarsColor(order, Colors.RED);
            priceBlue += getPriceOfSoldCarsColor(order, Colors.BLUE);
            priceBlack += getPriceOfSoldCarsColor(order, Colors.BLACK);
        }

        result.put(Colors.RED, priceRed);
        result.put(Colors.BLUE, priceBlue);
        result.put(Colors.BLACK, priceBlack);

        return result;
    }

    /**
     * Получить общую стоимость автомобилей по определенному цвету в одном заказе
     * @param order     Заказ
     * @param colors    Цвет
     * @return          Общая стоимость
     */
    public double getPriceOfSoldCarsColor(Order order, Colors colors) {
        double price = 0;

        for (int carId: order.getCars()) {
            Car car = getCarId(carId);
            if (car != null && car.getColors()==colors)
                price += car.getPrice();
        }
        return price;
    }

    /**
     * Получить общее количество и общую стоимость проданного товара
     * для продавца
     * @param sellerId  Уникальный номер продавца
     * @return          Количество и общая стоимость для продавца
     */
    public Profit getProfitBySeller(int sellerId) {
        int count = 0; double price = 0;

        for (Order order: orders)
            if (order.getSellerId()==sellerId){
                count += order.getCars().length;
                price += getPriceOfSoldCarsInOrder(order);
            }
        return new Profit(count, price);
    }

    // Получить общую стоимость всех заказов
    public double getAllPriceOfSoldCars() {
        double price = 0;

        for (Order order: orders) {
            price += getPriceOfSoldCarsInOrder(order);
        }
        return price;
    }

    /**
     * Получить общую стоимость одного заказа
     * @param order     Заказ по которому считается стоимость
     * @return          Общая стоимость автомобилей для одного заказа
     */
    public double getPriceOfSoldCarsInOrder(Order order) {
        double price = 0;

        for (int carId: order.getCars()) {
            Car car = getCarId(carId);
            if (car != null)
                price += car.getPrice();
        }
        return price;
    }

    // Получить общее колличество проданных автомобилей
    public int getCountOfSoldCars() {
        int count = 0;

        for (Order order: orders)
            count += order.getCars().length;

        return count;
    }

    /**
     * Получение автомомбиля по ее уникальному номеру
     * @param id    Уникальный номер автомобиля
     * @return      Найденый автомобиль
     */
    public Car getCarId(int id) {
        Car current = null;

        for (Car car: cars) {
            if (car.getId()==id){
                current = car;
                break;
            }
        }

        return current;
    }

    public void initDate() {

        // Продавцы
        sellers.add(new Seller(1, "Иванов Иван", 32));
        sellers.add(new Seller(2, "Петров Петр", 30));
        sellers.add(new Seller(3, "Васильева Алиса", 26));

        // Покупатели
        customers.add(new Customer(1, "Сидоров Алексей", 25));
        customers.add(new Customer(2, "Романов Дмитрий", 32));
        customers.add(new Customer(3, "Симонов Кирилл", 19));
        customers.add(new Customer(4, "Кириенко Данил", 45));
        customers.add(new Customer(5, "Воротынцева Алина", 23));

        // Автомобили
        cars.add(new Car(1, "Mercedes", 35000, Colors.RED));
        cars.add(new Car(2, "Toyota", 30000, Colors.RED));
        cars.add(new Car(3, "BMW", 45000, Colors.RED));

        cars.add(new Car(4, "Suzuki", 20000, Colors.BLUE));
        cars.add(new Car(5, "Honda", 30000, Colors.BLUE));
        cars.add(new Car(6, "Chevrolet", 28000, Colors.BLUE));

        cars.add(new Car(7, "Ford", 27000, Colors.BLACK));
        cars.add(new Car(8, "Renault", 24000, Colors.BLACK));
        cars.add(new Car(9, "Opel", 21000, Colors.BLACK));

        orders.add(new Order(1, 1, 1, new int[]{8}));
        orders.add(new Order(2, 1, 2, new int[]{4}));
        orders.add(new Order(3, 2, 3, new int[]{6}));
        orders.add(new Order(4, 2, 4, new int[]{7}));
        orders.add(new Order(5, 3, 5, new int[]{3}));
    }
}
