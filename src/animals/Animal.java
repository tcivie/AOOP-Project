package animals;

import diet.IDiet;
import food.EFoodType;
import food.IEdible;
import graphics.EColors;
import graphics.IAnimalBehavior;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.Mobile;
import mobility.Point;
import utilities.MessageUtility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public abstract class Animal extends Mobile implements IEdible, IAnimalBehavior, IDrawable, Runnable {
    public int getEAT_DISTANCE() {
        return 100;
    }

    protected static final String PICTURE_PATH = "src/graphics/assignment2_pictures/";

    private String name;
    private double weight;

    private IDiet diet;

    private final int size;
    private final EColors col;
    private final int horSpeed;

    private final int verSpeed;

    private int x_dir;

    private int y_dir;

    private int eatCount = 0;
    private ZooPanel pan;
    private BufferedImage img1;
    private BufferedImage img2;

    protected volatile boolean threadSuspended;

    private volatile boolean isTerminated = false;

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean terminated) {
        isTerminated = terminated;
    }

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
     * @param weight Weight of the animal
     */
    public Animal(String name, Point point, int size, String col, int horSpeed, int verSpeed, double weight) {
        super(point);
        this.size = size;
        this.col = EColors.valueOf(col.toUpperCase());
        this.horSpeed = horSpeed;
        this.x_dir = horSpeed > 0 ? 1 : -1;
        this.verSpeed = verSpeed;
        this.y_dir = verSpeed > 0 ? 1 : -1;
        this.x_dir = 1;
        this.y_dir = 1;
        this.weight = weight;
        setName(name);
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
        while (!isTerminated()) {
            currentLocation = getLocation();
            move(currentLocation.getX() + getHorSpeed() * getX_dir(), currentLocation.getY() + getVerSpeed() * getY_dir());
            try {
                if (!isThreadSuspended() && !isTerminated()) {
                    synchronized (this) {
                        setSuspended();
                        this.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Terminated : " + getAnimalName());
    }

    /**
     * Play sound of the animal
     */
    @Deprecated
    public abstract void makeSound();

    /**
     * Gets the food type
     * @return type of food from the Enum
     */
    public EFoodType getFoodtype() {
        return EFoodType.MEAT;
    }

    /**
     * Gets the animal weight
     * @return animals weight
     */
    public double getWeight() {
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
        return isSuccess;
    }

    /**
     * Gets the animals diet
     * @return IDiet object of the animals diet
     */
    public IDiet getDiet() {
        return this.diet;
    }

    /**
     * Sets the animals Diet
     * @param diet the animals diet
     */
    public void setDiet(IDiet diet) {
        this.diet = diet;
    }

    /**
     * Sets the animals name
     * @param name The animals name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the animals name
     * @return Name of the animal
     */
    public String getName() {
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
    @Deprecated
    public void fireLog(String methodName , String funcName, Object value, boolean isSuccess) {
        switch (methodName) {
            case "logBooleanFunction" -> MessageUtility.logBooleanFunction(this.name, funcName, value, isSuccess);
            case "logSetter" -> MessageUtility.logSetter(this.name, funcName, value, isSuccess);
        }
    }

    /**
     * Helper method for firing logs with the library MessageUtility
     * @param methodName What method to fire [logGetter]
     * @param funcName Name of the function that executes the method
     * @param value the value that the function returned
     */
    @Deprecated
    public void fireLog(String methodName , String funcName, Object value) {
        if ("logGetter".equals(methodName)) {
            MessageUtility.logGetter(this.name, funcName, value);
        }
    }

    /**
     * Helper method for firing logs with the library MessageUtility
     * @param methodName What method to fire [logSound]
     * @param message The message to pass to logSound
     */
    @Deprecated
    public void fireLog(String methodName , String message) {
        if ("logSound".equals(methodName)) {
            MessageUtility.logSound(this.name, message);
        }
    }

    /**
     * Move method that gives the animal the signal to move and removes the animals weight according to a method:
     * weight - ( calcDistance(point) * weight * 0.00025 )
     * @param point Where the animal should go
     * @return If the move is possible then the moved distance / Otherwise 0
     */
    @Deprecated
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
        int size = getSize()/2; // Get the padding which is half of the image
        boolean isSuccess = Point.checkBounderies(x,y,size);
        double distance;
        if (!isSuccess) {
            if (x + size > Point.MAX_X || x - size < Point.MIN_X) {
                setX_dir(-getX_dir()); // Invert move direction
                if (x + size > Point.MAX_X)
                    x = Point.MAX_X - (x - Point.MAX_X);
                else
                    x = Point.MIN_X - x;
            }
            if (y + size > Point.MAX_Y || y - size < Point.MIN_Y) {
                setY_dir(-getY_dir()); // Invert move direction
                if (y + size > Point.MAX_Y)
                    y = Point.MAX_Y - (y - Point.MAX_Y);
                else
                    y = Point.MIN_Y - y;
            }
        }

        double weight = this.getWeight();
        distance = calcDistance(x,y);
        setWeight(Math.round((weight - ( distance * weight * 0.00025 )) * 100) / 100.);
        setLocation(x,y);
        this.addTotalDistance(distance);

        return distance;
    }

    public String getAnimalName() {
        return getName();
    }

    /**
     * Gets the animal size
     * @return Animal size
     */
    public int getSize() {
        return this.size;
    }

    public void eatInc() {
        eatCount++;
    }

    public int getEatCount() {
        return eatCount;
    }

    /**
     * loadImages - loads right facing image to img1 and left facing image to img2
     *
     * @param nm - type of the animal e.g : "bear".
     */
    public void loadImages(String nm) {
        String col = switch (this.col) {
            case NORMAL -> "n";
            case BLUE -> "b";
            case RED -> "r";
        };
        try {
            img1 = ImageIO.read(new File(PICTURE_PATH + nm + "_" + col + "_1.png"));
            img2 = ImageIO.read(new File(PICTURE_PATH + nm + "_" + col + "_2.png"));
        } catch (IOException e) {
            System.out.println("Cannot load image");
        }
    }

    public static String getPicturePath(String nm) {
        return PICTURE_PATH + nm + "_n_1.png";
    }

    public void drawObject(Graphics g) {
        Point location = getLocation();
        if(x_dir==1)
            g.drawImage(getImg1(), location.getX()-getSize()/2, location.getY()-getSize()/2, getSize(), getSize(), getPan()); // animal goes to the left side
        else
            g.drawImage(getImg2(), location.getX()-getSize()/2, location.getY()-getSize()/2, getSize(), getSize(), getPan()); // animal goes to the right
    }

    /**
     * Get animal color
     *
     * @return String color
     */
    public String getColor() {
        return this.col.toString();
    }

    /**
     * Get the panel
     * @return Zoo panel
     */
    public ZooPanel getPan() {
        return pan;
    }

    /**
     * Gets the first animal image
     * @return first image
     */
    public BufferedImage getImg1() {
        return img1;
    }

    /**
     * Gets the second animal image
     * @return second image
     */
    public BufferedImage getImg2() {
        return img2;
    }

    /**
     * Gets the x direction of the animal movement
     * @return if the animal is moving in the direction
     */
    public int getX_dir() {
        return x_dir;
    }

    /**
     * Gets the y direction of the animal movement
     * @return if the animal is moving in the direction
     */
    public int getY_dir() {
        return y_dir;
    }

    public int getHorSpeed() {
        return horSpeed;
    }

    public int getVerSpeed() {
        return verSpeed;
    }

    public void setX_dir(int x_dir) {
        this.x_dir = x_dir;
    }

    public void setY_dir(int y_dir) {
        this.y_dir = y_dir;
    }
}
