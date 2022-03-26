package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;
// TODO: create class animal that when eats meat gets weight increased by 10% and wehn eating plants increases by 7%
public class Herbivore implements IDiet{
    /**
     * Checks if the other food type can be eaten
     *
     * @param food the food we are trying to eat
     * @return True of false if the food can be eaten
     */
    @Override
    public boolean canEat(EFoodType food) {
        return false; //TODO: add implementation
    }

    /**
     * Method of eating the selected food
     *
     * @param animal the animal to feed
     * @param food   what to feed the animal with
     * @return TODO: find out what is returned here
     */
    @Override
    public double eat(Animal animal, IEdible food) {
        return 0; //TODO: add implementation
    }
}
