package animals;

import diet.Herbivore;
import mobility.Point;

import javax.swing.*;
import java.awt.*;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public class Giraffe extends Animal {

    private double neckLength;
    private static final String PICTURE_PATH = "src/graphics/assignment2_pictures/grf_n_1.png";

    /**
     * Ctor
     * @param name Giraffes name
     */
    public Giraffe(String name) {
        super(name, new Point(50,0));
        setNeckLength(1.5);
        setDiet(new Herbivore());
    }

    /**
     * Ctor
     * @param name Giraffes name
     * @param x x point
     * @param y y point
     */
    public Giraffe(String name, int x, int y) {
        super(name, new Point(x,y));
        setNeckLength(1.5);
        setDiet(new Herbivore());
    }

    /**
     * Ctor
     * @param name Giraffes name
     * @param length Length of the neck
     */
    public Giraffe(String name, double length) {
        super(name, new Point(50,0));
        setNeckLength(length);
        setDiet(new Herbivore());
    }

    /**
     * Sets the Giraffes neck length
     * @param length new neck length
     * @return True if the operation was successful / False otherwise
     */
    public boolean setNeckLength(double length) {
        boolean isSuccess = (length >= 1 && length <= 2.5);
        if (isSuccess)
            this.neckLength = length;
        else
            this.neckLength = 1.5;
        return isSuccess;
    }

    /**
     * Gets the new neck length
     * @return Giraffes neck length
     */
    public double getNeckLength() {
        return this.neckLength;
    }

    @Override
    public void makeSound() {
        chew();
    } //TODO: find better approach

    /**
     * Plays the chew sound with the help of the Firelog method in Animal
     */
    public void chew() {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Creates the card for the ctor of the animal
     * @return Card with the relevant data added
     */
    public static JPanel createCard() {
        JPanel card = Animal.createPanel(PICTURE_PATH);

        JPanel additionalParams = new JPanel();
        additionalParams.setBorder(BorderFactory.createTitledBorder("Additional parameters"));
        additionalParams.setLayout(new GridLayout(1,2));
        additionalParams.add(new JLabel("Neck length:"));
        additionalParams.add(new JTextArea());

        card.add(additionalParams,BorderLayout.EAST);
        return card;
    }
}
