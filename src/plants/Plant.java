package plants;

import java.io.IOException;

import food.EFoodType;
import food.Food;
import mobility.Point;

/**
 * @author baroh
 *
 */
public abstract class Plant extends Food {


	public Plant(Point point, String imagePath) {
		super(point, imagePath);
	}

	/**
	 * Gets the food type
	 *
	 * @return type of food from the Enum
	 */
	@Override
	public EFoodType getFoodtype() {
		return EFoodType.VEGETABLE;
	}
}
