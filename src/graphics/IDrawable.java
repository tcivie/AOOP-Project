package graphics;

import javax.swing.*;
import java.awt.*;

/**
 * @author glebtcivie
 * @Date 18/04/2022
 */
public interface IDrawable {
    String PICTURE_PATH = "...";
    void loadImages(String nm);
    void drawObject (Graphics g);
    String getColor();
}
