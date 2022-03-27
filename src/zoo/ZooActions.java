package zoo;

import animals.Animal;
import food.EFoodType;
import food.IEdible;
import mobility.Point;

public abstract class ZooActions{

    public static boolean eat(Object animal, IEdible food) {
        return ((Animal)animal).eat(food);
    }
}
