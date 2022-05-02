package graphics;

import animals.Animal;
import diet.Carnivore;
import diet.IDiet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * @author glebtcivie
 * @Date 18/04/2022
 */
public class ZooPanel extends JPanel {

    private BufferedImage backgroundImage;
    private Color backgroundColor;


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
            if (ZooFrame.AnimalsInZoo[i].getChanges()) { // check if something changed
                flag = true;
                ZooFrame.AnimalsInZoo[i].setChanges(false);;
                checkIfEat(); // Check if the animals can eat
            }
        }
        return flag;
    }

    /**
     * Method that check if the animal at any time can eat something of someone
     */
    private void checkIfEat() { // TODO: Test this out later tomorrow
         // check if there are any other animals in the zoo
        for (int i = 0; i < ZooFrame.AnimalsInZooNow; i++) {
            if (ZooFrame.AnimalsInZooNow > 1) {
                for (int j = i + 1; j < ZooFrame.AnimalsInZooNow; j++) {
                    if ((ZooFrame.AnimalsInZoo[i].calcDistance(ZooFrame.AnimalsInZoo[j].getLocation())) <= ZooFrame.AnimalsInZoo[i].getEAT_DISTANCE()) {
                        if (ZooFrame.AnimalsInZoo[i].eat(ZooFrame.AnimalsInZoo[j])) {
                            ZooFrame.deleteAnimalFromTheZoo(ZooFrame.AnimalsInZoo[j]); // Delete the animal from the zoo
                        }
                    }
                }
            }
            if (ZooFrame.foodInZoo != null) {
                if (ZooFrame.AnimalsInZoo[i].calcDistance(ZooFrame.foodInZoo.getLocation()) < ZooFrame.AnimalsInZoo[i].getEAT_DISTANCE()) {
                    if (ZooFrame.AnimalsInZoo[i].eat(ZooFrame.foodInZoo)) {
                        ZooFrame.foodInZoo = null;
                    }
                }
            }
        }
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
        if (ZooFrame.foodInZoo != null) {
            ZooFrame.foodInZoo.drawObject(g);
        }
    }
}
