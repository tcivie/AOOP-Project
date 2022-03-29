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

    public void chew() {

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
