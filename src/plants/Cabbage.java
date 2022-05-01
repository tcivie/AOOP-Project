package plants;

import food.EFoodType;
import mobility.Point;
import utilities.MessageUtility;

import java.io.IOException;

/**
 * @author baroh
 *
 */
public class Cabbage extends Plant {
	private static final String PICTURE_PATH = "src/graphics/assignment2_pictures/cabbage.png";
	public Cabbage(Point point) throws IOException {
		super(point,PICTURE_PATH);
		MessageUtility.logConstractor("Cabbage", "Cabbage");
	}
}
