package food;

import mobility.Point;

import java.io.IOException;

/**
 * @author Gleb Tcivie & Orel Dandeker
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
