package animals;

import diet.Herbivore;
import mobility.Point;

import java.io.IOException;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public class Turtle extends Animal {

    private int Age;

    /**
     * Ctor
     * @param name Turtles name
     * @param x x point
     * @param y y point
     */
    public Turtle(String name, int x, int y, int size, String col, int horSpeed, int verSpeed, double weight, int age) throws IOException {
        super(name, new Point(x,y), size, col, horSpeed, verSpeed, weight);
        setAge(age);
        setWeight(weight);
        setDiet(new Herbivore());
        loadImages("trt");
    }


    /**
     * Gets the turtles age
     * @return age
     */
    public int getAge() {
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
        return isSuccess;
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
        fireLog("logSound","Retracts its head in then eats quietly");
    }

}
