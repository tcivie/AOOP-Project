package graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author glebtcivie
 * @Date 18/04/2022
 */
public class ZooPanel extends JPanel {

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(BufferedImage backgroundImage) {
        if (getBackgroundColor() != null)
            setBackgroundColor(null);
        this.backgroundImage = backgroundImage;
        this.repaint();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        if (getBackgroundImage() != null)
            setBackgroundImage(null);
        this.backgroundColor = backgroundColor;
        this.repaint();
    }

    private BufferedImage backgroundImage;
    private Color backgroundColor;

//    /**
//     * When an object implementing interface {@code Runnable} is used
//     * to create a thread, starting the thread causes the object's
//     * {@code run} method to be called in that separately executing
//     * thread.
//     * <p>
//     * The general contract of the method {@code run} is that it may
//     * take any action whatsoever.
//     *
//     * @see Thread#run()
//     */
//    @Override
//    public void run() {
//
//    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getBackgroundImage() != null)
            g.drawImage(getBackgroundImage(), 0, 0, this);
        else if (getBackgroundColor() != null) {
            g.setColor(getBackgroundColor());
            g.fillRect(0,0,getWidth(),getHeight());
        }
    }
}
