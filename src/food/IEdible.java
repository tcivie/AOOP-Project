package food;

import mobility.Point;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public interface IEdible {
    /**
     * Gets the food type
     * @return type of food from the Enum
     */
    EFoodType getFoodtype();

    double getWeight();

    Point getLocation();
}
