package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;

public class Omnivore implements IDiet {
    /**
     * Checks if the other food type can be eaten
     *
     * @param food the food we are trying to eat
     * @return True of false if the food can be eaten
     */
    @Override
    public boolean canEat(EFoodType food) {
        if (food == EFoodType.MEAT || food == EFoodType.VEGETABLE)
            return true;
        return false;
    }

    /**
     * Method of eating the selected food
     *
     * @param animal the animal to feed
     * @param food   what to feed the animal with
     * @return the new weight of the animal after eating it
     */
    @Override
    public double eat(Animal animal, IEdible food) {
        return 0; //TODO: add implementation
    }
}
