package graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author glebtcivie
 * @Date 18/04/2022
 */
public class ZooFrame extends JFrame {


    public static void main(String[] args) {
        JFrame frame = new JFrame("AOOP Assignment 2 - Zoo"); // Create the frame
        frame.setLayout(new BorderLayout()); // Set border layout

        ZooPanel zooPanel = new ZooPanel();
        JPanel actionButtons = actionButtons();

        frame.setJMenuBar(setMenuBar(zooPanel,actionButtons)); // add the menu to the frame

        frame.add(zooPanel,BorderLayout.CENTER); // Add zoo panel to the frame

        frame.add(actionButtons,BorderLayout.SOUTH); // Create new buttons panel and add it to the bottom

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets default close operation
        frame.setSize(300, 200); // Set the frame size
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centers the frame
        frame.pack();
        frame.setVisible(true);
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
    private static JMenuBar setMenuBar(ZooPanel zooPanel, JPanel actionButtons) {
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
            }
        });
        menuItemGreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zooPanel.setBackgroundColor(Color.GREEN);
            }
        });
        menuItemNone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zooPanel.setBackgroundColor(null);
                zooPanel.setBackgroundImage(null);
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
//                JOptionPane.showInputDialog(getFrames()[0],new JComboBox<String>(),"COMBOBOX");
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
