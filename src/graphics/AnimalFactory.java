package graphics;

import animals.Animal;

import java.awt.*;

/**
 * @author glebtcivie
 * @Date 30/05/2022
 */
public class AnimalFactory implements IAbstractFactory{
    @Override
    public void createCarnivore(ZooPanel zooPanel, Frame owner) {
        String[] animalTypes = {"Lion"};
        new AddAnimalDialog(zooPanel,owner, "New Carnivore", true, animalTypes);
    }

    @Override
    public void createOmnivore(ZooPanel zooPanel, Frame owner) {
        String[] animalTypes = {"Bear"};
        new AddAnimalDialog(zooPanel,owner, "New Omnivore", true, animalTypes);
    }

    @Override
    public void createHerbivore(ZooPanel zooPanel, Frame owner) {
        String[] animalTypes = {"Elephant","Giraffe","Turtle"};
        new AddAnimalDialog(zooPanel,owner, "New Herbivore", true, animalTypes);
    }
}
