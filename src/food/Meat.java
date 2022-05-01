package food;

import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;
import utilities.MessageUtility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author glebtcivie
 * @Date 01/05/2022
 */
public class Meat extends Food {

    private static final String PICTURE_PATH = "src/graphics/assignment2_pictures/meat.gif";

    public Meat(Point point) throws IOException {
        super(point,PICTURE_PATH);
    }

    /**
     * Gets the food type
     *
     * @return type of food from the Enum
     */
    @Override
    public EFoodType getFoodtype() {
        return EFoodType.MEAT;
    }
}
