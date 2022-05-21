package plants;

import mobility.Point;

import java.io.IOException;

/**
 * @author baroh
 *
 */
public class Cabbage extends Plant {
	private static final String PICTURE_PATH = "src/graphics/assignment2_pictures/cabbage.png";
	public Cabbage(Point point) throws IOException {
		super(point,PICTURE_PATH);
//		MessageUtility.logConstractor("Cabbage", "Cabbage");
	}
}
