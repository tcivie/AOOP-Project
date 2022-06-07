package animals;

import diet.Herbivore;
import mobility.Point;

import java.io.IOException;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public class Elephant extends Animal{

    private double trunkLength;

    /**
     * Ctor
     * @param name Elephants name
     * @param x x value
     * @param y y value
     */
    public Elephant(String name, int x, int y, int size, String col, int horSpeed, int verSpeed, double weight, double trunkLength) throws IOException {
        super(name, new Point(x,y), size, col, horSpeed, verSpeed,weight);
//        MessageUtility.logConstractor("Elephant", name);
        setWeight(weight);
        settrunkLength(trunkLength);
        setDiet(new Herbivore());
        loadImages("elf");
    }

    @Override
    @Deprecated
    public void makeSound() {
        chew();
    } //TODO: find better approach

    /**
     * Plays the chew sound with the help of the Firelog method in Animal
     */
    @Deprecated
    public void chew() {
        fireLog("logSound","Trumpets with joy while flapping its ears, then chews");
    }

    /**
     * Gets the trunk length
     * @return Trunk length
     */
    public double getTrunkLength() {
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
        return isSuccess;
    }
}
