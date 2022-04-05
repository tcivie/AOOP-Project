package animals;

import diet.Herbivore;
import food.EFoodType;
import mobility.Point;
import utilities.MessageUtility;

/**
 * @author Gleb Tcivie
 * @Date 5/4/22
 */
public class Giraffe extends Animal {

    private double neckLength;

    /**
     * Ctor
     * @param name Giraffes name
     */
    public Giraffe(String name) {
        super(name, new Point(50,0));
        setNeckLength(1.5);
        setDiet(new Herbivore());
        MessageUtility.logConstractor("Giraffe", name);
    }

    /**
     * Ctor
     * @param name Giraffes name
     * @param x x point
     * @param y y point
     */
    public Giraffe(String name, int x, int y) {
        super(name, new Point(x,y));
        setNeckLength(1.5);
        setDiet(new Herbivore());
        MessageUtility.logConstractor("Giraffe", name);
    }

    /**
     * Ctor
     * @param name Giraffes name
     * @param length Length of the neck
     */
    public Giraffe(String name, double length) {
        super(name, new Point(50,0));
        setNeckLength(length);
        setDiet(new Herbivore());
        MessageUtility.logConstractor("Giraffe", name);
    }

    /**
     * Sets the Giraffes neck length
     * @param length new neck length
     * @return True if the operation was successful / False otherwise
     */
    public boolean setNeckLength(double length) {
        boolean isSuccess = (length >= 1 && length <= 2.5);
        if (isSuccess)
            this.neckLength = length;
        else
            this.neckLength = 1.5;
        fireLog("logSetter", "setNeckLength", length, isSuccess);
        return isSuccess;
    }

    /**
     * Gets the new neck length
     * @return Giraffes neck length
     */
    public double getNeckLength() {
        fireLog("logGetter", "getNeckLength", this.neckLength);
        return this.neckLength;
    }

    @Override
    public void makeSound() {
        chew();
    } //TODO: find better approach

    /**
     * Plays the chew sound with the help of the Firelog method in Animal
     */
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
        return super.toString();
    }
}
