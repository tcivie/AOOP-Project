package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author glebtcivie
 * @Date 18/04/2022
 */
public class ZooFrame extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("AOOP Assignment 2 - Zoo"); // Create the frame
        frame.setLayout(new BorderLayout()); // Set border layout

        frame.setJMenuBar(setMenuBar()); // add the menu to the frame
        frame.add(new ZooPanel()); // Add zoo panel to the frame

        frame.add(actionButtons(),BorderLayout.SOUTH); // Create new buttons panel and add it to the bottom

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets default close operation
        frame.setSize(300, 200); // Set the frame size
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centers the frame
        frame.pack();
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
     */
    private static JMenuBar setMenuBar() {
        JMenuBar menuBar = new JMenuBar(); // Create menu bar

        JMenu menuFile = new JMenu("File"); // Create section File
        JMenuItem menuItemExit = new JMenuItem("Exit"); // Add exit item


        menuItemExit.addActionListener(new ActionListener() { // Add action to exit item
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Frame frame:
                     getFrames()) {
                    frame.dispose();
                }
                getFrames()[0].dispose(); // Close all windows
            }
        });
        menuFile.add(menuItemExit);

        JMenu menuBackground = new JMenu("Background"); // Create section Background
        JMenuItem menuItemImage = new JMenuItem("Image"); // Add image item
        JMenuItem menuItemGreen = new JMenuItem("Green"); // Add green item
        JMenuItem menuItemNone = new JMenuItem("None"); // Add none item

        menuItemImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Add implementation of the menuItemImage
            }
        });
        menuItemGreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Add implementation of the menuItemGreen
            }
        });
        menuItemNone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Add implementation of the menuItemNone
            }
        });
        menuBackground.add(menuItemImage);
        menuBackground.add(menuItemGreen);
        menuBackground.add(menuItemNone);

        JMenu menuHelp = new JMenu("Help"); // Create section Background
        JMenuItem menuItemHelp = new JMenuItem("Help"); // Add image item

        menuItemHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(getFrames()[0],"Home Work 2\nGUI"); // Show popup with the message
            }
        });
        menuHelp.add(menuItemHelp);

        menuBar.add(menuFile);
        menuBar.add(menuBackground);
        menuBar.add(menuHelp);
        return menuBar;
    }

    private static JPanel actionButtons() {
        JPanel buttons = new JPanel();

        JButton addAnimalButton = new JButton("Add Animal");
        JButton moveAnimalButton = new JButton("Move Animal");
        JButton clearButton = new JButton("Clear");
        JButton foodButton = new JButton("Food");
        JButton infoButton = new JButton("Info");
        JButton exitButton = new JButton("Exit");
        //TODO: Add actions to the buttons

        buttons.add(addAnimalButton);
        buttons.add(moveAnimalButton);
        buttons.add(clearButton);
        buttons.add(foodButton);
        buttons.add(infoButton);
        buttons.add(exitButton);

        return buttons;
    }
}
