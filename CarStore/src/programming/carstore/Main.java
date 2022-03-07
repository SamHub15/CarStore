package programming.carstore;

import programming.carstore.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Requests init = new Requests();
        init.printMenu();
        Scanner value = new Scanner(System.in);
        int in = value.nextInt();
        switch (in) {
            case 1:
                init.countCars();
                break;

            case 2:
                init.allCountSeller();
                break;

            case 3:
                init.allCountSellerColor();
                break;

            case 4:
                init.popularColorYounger30Age();
                break;

            case 5:
                init.popularColorOlder30Age();
                break;
        }

    }
}
