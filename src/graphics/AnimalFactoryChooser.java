package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author glebtcivie
 * @Date 30/05/2022
 */
public class AnimalFactoryChooser {
    public AnimalFactoryChooser(ZooPanel zooPanel, Frame frameOwner, Component parentComponent) {
        String[] options = {"Carnivore","Omnivore","Herbivore"};
        String selection = String.valueOf(JOptionPane.showInputDialog(parentComponent,"Select animal type","Animal factory",JOptionPane.QUESTION_MESSAGE,null,options,options[0]));
        AnimalFactory factory = new AnimalFactory();
        switch (selection) {
            case "Carnivore" -> factory.createCarnivore(zooPanel,frameOwner);
            case "Omnivore" -> factory.createOmnivore(zooPanel,frameOwner);
            case "Herbivore" -> factory.createHerbivore(zooPanel,frameOwner);
        }
    }


}
