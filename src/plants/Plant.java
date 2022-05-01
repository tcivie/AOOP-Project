package plants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import food.EFoodType;
import food.Food;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;
import utilities.MessageUtility;

import javax.imageio.ImageIO;

/**
 * @author baroh
 *
 */
public abstract class Plant extends Food {


	public Plant(Point point, String imagePath) throws IOException {
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
