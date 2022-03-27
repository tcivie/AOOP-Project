package animals;

import diet.Herbivore;
import food.EFoodType;
import mobility.Point;
import utilities.MessageUtility;

public class Elephant extends Animal{

    private double trunkLength;

    public Elephant(String name) {
        super(name, new Point(50,90));
        this.trunkLength = 1;
        setDiet(new Herbivore());
        MessageUtility.logConstractor("Elephant", name);
    }

    public Elephant(String name, double length) {
        super(name, new Point(50,90));
        this.trunkLength = length;
        setDiet(new Herbivore());
        MessageUtility.logConstractor("Elephant", name);
    }

    public void chew() {
        
    }

    public void settrunkLength(double length) {
        this.trunkLength = length;
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
}
