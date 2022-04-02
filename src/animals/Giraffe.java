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
        fireLog("logSetter", "setNeckLength", length, isSuccess);
        return isSuccess;
    }

    public double getNeckLength() {
        fireLog("logGetter", "getNeckLength", this.neckLength);
        return this.neckLength;
    }

    @Override
    public void makeSound() {
        chew();
    } //TODO: find better approach

    public void chew() {
        fireLog("logSound","Bleats and Stomps its legs, then chews");
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName() + "]: " + super.toString();
    }
}
