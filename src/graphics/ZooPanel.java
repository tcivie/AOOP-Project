package graphics;

import animals.Animal;

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

    public ZooPanel(int width, int height) {
        super();
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setBackgroundColor(Color.WHITE);

        setVisible(true);
    }

    public void manageZoo() {
        while (true) {
            if (isChange())
                repaint();
        }
    }

    /**
     * Checks if the frame needs repaint
     * @return
     */
    private boolean isChange() {
        boolean flag = false;
        for (int i = 0; i < ZooFrame.AnimalsInZooNow; i++) {
            if (ZooFrame.AnimalsInZoo[i].isNeedsRepaint()) {
                flag = true;
                ZooFrame.AnimalsInZoo[i].setNeedsRepaint(false);
            }
        }
        return flag;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Paint background on panel
        if (getBackgroundImage() != null)
            g.drawImage(getBackgroundImage(),0,0,getWidth(),getHeight(), this);
        else if (getBackgroundColor() != null) {
            g.setColor(getBackgroundColor());
            g.fillRect(0,0,getWidth(),getHeight());
        }
        if (ZooFrame.AnimalsInZooNow > 0) {
            for (int i = 0; i < ZooFrame.AnimalsInZooNow; i++) {
                ZooFrame.AnimalsInZoo[i].drawObject(g);
            }
        }
    }
}
