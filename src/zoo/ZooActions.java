package zoo;

import animals.Animal;
import food.EFoodType;
import food.IEdible;
import mobility.Point;

public abstract class ZooActions{

    /**
     * Eating method for animal and its food
     * @param animal The animal that should eat
     * @param food The food that the animal will try to eat
     * @return True if the animal ate the food / False otherwise
     */
    public static boolean eat(Object animal, IEdible food) {
        Animal ani = (Animal) animal;
        boolean isSuccess = ani.eat(food);
        if (isSuccess) {
            ani.makeSound();
        }
        return isSuccess;
    }

    /**
     * Method that will move the animal from one place to another
     * @param animal The animal that we need to move
     * @param point To where we want to move the animal
     * @return True if the animal has moved / False otherwise
     */
    public static boolean move(Object animal, Point point) {
        return ((Animal) animal).move(point) > 0;
    }


}
