package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;

/**
 * @author Gleb Tcivie
 * @Date 5/4/22
 */
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
     * @return Returns the new weight of the animal after eating
     */
    double eat(Animal animal, IEdible food);
}
