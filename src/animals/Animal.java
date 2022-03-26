package animals;

import diet.IDiet;
import food.EFoodType;
import food.IEdible;
import mobility.Mobile;
import mobility.Point;

public abstract class Animal extends Mobile implements IEdible {

    private String name;
    private double weight;
    private IDiet diet;


    public Animal(String name, Point point) {
        super();
        // TODO: ctor
    }

    public void makeSound() {
        //TODO: implement method
    }

    public boolean eat(IEdible edible) {
        // TODO: implement method
        return true;
    }

    /**
     * Gets the food type
     *
     * @return type of food from the Enum
     */
    @Override
    public EFoodType getFoodtype() {
        return null;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public abstract void setName(String name);
}
