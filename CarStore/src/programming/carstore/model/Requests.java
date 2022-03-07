package programming.carstore.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Requests {

    private ListInitBase initBase = new ListInitBase();

    public void countCars(){
        initBase.initDate();
        String carsInfo = String.format("Общее количество проданных автомобилей %d на сумму %f",
                initBase.getCountOfSoldCars(),initBase.getAllPriceOfSoldCars());
        System.out.println(carsInfo);
    }

    public void allCountSeller() {
        initBase.initDate();
        for (Seller seller: initBase.sellers) {
            System.out.println(seller.getName() + " продал(а) " + initBase.getProfitBySeller(seller.getId()));
        }
    }

    public void allCountSellerColor() {
        initBase.initDate();
        ArrayList<CarAdditional> soldCarsCount = initBase.getCountOfSoldCarsByColor();
        HashMap<Colors, Double> soldCarsPrice = initBase.getPriceSoldCarColors();

        String soldCarsStr = "%d автомобиля(ей) продано %s цвета общей стоимостью %f";

        for (CarAdditional carAdditional: soldCarsCount) {
            double price = soldCarsPrice.get(carAdditional.getColors());
            System.out.println(String.format(soldCarsStr, carAdditional.getCount(),
                    carAdditional.getColors(), price));
        }
    }

    public void popularColorYounger30Age() {
        int age = 30;
        String colorMax = "Покупатели  младше %d лет выбирают %s цвет";
        System.out.println(String.format(colorMax, age, initBase.getPopularColorsMax(age)));
    }

    public void popularColorOlder30Age() {
        int age = 30;
        String colorMin = "Покупатели  старше %d лет выбирают %s цвет";
        System.out.println(String.format(colorMin, age, initBase.getPopularColorsMin(age)));
    }

    public void printMenu() {
        System.out.println("Добро пожаловать в автомагазин");
        System.out.println("Выберете действие которое необходимо выполнить");
        System.out.println("-----------------------------------------------");
        System.out.println("1) Посмотреть количество проданных автмобилей");
        System.out.println("2) Посмотреть общее количество проданных автомобилей по всем продавцам");
        System.out.println("3) Посмотреть количество и общую стоимость проданных автомобилей по цвету");
        System.out.println("4) Посмотреть популярный цвет у покупателей младше 30 лет");
        System.out.println("5) Посмотреть популярный цвет у покупателей старше 30 лет");
        System.out.println("6) Выход");
    }
}
