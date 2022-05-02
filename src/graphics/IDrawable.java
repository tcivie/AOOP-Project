package graphics;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 18/04/2022
 */
public interface IDrawable {
    String PICTURE_PATH = "...";
    void loadImages(String nm) throws IOException;
    void drawObject (Graphics g);
    String getColor();
}
