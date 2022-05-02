package animals;

import diet.Omnivore;
import food.IEdible;
import graphics.ZooFrame;
import mobility.Point;
import utilities.MessageUtility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public class Bear extends Animal{

    private String furColor;
    public static String[] COLORS = {"BLACK","WHITE","GRAY"};
    private static final String PICTURE_PATH = "src/graphics/assignment2_pictures/bea_n_1.png";
    private static final String PICTURE_PATH2 = "src/graphics/assignment2_pictures/bea_n_2.png";


    /**
     * Ctor
     * @param name Name of the bear
     * @param x x point
     * @param y y point
     */
    public Bear(String name, int x, int y, int size, String col, int horSpeed, int verSpeed, double weight, String furColor) throws IOException {
        super(name, new Point(x,y), size, col, horSpeed, verSpeed, convertFromFilename(PICTURE_PATH), convertFromFilename(PICTURE_PATH2), weight);
        MessageUtility.logConstractor("Bear", name);
        setWeight(weight);
        setFurColor(furColor);
        setDiet(new Omnivore());
    }

    /**
     * Changes the fur color of the bear
     * @param color Color from the list of available colors {"BLACK","WHITE","GRAY"}
     * @return True if the operation was successful / False otherwise
     */
    public boolean setFurColor(String color) {
        boolean isSuccess = (Arrays.asList(COLORS).contains(color));
        if (isSuccess)
            this.furColor = color;
        else
            this.furColor = "GRAY";
        fireLog("logSetter","setFurColor",color,isSuccess);
        return isSuccess;
    }

    /**
     * Returns the current fur color
     * @return Fur color of the bear
     */
    public String getFurColor() {
        fireLog("logGetter", "getFurColor", this.furColor);
        return this.furColor;
    }

    @Override
    public void makeSound() {
        roar();
    } //TODO: find better approach

    /**
     * Plays the roar sound with the help of the Firelog method in Animal
     */
    public void roar() {
        fireLog("logSound","Stands on its hind legs, roars and scratches its belly");
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
    public static String getPATH2() {
        return PICTURE_PATH2;
    }
}
