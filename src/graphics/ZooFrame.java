package graphics;

import animals.Animal;
import food.Food;
import mobility.Point;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 18/04/2022
 */
public class ZooFrame extends JFrame {

    private AddAnimalDialog addAnimalDialog;
    private MoveAnimalDialog moveAnimalDialog;
    private final ZooPanel zooPanel;
    private AddFoodDialog addFoodDialog;
    private AnimalData animalData;

    public ZooPanel getZooPanel() {
        return zooPanel;
    }

    /**
     * Creates a new, initially invisible <code>Frame</code> with the
     * specified title.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param title the title for the frame
     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
     *                           returns true.
     * @see GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public ZooFrame(String title) throws HeadlessException {
        super(title);
        setLayout(new BorderLayout()); // Set border layout

        zooPanel = new ZooPanel(Point.MAX_X,Point.MAX_Y);
        JPanel actionButtons = actionButtons();

        setJMenuBar(setMenuBar(zooPanel,actionButtons)); // add the menu to the frame

        add(zooPanel,BorderLayout.CENTER); // Add zoo panel to the frame

        add(actionButtons,BorderLayout.SOUTH); // Create new buttons panel and add it to the bottom

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets default close operation
        setSize(Point.MAX_X + 400, Point.MAX_Y + 400); // Set the frame size
        setLocationRelativeTo(null); // Centers the frame
        pack();
        zooPanel.getController().start(); // Start the panel
        setVisible(true);
    }

    public static void main(String[] args) {
        ZooFrame frame = new ZooFrame("AOOP Assignment 2 - Zoo");
        Thread zooPanel = new Thread(frame.zooPanel);
        zooPanel.start();
    }


    /**
     * Adds animal to the zoo
     * @param animal
     * @return True if operation succeeded / False otherwise
     */
    public static boolean addAnimalToZoo(Animal animal) {
        if (ZooPanel.AnimalsInZoo.size() >= ZooPanel.MAX_ANIMALS)
            return false;
        ZooPanel.AnimalsInZoo.add(animal);
        animal.getThread().start(); // Start animal thread
        return true;
    }

    /**
     * Adds animal to the zoo
     * @param food
     * @return True if operation succeeded / False otherwise
     */
    public static boolean addFoodToZoo(Food food) {
        if (ZooPanel.foodInZoo == null) {
            ZooPanel.foodInZoo = food;
            return true;
        }
        return false;
    }

    /**
     * Creates new navigation bar with JMenuBar with the following hierarchy:
     *
     * NavBar/
     * ├─ File/
     * │  ├─ Exit
     * ├─ Background/
     * │  ├─ Image
     * │  ├─ Green
     * │  ├─ None
     * ├─ Help/
     * │  ├─ Help
     *
     * @return new JMenu bar with the fields and the actions
     * @param zooPanel
     */
    private JMenuBar setMenuBar(ZooPanel zooPanel, JPanel actionButtons) {
        JMenuBar menuBar = new JMenuBar(); // Create menu bar

        JMenu menuFile = new JMenu("File"); // Create section File
        JMenuItem menuItemExit = new JMenuItem("Exit"); // Add exit item


        // Add action to exit item
        menuItemExit.addActionListener(e -> { //TODO: Kill all the animals and their threads
            zooPanel.setTerminated(true);
            for (Frame frame:
                 getFrames()) {
                frame.dispose();
            }
            getFrames()[0].dispose(); // Close all windows
            System.exit(0);
        });
        menuFile.add(menuItemExit);

        JMenu menuBackground = new JMenu("Background"); // Create section Background
        JMenuItem menuItemImage = new JMenuItem("Image"); // Add image item
        JMenuItem menuItemGreen = new JMenuItem("Green"); // Add green item
        JMenuItem menuItemNone = new JMenuItem("None"); // Add none item

        menuItemImage.addActionListener(e -> { // Add Background image
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(getFrames()[0]);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage bgImage = ImageIO.read(fc.getSelectedFile());
                    zooPanel.setBackgroundImage(bgImage);
                    zooPanel.setSize(bgImage.getWidth(), bgImage.getHeight()); // resize the panel to match the zoo size
                    getFrames()[0].setSize(bgImage.getWidth(), bgImage.getHeight() + actionButtons.getHeight()*2); // resize the frame to include the image
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(getFrames()[0], "There's an issue with the file you chose", "Error: File choosing", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Set background color to green
        menuItemGreen.addActionListener(e -> zooPanel.setBackgroundColor(Color.GREEN));
        // Remove all backgrounds
        menuItemNone.addActionListener(e -> {
            zooPanel.setBackgroundColor(null);
            zooPanel.setBackgroundImage(null);
        });
        menuBackground.add(menuItemImage);
        menuBackground.add(menuItemGreen);
        menuBackground.add(menuItemNone);

        JMenu menuHelp = new JMenu("Help"); // Create section Background
        JMenuItem menuItemHelp = new JMenuItem("Help"); // Add image item

        // Display help popup
        menuItemHelp.addActionListener(e -> {
            JOptionPane.showMessageDialog(getFrames()[0],"Home Work 2\nGUI"); // Show popup with the message
        });
        menuHelp.add(menuItemHelp);

        menuBar.add(menuFile);
        menuBar.add(menuBackground);
        menuBar.add(menuHelp);
        return menuBar;
    }

    private JPanel actionButtons() {
        JPanel buttons = new JPanel();
        JPanel cards = new JPanel(new CardLayout());

        JButton addAnimalButton = new JButton("Add Animal");
        addAnimalButton.addActionListener(e -> { // Creates new animals
            addAnimalDialog = new AddAnimalDialog(getFrames()[0], "New Animal", true);
            zooPanel.repaint();
        });
        JButton sleepAnimalButton = new JButton("Sleep");
        sleepAnimalButton.addActionListener(e -> { // Moves the animal
//                moveAnimalDialog = new MoveAnimalDialog(getFrames()[0], "Sleep", true );
            zooPanel.setAnimalsSuspended(true);
//                zooPanel.repaint();
        });
        JButton wakeUpAnimalsButton = new JButton("WakeUp");
        wakeUpAnimalsButton.addActionListener(e -> zooPanel.setAnimalsSuspended(false));
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> { // Kills all the animals :(
            ZooPanel.AnimalsInZoo = new ArrayList<>();
            ZooPanel.foodInZoo = null;
            zooPanel.repaint();
        });
        JButton foodButton = new JButton("Food");
        foodButton.addActionListener(e -> {
            addFoodDialog = new AddFoodDialog(getZooPanel(),"Please choose food", "Food for animals");
            zooPanel.repaint();
        });
        JButton infoButton = new JButton("Info");
        infoButton.addActionListener(e -> animalData = new AnimalData(getFrames()[0],"Animal Data", true));
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {//TODO: Kill all the animals and their threads
            zooPanel.setTerminated(true);
            for (Frame frame:
                    getFrames()) {
                frame.dispose();
            }
            getFrames()[0].dispose(); // Close all windows
            System.exit(0);
        });

        buttons.add(addAnimalButton);
        buttons.add(sleepAnimalButton);
        buttons.add(wakeUpAnimalsButton);
        buttons.add(clearButton);
        buttons.add(foodButton);
        buttons.add(infoButton);
        buttons.add(exitButton);

        return buttons;
    }
}
