package animals;

import diet.Herbivore;
import mobility.Point;
import utilities.MessageUtility;

import java.io.IOException;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public class Giraffe extends Animal {

    private double neckLength;
    private static final String PICTURE_PATH = "src/graphics/assignment2_pictures/grf_n_1.png";
    private static final String PICTURE_PATH2 = "src/graphics/assignment2_pictures/grf_n_2.png";

//    /**
//     * Ctor
//     * @param name Giraffes name
//     */
//    public Giraffe(String name) {
//        super(name, new Point(50,0), size, col, horSpeed, verSpeed, x_dir, y_dir, img1, img2);
//        setNeckLength(1.5);
//        setDiet(new Herbivore());
//        MessageUtility.logConstractor("Giraffe", name);
//    }

    /**
     * Ctor
     * @param name Giraffes name
     * @param x x point
     * @param y y point
     */
    public Giraffe(String name, int x, int y, int size, String col, int horSpeed, int verSpeed, double weight, double neckLength) throws IOException {
        super(name, new Point(x,y), size, col, horSpeed, verSpeed, convertFromFilename(PICTURE_PATH), convertFromFilename(PICTURE_PATH2), weight);
        setNeckLength(neckLength);
        setWeight(weight);
        setDiet(new Herbivore());
        MessageUtility.logConstractor("Giraffe", name);
    }

//    /**
//     * Ctor
//     * @param name Giraffes name
//     * @param length Length of the neck
//     */
//    public Giraffe(String name, double length) {
//        super(name, new Point(50,0), size, col, horSpeed, verSpeed, x_dir, y_dir, img1, img2);
//        setNeckLength(length);
//        setDiet(new Herbivore());
//        MessageUtility.logConstractor("Giraffe", name);
//    }

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


    public static String getPATH() {
        return PICTURE_PATH;
    }
}
