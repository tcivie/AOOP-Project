package food;

import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 01/05/2022
 */
public abstract class Food implements IEdible, ILocatable, IDrawable {

    private double height;
    private Point location;
    private double weight;
    private String col;

    private ZooPanel pan;
    private BufferedImage img;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ZooPanel getPan() {
        return pan;
    }

    public void setPan(ZooPanel pan) {
        this.pan = pan;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }


    public Food(Point point, String imagePath) throws IOException {
        Random rand = new Random();
        setLocation(new Point(point));
        setHeight(rand.nextInt(30));
        setWeight(rand.nextInt(12));
        loadImages(imagePath);
    }

    /**
     * Gets the food type
     *
     * @return type of food from the Enum
     */
    @Override
    public abstract EFoodType getFoodtype();

    @Override
    public void loadImages(String nm) throws IOException {
        setImg(convertFromFilename(nm));
    }

    @Override
    public void drawObject(Graphics g) {
        g.drawImage(getImg(), location.getX(), location.getY(), 25, 25, getPan());
    }

    private Image getImg() {
        return img;
    }

    @Override
    public String getColor() {
        return col;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public void setLocation(Point point) {
        location = new Point(point);
    }

    /**
     * Creates buffered image from given path
     * @param path
     * @return Buffered image
     */
    protected static BufferedImage convertFromFilename(String path) throws IOException {
        return ImageIO.read(new File(path));
    }
}
