package animals;

import diet.Herbivore;
import food.EFoodType;
import mobility.Point;
import utilities.MessageUtility;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public class Elephant extends Animal{

    private double trunkLength;

    /**
     * Ctor
     * @param name Elephants name
     */
    public Elephant(String name) {
        super(name, new Point(50,90));
        MessageUtility.logConstractor("Elephant", name);
        setWeight(500);
        settrunkLength(1);
        setDiet(new Herbivore());
    }

    /**
     * Ctor
     * @param name Elephants name
     * @param x x value
     * @param y y value
     */
    public Elephant(String name, int x, int y) {
        super(name, new Point(x,y));
        MessageUtility.logConstractor("Elephant", name);
        setWeight(500);
        settrunkLength(1);
        setDiet(new Herbivore());
    }

    /**
     * Ctor
     * @param name Elephants name
     * @param length Trunk length
     */
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

    /**
     * Plays the chew sound with the help of the Firelog method in Animal
     */
    public void chew() {
        fireLog("logSound","Trumpets with joy while flapping its ears, then chews");
    }

    /**
     * Gets the trunk length
     * @return Trunk length
     */
    public double getTrunkLength() {
        fireLog("logGetter", "getTrunkLength", this.trunkLength);
        return this.trunkLength;
    }

    /**
     * Sets the trunks length
     * @param length the new trunks length
     * @return True if the operation was successful / False otherwise
     */
    public boolean settrunkLength(double length) {
        boolean isSuccess = (length >= 0.5 && length <= 3);
        if (isSuccess)
            this.trunkLength = length;
        else
            this.trunkLength = 1;
        fireLog("logSetter", "settrunkLength", length, isSuccess);
        return isSuccess;
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
