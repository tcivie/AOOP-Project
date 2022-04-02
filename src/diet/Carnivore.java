package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;

import java.util.InputMismatchException;

public class Carnivore extends Diet implements IDiet {
    /**
     * Checks if the other food type can be eaten
     *
     * @param food the food we are trying to eat
     * @return True of false if the food can be eaten
     */
    @Override
    public boolean canEat(EFoodType food) {
        if (food == EFoodType.MEAT)
            return true;
        return false;
    }

    /**
     * Method of eating the selected food
     * In case of wrong input the method will throw exception InputMismatchException
     *
     * @param animal the animal to feed
     * @param food   what to feed the animal with
     * @return the new weight of the animal
     */
    @Override
    public double eat(Animal animal, IEdible food) {
        if (animal.getDiet().canEat(food.getFoodtype()))
            return Math.round(animal.getWeight() * 1.1 * 100) / 100.;
        return animal.getWeight();
    }

}
