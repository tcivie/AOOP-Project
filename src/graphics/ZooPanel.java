package graphics;

import animals.Animal;
import diet.Carnivore;
import diet.IDiet;
import food.Food;

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
    public static ArrayList<Animal> AnimalsInZoo;
    public static Food foodInZoo;

    private String counter;

    public String getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = String.valueOf(counter);
    }

    public static final int MAX_ANIMALS = 10;


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


    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public ZooPanel(int width, int height) {
        super();
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setBackgroundColor(Color.WHITE);
        AnimalsInZoo = new ArrayList<Animal>();
        setCounter(0);
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
        for (int i = 0; i < AnimalsInZoo.size(); i++) {
            if (AnimalsInZoo.get(i).getChanges()) { // check if something changed
                flag = true;
                AnimalsInZoo.get(i).setChanges(false);
                checkIfEat(); // Check if the animals can eat
            }
        }
        return flag;
    }

    /**
     * Method that check if the animal at any time can eat something of someone
     */
    private void checkIfEat() {
         // check if there are any other animals in the zoo
        for (int i = 0; i < AnimalsInZoo.size(); i++) {
            if (AnimalsInZoo.size() > 1) {
                for (int j = i + 1; j < AnimalsInZoo.size(); j++) {
                    if ((AnimalsInZoo.get(i).calcDistance(AnimalsInZoo.get(j).getLocation())) <= AnimalsInZoo.get(i).getEAT_DISTANCE()) {
                        if (AnimalsInZoo.get(i).eat(AnimalsInZoo.get(j))) {
                            AnimalsInZoo.remove(j); // delete the animal from the zoo
                            setCounter(Integer.parseInt(getCounter()) + 1);
                            repaint();
                        }
                    }
                }
            }
            if (ZooPanel.foodInZoo != null) {
                if (AnimalsInZoo.get(i).calcDistance(ZooPanel.foodInZoo.getLocation()) < AnimalsInZoo.get(i).getEAT_DISTANCE()) {
                    if (AnimalsInZoo.get(i).eat(ZooPanel.foodInZoo)) {
                        ZooPanel.foodInZoo = null;
                        setCounter(Integer.parseInt(getCounter()) + 1);
                        repaint();
                    }
                }
            }
        }
    }


    /**
     * Draws the elements on the panel
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Paint background on panel
        if (getBackgroundImage() != null) // Check if there is background image
            g.drawImage(getBackgroundImage(),0,0,getWidth(),getHeight(), this);
        else if (getBackgroundColor() != null) { // Check if there is background color
            g.setColor(getBackgroundColor());
            g.fillRect(0,0,getWidth(),getHeight());
        }
        if (AnimalsInZoo.size() > 0) { // Check if there are animals in the zoo
            for (int i = 0; i < AnimalsInZoo.size(); i++) {
                AnimalsInZoo.get(i).drawObject(g);
            }
        }
        if (ZooPanel.foodInZoo != null) { // Check if there is animal
            ZooPanel.foodInZoo.drawObject(g);
        }
        g.setColor(Color.BLACK);
        g.drawString("Eat Counter: " + getCounter(),getWidth()/2-50,50);
        g.setColor(Color.WHITE);
    }
}
