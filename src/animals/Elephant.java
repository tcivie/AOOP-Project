package animals;

import diet.Herbivore;
import mobility.Point;

import javax.swing.*;
import java.awt.*;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public class Elephant extends Animal{

    private double trunkLength;
    private static final String PICTURE_PATH = "src/graphics/assignment2_pictures/elf_n_1.png";

    /**
     * Ctor
     * @param name Elephants name
     */
    public Elephant(String name) {
        super(name, new Point(50,90));
        setWeight(500);
        settrunkLength(1);
        setDiet(new Herbivore());
    }

    /**
     * Ctor
     * @param name Elephants name
     * @param x x value
     * @param y y value
     */
    public Elephant(String name, int x, int y) {
        super(name, new Point(x,y));
        setWeight(500);
        settrunkLength(1);
        setDiet(new Herbivore());
    }

    /**
     * Ctor
     * @param name Elephants name
     * @param length Trunk length
     */
    public Elephant(String name, double length) {
        super(name, new Point(50,90));
        setWeight(500);
        settrunkLength(length);
        setDiet(new Herbivore());
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
        additionalParams.add(new JLabel("Trunk length:"));
        additionalParams.add(new JTextArea());

        card.add(additionalParams,BorderLayout.EAST);
        return card;
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

    /**
     * Gets the trunk length
     * @return Trunk length
     */
    public double getTrunkLength() {
        return this.trunkLength;
    }

    /**
     * Sets the trunks length
     * @param length the new trunks length
     * @return True if the operation was successful / False otherwise
     */
    public boolean settrunkLength(double length) {
        boolean isSuccess = (length >= 0.5 && length <= 3);
        if (isSuccess)
            this.trunkLength = length;
        else
            this.trunkLength = 1;
        return isSuccess;
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
}
