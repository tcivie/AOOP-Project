package plants;

import mobility.Point;

import java.io.IOException;

/**
 * @author baroh
 *
 */
public class Cabbage extends Plant {

	private static Cabbage cabbage = null;

	public static synchronized Cabbage getInstance(Point point) throws IOException {
		if (cabbage == null) {
			cabbage = new Cabbage(point);
		}
		return cabbage;
	}

	private static final String PICTURE_PATH = "src/graphics/assignment2_pictures/cabbage.png";
	private Cabbage(Point point) throws IOException {
		super(point,PICTURE_PATH);
//		MessageUtility.logConstractor("Cabbage", "Cabbage");
	}
}
