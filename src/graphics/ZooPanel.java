package graphics;

import animals.Animal;
import food.Food;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 18/04/2022
 */
public class ZooPanel extends JPanel implements Runnable{

    private static final int MAX_CONCURRENT_THREADS = 2;
    private static final int MAX_WAIT_QUEUE = 1;

    private ThreadPool pool;

    private BufferedImage backgroundImage;

    private Color backgroundColor;
    public static volatile ArrayList<Animal> AnimalsInZoo;
    public static Food foodInZoo;
    private volatile boolean isTerminated = false;

    private String counter;

    public static final int MAX_ANIMALS = 2;

    private final Thread controller;

    private boolean animalsSuspended;

    public boolean isAnimalsSuspended() {
        return animalsSuspended;
    }

    public void setAnimalsSuspended(boolean animalsSuspended) {
        this.animalsSuspended = animalsSuspended;
    }

    public Thread getController() {
        return controller;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = String.valueOf(counter);
    }

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

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean terminated) {
        isTerminated = terminated;
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
        AnimalsInZoo = new ArrayList<>();
        setCounter(0);
        setVisible(true);
        setAnimalsSuspended(false);
        this.controller = new Thread(this);
        this.pool = new ThreadPool(MAX_CONCURRENT_THREADS,MAX_WAIT_QUEUE); // Create thread pool
    }


    /**
     * Checks if the frame needs repaint
     * @return
     */
    @Deprecated
    private boolean isChange() {
//        boolean flag = false;
//        for (int i = 0; i < AnimalsInZoo.size(); i++) {
//            if (AnimalsInZoo.get(i).getChanges()) { // check if something changed
//                flag = true;
//                AnimalsInZoo.get(i).setChanges(false);
//            }
//        }
//        return flag;
        return false;
    }

    /**
     * Method that check if the animal at any time can eat something of someone
     * @return returns True if ate Animal/ False otherwise
     */
    private boolean checkIfEat() {
        boolean ateAnimal = false;
         // check if there are any other animals in the zoo
        for (int i = 0; i < AnimalsInZoo.size(); i++) {
            if (AnimalsInZoo.size() > 1) {
                for (int j = i + 1; j < AnimalsInZoo.size(); j++) {
                    if (!pool.checkIfObjectBlocked(AnimalsInZoo.get(i)) && !pool.checkIfObjectBlocked(AnimalsInZoo.get(j))) {
                        if ((AnimalsInZoo.get(i).calcDistance(AnimalsInZoo.get(j).getLocation())) <= AnimalsInZoo.get(i).getEAT_DISTANCE()) {
                            synchronized (AnimalsInZoo.get(i)) {
                                if (AnimalsInZoo.get(i).eat(AnimalsInZoo.get(j))) {
                                    synchronized (AnimalsInZoo.get(j)) {
                                        AnimalsInZoo.get(j).setTerminated(true); // terminate the thread
                                        AnimalsInZoo.get(j).notify(); // Wake the animal and let the thread finish his work
                                        AnimalsInZoo.remove(j); // delete the animal from the zoo
                                        setCounter(Integer.parseInt(getCounter()) + 1);
                                        ateAnimal = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (ZooPanel.foodInZoo != null) {
                if (!pool.checkIfObjectBlocked(AnimalsInZoo.get(i))) {
                    if (AnimalsInZoo.get(i).calcDistance(ZooPanel.foodInZoo.getLocation()) < AnimalsInZoo.get(i).getEAT_DISTANCE()) {
                        if (AnimalsInZoo.get(i).eat(ZooPanel.foodInZoo)) {
                            ZooPanel.foodInZoo = null;
                            setCounter(Integer.parseInt(getCounter()) + 1);
                        }
                    }
                }
            }
        }
        return ateAnimal;
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
            for (Animal animal : AnimalsInZoo) {
                if (!pool.checkIfObjectBlocked(animal)) // Check if the animal is in the blocked queue
                    animal.drawObject(g);
            }
        }
        if (ZooPanel.foodInZoo != null) { // Check if there is animal
            ZooPanel.foodInZoo.drawObject(g);
        }
        g.setColor(Color.BLACK);
        g.drawString("Eat Counter: " + getCounter(),getWidth()/2-50,50);
        g.setColor(Color.WHITE);
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        Animal animal;
        while (!isTerminated()) {
                repaint();
                checkIfEat();
//                if(checkIfEat()) // Check if the animals can eat
//                    this.pool.pollQueue();
                for (Animal value : AnimalsInZoo) {
                    if (!pool.checkIfObjectBlocked(value)) {
                        animal = value;
                        if (animal.isThreadSuspended() && !isAnimalsSuspended()) {
                            synchronized (animal) {
                                animal.setResumed();
                                animal.notify();
                            }
                        }
                    }
                }
            try {
                sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < AnimalsInZoo.size(); i++) {
            animal = AnimalsInZoo.get(i);
            animal.setTerminated(true); // Terminate the animal threads
        }
        this.pool.killAll(); // Kill all running threads
    }

    /**
     * Adds animal to the zoo
     * @param animal
     * @return True if operation succeeded / False otherwise
     */
    public boolean addAnimalToZoo(Animal animal) {
        if (!(this.pool.addToThreadPool(animal)))
            return false;
        AnimalsInZoo.add(animal);
        return true;
    }
}
