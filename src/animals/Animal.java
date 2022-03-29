package animals;

import diet.IDiet;
import food.EFoodType;
import food.IEdible;
import mobility.Mobile;
import mobility.Point;
import utilities.MessageUtility;

public abstract class Animal extends Mobile implements IEdible {

    private String name;
    private double weight;
    private IDiet diet;

    public Animal(String name, Point point) {
        super(point);
        setName(name);
        MessageUtility.logConstractor("Animal", name);
    }

    public void makeSound() {
        // TODO: add get sound method to each of the animals and make them include them all. (Maybe add interface or add method in the animal abstract class)
    }

    /**
     * Gets the food type
     *
     * @return type of food from the Enum
     */
    public EFoodType getFoodtype() {
        MessageUtility.logGetter(this.getClass().getSimpleName(), "getFoodType", EFoodType.MEAT);
        return EFoodType.MEAT;
    }

    public double getWeight() {
        MessageUtility.logGetter(this.name, "getWeight", this.weight);
        return this.weight;
    }

    public boolean setWeight(double weight) {
        boolean isSuccess = (weight >= 0);
        if (isSuccess)
            this.weight = weight;
        else
            this.weight = 0;
        MessageUtility.logSetter(this.name, "setWeight", weight, isSuccess);
        return isSuccess;
    }

    public IDiet getDiet() {
        MessageUtility.logGetter(this.name, "getDiet", this.diet);
        return this.diet;
    }

    public boolean setDiet(IDiet diet) {
        this.diet = diet;
        MessageUtility.logSetter(this.name, "setDiet", diet, true);
        return true; //TODO: Check if there is some thing to check here
    }

    public boolean setName(String name) {
        this.name = name;
        MessageUtility.logSetter(this.name, "setName", name, true);
        return true;
    }

    public String getName() {
        MessageUtility.logGetter(this.name, "getName", this.name);
        return this.name;
    }

    public boolean eat(IEdible food) {
        if (this.diet.canEat(food.getFoodtype())) {
            this.weight = this.diet.eat(this,food);
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName() + "] ";
    }
}
