package graphics;

import food.Meat;
import mobility.Point;
import plants.Cabbage;
import plants.Lettuce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 30/04/2022
 */
public class AddFoodDialog extends JOptionPane implements ActionListener {

    private JPanel panel;

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JPanel getPanel() {
        return panel;
    }

    /**
     * Creates a instance of <code>JOptionPane</code> to display a
     * message using the
     * plain-message message type and the default options delivered by
     * the UI.
     *
     * @param panel the main zoo panel to print the object to
     * @param message the message to display
     * @param title the title to display
     */
    public AddFoodDialog(JPanel panel, String message, String title) {
        super(message);
        JButton lettuce = new JButton("Lettuce");
        JButton cabbage = new JButton("Cabbage");
        JButton meat = new JButton("Meat");

        setPanel(panel);

        lettuce.addActionListener(this);
        cabbage.addActionListener(this);
        meat.addActionListener(this);

        Object[] options = {meat, cabbage, lettuce};
        showOptionDialog(this,message,title,JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Frame frame = ZooFrame.getFrames()[0];
        JPanel panel = getPanel();
        try {
            switch ((String) e.getActionCommand()) {
                case "Meat" -> {ZooFrame.addFoodToZoo(new Meat(new Point(panel.getWidth()/2,panel.getHeight()/2)));} // Position at center of screen
                case "Cabbage" -> {ZooFrame.addFoodToZoo(new Cabbage(new Point(panel.getWidth()/2,panel.getHeight()/2)));}
                case "Lettuce" -> {ZooFrame.addFoodToZoo(new Lettuce(new Point(panel.getWidth()/2,panel.getHeight()/2)));}
            }

            getRootFrame().dispose();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}