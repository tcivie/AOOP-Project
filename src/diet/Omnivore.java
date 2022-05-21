package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public class Omnivore extends Diet implements IDiet {


    /**
     * Checks if the other food type can be eaten
     *
     * @param food the food we are trying to eat
     * @return True of false if the food can be eaten
     */
    @Override
    public boolean canEat(EFoodType food) {
        return food == EFoodType.MEAT || food == EFoodType.VEGETABLE;
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
        if (animal.getDiet().canEat(food.getFoodtype())) {
            if (food.getFoodtype() == EFoodType.MEAT)
                return Math.round(animal.getWeight() * 0.1 * 100) / 100.;
            return Math.round(animal.getWeight() * 0.07 * 100) / 100.;
        }
        return 0;
    }

}
