package plants;

import mobility.Point;
import utilities.MessageUtility;

import java.io.IOException;

/**
 * @author baroh
 *
 */
public class Lettuce extends Plant {
	private static final String PICTURE_PATH = "src/graphics/assignment2_pictures/lettuce.png";
	public Lettuce(Point point) throws IOException {
		super(point,PICTURE_PATH);
		MessageUtility.logConstractor("Lettuce", "Lettuce");
	}
}
