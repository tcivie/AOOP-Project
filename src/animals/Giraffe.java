package animals;

import diet.Herbivore;
import mobility.Point;

import java.io.IOException;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public class Giraffe extends Animal {

    private double neckLength;

    /**
     * Ctor
     * @param name Giraffes name
     * @param x x point
     * @param y y point
     */
    public Giraffe(String name, int x, int y, int size, String col, int horSpeed, int verSpeed, double weight, double neckLength) throws IOException {
        super(name, new Point(x,y), size, col, horSpeed, verSpeed,weight);
        setNeckLength(neckLength);
        setWeight(weight);
        setDiet(new Herbivore());
        loadImages("grf");
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
        return isSuccess;
    }

    /**
     * Gets the new neck length
     * @return Giraffes neck length
     */
    public double getNeckLength() {
        return this.neckLength;
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
        fireLog("logSound","Bleats and Stomps its legs, then chews");
    }

}
