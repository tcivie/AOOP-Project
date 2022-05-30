package graphics;

import animals.Animal;

import java.awt.*;

/**
 * @author glebtcivie
 * @Date 30/05/2022
 */
public interface IAbstractFactory {
    /**
     * Inits the creation of Carnivore animals
     */
    void createCarnivore(ZooPanel zooPanel, Frame owner);

    /**
     * Inits the creation of Omnivore animals
     */
    void createOmnivore(ZooPanel zooPanel, Frame owner);

    /**
     * Inits the creation of Herbivore animals
     */
    void createHerbivore(ZooPanel zooPanel, Frame owner);
}
