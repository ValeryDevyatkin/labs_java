package contr;

import java.util.ArrayList;
import java.util.Arrays;
import model.Car;

public class CarManager {

    private static final ArrayList<Car> carList = new ArrayList<Car>(Arrays.asList(
            new Car("Model1", 13),
            new Car("Model2", 12),
            new Car("Model3", 12),
            new Car("Model4", 11),
            new Car("Model5", 10)
    ));

    public ArrayList<Car> getCarList() {
        return carList;
    }
}
