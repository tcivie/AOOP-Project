package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author glebtcivie
 * @Date 30/04/2022
 */
public class FoodDialog extends JOptionPane implements ActionListener {

    /**
     * Creates a instance of <code>JOptionPane</code> to display a
     * message using the
     * plain-message message type and the default options delivered by
     * the UI.
     *
     * @param message the <code>Object</code> to display
     */
    public FoodDialog(Frame rootFrame ,Object message, String title) {
        super(message);
        JButton lettuce = new JButton("Lettuce");
        JButton cabbage = new JButton("Cabbage");
        JButton meat = new JButton("Meat");

        lettuce.addActionListener(this);
        cabbage.addActionListener(this);
        meat.addActionListener(this);

        Object[] options = {meat, cabbage, lettuce};
        showOptionDialog(rootFrame,message,title,JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch ((String) e.getActionCommand()) {
            // TODO: Check if I need to add Meat class to the project
        }
    }
}
//
//    public FoodDialog(Frame owner, String title, boolean modal) {
//        super(owner, title, modal);
//
//        JButton lettuce = new JButton("Lettuce");
//        JButton cabbage = new JButton("Cabbage");
//        JButton meat = new JButton("Meat");
//
//        lettuce.addActionListener(this);
//        cabbage.addActionListener(this);
//        meat.addActionListener(this);
//
//        Object[] options = {lettuce, cabbage, meat};
//
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
//        setVisible(true);
//
//    }
//
//    /**
//     * Invoked when an action occurs.
//     *
//     * @param e the event to be processed
//     */
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//}
