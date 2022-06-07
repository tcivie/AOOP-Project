package plants;

import mobility.Point;

import java.io.IOException;

/**
 * @author baroh
 *
 */
public class Lettuce extends Plant {

	private static Lettuce lettuce = null;

	public static synchronized Lettuce getInstance(Point point) throws IOException {
		if (lettuce == null) {
			lettuce = new Lettuce(point);
		}
		return lettuce;
	}

	private static final String PICTURE_PATH = "src/graphics/assignment2_pictures/lettuce.png";
	private Lettuce(Point point) throws IOException {
		super(point,PICTURE_PATH);
	}
}
