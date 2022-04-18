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

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets default close operation
        frame.setSize(300, 200); // Set the frame size
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centers the frame
//        frame.pack();
    }

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
        menuBar.add(menuFile);
        return menuBar;
    }
}
