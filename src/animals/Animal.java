package animals;

import diet.IDiet;
import food.EFoodType;
import food.IEdible;
import graphics.IAnimalBehavior;
import graphics.IDrawable;
import graphics.ZooFrame;
import graphics.ZooPanel;
import mobility.Mobile;
import mobility.Point;
import utilities.MessageUtility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public abstract class Animal extends Mobile implements IEdible, IAnimalBehavior, IDrawable, Runnable {

    private String name;
    private double weight;
    private IDiet diet;

    private final int EAT_DISTANCE = 100;


    private final int size;
    private final String col;
    private final int horSpeed;
    private final int verSpeed;

    private int x_dir;

    private int y_dir;

    private int eatCount = 0;

    private ZooPanel pan;
    private final BufferedImage img1, img2;
    protected final Thread thread;

    protected volatile boolean threadSuspended;

    public synchronized boolean isThreadSuspended() {
        return threadSuspended;
    }

    /**
     * Ctor
     * @param name Animals name
     * @param point Animals Position
     * @param size Animals size
     * @param col Color from the list
     * @param horSpeed Horizontal movement speed
     * @param verSpeed vertical movement speed
     * @param img1 One side image
     * @param img2 Second side image
     * @param weight Weight of the animal
     */
    public Animal(String name, Point point, int size, String col, int horSpeed, int verSpeed, BufferedImage img1, BufferedImage img2, double weight) {
        super(point);
        this.size = size;
        this.col = col;
        this.horSpeed = horSpeed;
        this.x_dir = horSpeed > 0 ? 1 : -1;
        this.verSpeed = verSpeed;
        this.y_dir = verSpeed > 0 ? 1 : -1;
        this.x_dir = 1;
        this.y_dir = 1;
        this.img1 = img1;
        this.img2 = img2;
        this.weight = weight;
        MessageUtility.logConstractor("Animal", name);
        setName(name);
        this.thread = new Thread(this);
    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public synchronized void setSuspended() {
        this.threadSuspended = true;
    }

    @Override
    public synchronized void setResumed() {
        this.threadSuspended = false;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        Point currentLocation;
        setResumed();
        while (true) {
            currentLocation = getLocation();
            move(currentLocation.getX() + getHorSpeed() * getX_dir(), currentLocation.getY() + getVerSpeed() * getY_dir());
            try {
                if (!isThreadSuspended()) {
                    synchronized (this) {
                        setSuspended();
                        this.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
        boolean isSuccess = ((this.getDiet().canEat(food.getFoodtype())) &&
                (getWeight() >= food.getWeight() * 2) && (getSize() > calcDistance(food.getLocation())));
        if (isSuccess) {
            setWeight(this.diet.eat(this, food) + this.weight);
            eatInc();
        }
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

    /**
     * Move method that gives the animal the signal to move and removes the animals weight according to a method:
     * weight - ( calcDistance(point) * weight * 0.00025 )
     * If the animal is at the corner of the screen then switch the direction in which it goes
     * @param x X coordinate to where to go
     * @param y Y coordinate to where to go
     * @return If the move is possible then the moved distance / Otherwise 0
     */
    public double move(int x, int y) {
        boolean isSuccess = Point.checkBounderies(x,y);
        double distance = 0;
        if (isSuccess) {
            double weight = this.getWeight();
            distance = calcDistance(x,y);
            isSuccess = setWeight(Math.round((weight - ( distance * weight * 0.00025 )) * 100) / 100.);
            setLocation(x,y);
            this.addTotalDistance(distance);
        } else {
             if ( x >= Point.MAX_X || x <= Point.MIN_X) {
                 if (x >= Point.MAX_X)
                     x = Point.MAX_X - (x - Point.MAX_X);
                 else
                     x = Point.MIN_X + Math.abs(x);
                 setX_dir(-getX_dir()); // Invert move direction
             } if ( y >= Point.MAX_Y || y <= Point.MIN_Y) {
                if (y >= Point.MAX_Y)
                    y = Point.MAX_Y - (y - Point.MAX_Y);
                else
                    y = Point.MIN_Y + Math.abs(y);
                setY_dir(-getY_dir()); // Invert move direction
            }
             return move(x,y); // Calls the method again so that the changes will take effect
        }
        fireLog("logBooleanFunction","move","(" + x + "," + y + ")",isSuccess);
        return distance;
    }

    public String getAnimalName() { //TODO: Add implementation
        return getName();
    }

    /**
     * Gets the animal size
     * @return Animal size
     */
    public int getSize() {
        fireLog("logGetter", "getSize", this.size);
        return this.size;
    }

    public void eatInc() {
        eatCount++;
    }

    public int getEatCount() {
        return eatCount;
    }

    public void loadImages(String nm) { //TODO: Add implementation
//        try {
//            ImageIO.read(new File(nm));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void drawObject(Graphics g) {
        Point location = getLocation();
        if(x_dir==1)
            g.drawImage(getImg1(), Math.abs(location.getX()-getSize()/2), Math.abs(location.getY()-getSize()/10), getSize()/2, getSize(), getPan()); // animal goes to the left side
        else
            g.drawImage(getImg2(), location.getX(), Math.abs(location.getY()-getSize()/10), getSize()/2, getSize(), getPan()); // animal goes to the right
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

    /**
     * Creates buffered image from given path
     * @param path
     * @return Buffered image
     */
    protected static BufferedImage convertFromFilename(String path) throws IOException {
        return ImageIO.read(new File(path));
    }

    public int getHorSpeed() {
        return horSpeed;
    }

    public int getVerSpeed() {
        return verSpeed;
    }

    public int getEAT_DISTANCE() {
        return EAT_DISTANCE;
    }

    public void setX_dir(int x_dir) {
        this.x_dir = x_dir;
    }

    public void setY_dir(int y_dir) {
        this.y_dir = y_dir;
    }
}
