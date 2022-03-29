package animals;

import diet.Herbivore;
import food.EFoodType;
import mobility.Point;
import utilities.MessageUtility;

public class Giraffe extends Animal {

    private double neckLength;

    public Giraffe(String name) {
        super(name, new Point(50,0));
        setNeckLength(1.5);
        setDiet(new Herbivore());
        MessageUtility.logConstractor("Giraffe", name);
    }

    public Giraffe(String name, double length) {
        super(name, new Point(50,0));
        setNeckLength(length);
        setDiet(new Herbivore());
        MessageUtility.logConstractor("Giraffe", name);
    }

    public boolean setNeckLength(double length) {
        boolean isSuccess = (length >= 1 && length <= 2.5);
        if (isSuccess)
            this.neckLength = length;
        else
            this.neckLength = 1.5;
        MessageUtility.logSetter(this.getClass().getSimpleName(), "setNeckLength", length, isSuccess);
        return isSuccess;
    }

    public double getNeckLength() {
        MessageUtility.logGetter(this.getClass().getSimpleName(), "getNeckLength", this.neckLength);
        return this.neckLength;
    }
}
