package animals;

import diet.Herbivore;
import food.EFoodType;
import mobility.Point;
import utilities.MessageUtility;

public class Elephant extends Animal{

    private double trunkLength;

    public Elephant(String name) {
        super(name, new Point(50,90));
        MessageUtility.logConstractor("Elephant", name);
        setWeight(500);
        settrunkLength(1);
        setDiet(new Herbivore());
    }

    public Elephant(String name, double length) {
        super(name, new Point(50,90));
        MessageUtility.logConstractor("Elephant", name);
        setWeight(500);
        settrunkLength(length);
        setDiet(new Herbivore());
    }

    @Override
    public void makeSound() {
        chew();
    } //TODO: find better approach

    public void chew() {
        fireLog("logSound","Trumpets with joy while flapping its ears, then chews");
    }

    public double getTrunkLength() {
        fireLog("logGetter", "getTrunkLength", this.trunkLength);
        return this.trunkLength;
    }

    public boolean settrunkLength(double length) {
        boolean isSuccess = (length >= 0.5 && length <= 3);
        if (isSuccess)
            this.trunkLength = length;
        else
            this.trunkLength = 1;
        fireLog("logSetter", "settrunkLength", length, isSuccess);
        return isSuccess;
    }
}
