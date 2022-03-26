package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;

public interface IDiet {
    /**
     * Checks if the other food type can be eaten
     * @param food the food we are trying to eat
     * @return True of false if the food can be eaten
     */
    boolean canEat(EFoodType food);

    /**
     * Method of eating the selected food
     * @param animal the animal to feed
     * @param food what to feed the animal with
     * @return TODO: find out what is returned here
     */
    double eat(Animal animal, IEdible food);
}
