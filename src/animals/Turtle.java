package animals;

import diet.Herbivore;
import mobility.Point;
import utilities.MessageUtility;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public class Turtle extends Animal {

    private int Age;
    private static final String PICTURE_PATH = "src/graphics/assignment2_pictures/trt_n_1.png";

    /**
     * Ctor
     * @param name Turtles name
     */
    public Turtle(String name) {
        super(name, new Point(80,0));
        this.Age = 1;
        setDiet(new Herbivore());
        MessageUtility.logConstractor("Turtle", name);
    }

    /**
     * Ctor
     * @param name Turtles name
     * @param x x point
     * @param y y point
     */
    public Turtle(String name, int x, int y) {
        super(name, new Point(x,y));
        this.Age = 1;
        setDiet(new Herbivore());
        MessageUtility.logConstractor("Turtle", name);
    }

    /**
     * Ctor
     * @param name Turtles name
     * @param age Turtles age
     */
    public Turtle(String name, int age) {
        super(name, new Point(80,0));
        this.Age = age;
        setDiet(new Herbivore());
        MessageUtility.logConstractor("Turtle", name);
    }

    /**
     * Gets the turtles age
     * @return age
     */
    public int getAge() {
        fireLog("logGetter", "getAge", this.Age);
        return Age;
    }

    /**
     * Sets the turtles age
     * @param age The new age to set
     * @return True if the operation was successful / False otherwise
     */
    public boolean setAge(int age) {
        boolean isSuccess = (0 <= age && age <= 500);
        if (isSuccess)
            this.Age = age;
        fireLog("logSetter", "setAge", age, isSuccess);
        return isSuccess;
    }

    @Override
    public void makeSound() {
        chew();
    } //TODO: find better approach

    /**
     * Plays the chew sound with the help of the Firelog method in Animal
     */
    public void chew() {
        fireLog("logSound","Retracts its head in then eats quietly");
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
