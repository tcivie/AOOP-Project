package animals;

import diet.IDiet;
import food.EFoodType;
import food.IEdible;
import graphics.IAnimalBehavior;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.Mobile;
import mobility.Point;
import utilities.MessageUtility;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public abstract class Animal extends Mobile implements IEdible, IAnimalBehavior, IDrawable {

    private String name;
    private double weight;
    private IDiet diet;

    private final int EAT_DISTANCE = 5;
    private int size;
    private String col;
    private int horSpeed;
    private int verSpeed;
    private boolean coordChanged;
    private Thread thread;

    private int x_dir;

    private int y_dir;
    private int eatCount;
    private ZooPanel pan;
    private BufferedImage img1, img2;

    /**
     * Ctor
     * @param name Animals name
     * @param point Animals Position
     */
    public Animal(String name, Point point) {
        super(point);
        MessageUtility.logConstractor("Animal", name);
        setName(name);
    }

    /**
     * Play sound of the animal
     */
    public abstract void makeSound();

    /**
     * Gets the food type
     * @return type of food from the Enum
     */
    public EFoodType getFoodtype() {
        fireLog("logGetter", "getFoodType", EFoodType.MEAT);
        return EFoodType.MEAT;
    }

    /**
     * Gets the animal weight
     * @return animals weight
     */
    public double getWeight() {
        fireLog("logGetter", "getWeight", this.weight);
        return this.weight;
    }

    /**
     * Sets the animals weight
     * @param weight number of KG the animal should weight
     * @return True if the set was successful/ False otherwise
     */
    public boolean setWeight(double weight) {
        boolean isSuccess = (weight >= 0);
        if (isSuccess)
            this.weight = weight;
        else
            this.weight = 0;
        fireLog("logSetter", "setWeight", weight, isSuccess);
        return isSuccess;
    }

    /**
     * Gets the animals diet
     * @return IDiet object of the animals diet
     */
    public IDiet getDiet() {
        fireLog("logGetter", "getDiet", this.diet);
        return this.diet;
    }

    /**
     * Sets the animals Diet
     * @param diet the animals diet
     * @return True if the set was successful/ False otherwise
     */
    public boolean setDiet(IDiet diet) {
        this.diet = diet;
        fireLog("logSetter", "setDiet", diet, true);
        return true;
    }

    /**
     * Sets the animals name
     * @param name The animals name
     * @return True if the set was successful/ False otherwise
     */
    public boolean setName(String name) {
        this.name = name;
        fireLog("logSetter", "setName", name, true);
        return true;
    }

    /**
     * Gets the animals name
     * @return Name of the animal
     */
    public String getName() {
        fireLog("logGetter", "getName", this.name);
        return this.name;
    }

    /**
     * Eat method of the animal - checks and feeds the animal the relevant food
     * @param food What the animal will eat
     * @return True if the animal has been fed successfully / False otherwise
     */
    public boolean eat(IEdible food) {
        boolean isSuccess = (this.diet.canEat(food.getFoodtype()));
        if (isSuccess)
            setWeight(this.diet.eat(this,food) + this.weight);
        fireLog("logBooleanFunction", "eat", food, isSuccess);
        return isSuccess;
    }


    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "[" +this.getClass().getSimpleName() + "]" +this.name;
    }


    /**
     * Helper method for firing logs with the library MessageUtility
     * @param methodName What method to fire [logBooleanFunction,logSetter]
     * @param funcName Name of the function that executes the method
     * @param value the value that the function received
     * @param isSuccess True/False if the operation succeeded
     */
    public void fireLog(String methodName , String funcName, Object value, boolean isSuccess) {
        switch (methodName) {
            case "logBooleanFunction":
                MessageUtility.logBooleanFunction(this.name, funcName, value, isSuccess);
                break;
            case "logSetter":
                MessageUtility.logSetter(this.name, funcName, value, isSuccess);
                break;
        }
    }

    /**
     * Helper method for firing logs with the library MessageUtility
     * @param methodName What method to fire [logGetter]
     * @param funcName Name of the function that executes the method
     * @param value the value that the function returned
     */
    public void fireLog(String methodName , String funcName, Object value) {
        switch (methodName) {
            case "logGetter":
                MessageUtility.logGetter(this.name, funcName, value);
                break;
        }
    }
    /**
     * Helper method for firing logs with the library MessageUtility
     * @param methodName What method to fire [logSound]
     * @param message The message to pass to logSound
     */
    public void fireLog(String methodName , String message) {
        switch (methodName) {
            case "logSound":
                MessageUtility.logSound(this.name,message);
                break;
        }
    }

    /**
     * Move method that gives the animal the signal to move and removes the animals weight according to a method:
     * weight - ( calcDistance(point) * weight * 0.00025 )
     * @param point Where the animal should go
     * @return If the move is possible then the moved distance / Otherwise 0
     */
    public double move(Point point) {
        boolean isSuccess = Point.checkBounderies(point);
        double distance = 0;
        if (isSuccess) {
            double weight = this.getWeight();
            distance = calcDistance(point);
            isSuccess = setWeight(Math.round((weight - ( distance * weight * 0.00025 )) * 100) / 100.);
            setLocation(point);
            this.addTotalDistance(distance);
        }
        fireLog("logBooleanFunction","move",point,isSuccess);
        return distance;
    }

    public String getAnimalName() { //TODO: Add implementation
        return null;
    }

    /**
     * Gets the animal size
     * @return Animal size
     */
    public int getSize() {
        fireLog("logGetter", "getSize", this.size);
        return this.size;
    }

    public void eatInc() { //TODO: Add implementation

    }

    public int getEatCount() { //TODO: Add implementation
        return 0;
    }

    public boolean getChanges() { //TODO: Add implementation
        return false;
    }

    public void setChanges(boolean state) { //TODO: Add implementation

    }

    public void loadImages(String nm) { //TODO: Add implementation

    }

    public void drawObject(Graphics g) {
        Point location = getLocation();
        if(x_dir==1) // giraffe goes to the right side
            g.drawImage(getImg1(), location.getX()-getSize()/2, location.getY()-getSize()/10, getSize()/2, getSize(), getPan()); // animal goes to the left side
        else
            g.drawImage(getImg2(), location.getX(), location.getY()-getSize()/10, getSize()/2, getSize(), getPan()); // animal goes to the right
    }

    /**
     * Get animal color
     * @return String color
     */
    public String getColor() {
        fireLog("logGetter", "getColor", this.col);
        return this.col;
    }

    /**
     * Get the panel
     * @return Zoo panel
     */
    public ZooPanel getPan() {
        fireLog("logGetter", "getPan", this.pan);
        return pan;
    }

    /**
     * Gets the first animal image
     * @return first image
     */
    public BufferedImage getImg1() {
        fireLog("logGetter", "getImg1", this.img1);
        return img1;
    }

    /**
     * Gets the second animal image
     * @return second image
     */
    public BufferedImage getImg2() {
        fireLog("logGetter", "getImg2", this.img1);
        return img2;
    }

    /**
     * Gets the x direction of the animal movement
     * @return if the animal is moving in the direction
     */
    public int getX_dir() {
        fireLog("logGetter", "getX_dir", this.x_dir);
        return x_dir;
    }

    /**
     * Gets the y direction of the animal movement
     * @return if the animal is moving in the direction
     */
    public int getY_dir() {
        fireLog("logGetter", "getY_dir", this.y_dir);
        return y_dir;
    }
}
