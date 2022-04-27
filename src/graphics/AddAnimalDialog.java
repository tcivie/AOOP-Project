package graphics;

import animals.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author glebtcivie
 * @Date 18/04/2022
 */
public class AddAnimalDialog extends JDialog implements ItemListener {
    private JPanel cards; //a panel that uses CardLayout
    private JComboBox<String> cb;

    public AddAnimalDialog(JFrame parentWindow, boolean modal) {
        super(parentWindow, "Add animal", modal);
        initComponents();
        setSize(800,350);
        setResizable(false);
        setVisible(true);
    }

    private void initComponents() {
        JPanel comboBoxPane = new JPanel();
        String[] comboBoxItems = {"Bear","Elephant","Giraffe","Lion","Turtle"}; // Add cards
        cb = new JComboBox<String>(comboBoxItems);
        cb.addItemListener(this);

        cb.setEditable(false);
        comboBoxPane.add(cb);

        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(Bear.createCard(), "Bear");
        cards.add(Elephant.createCard(), "Elephant");
        cards.add(Giraffe.createCard(), "Giraffe");
        cards.add(Lion.createCard(), "Lion");
        cards.add(Turtle.createCard(), "Turtle");

        // Create submit buttons and cancel
        JPanel buttons = new JPanel(new FlowLayout());
        JButton cancel = new JButton("Cancel");
        JButton submit = new JButton("Submit");

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttons.add(cancel);
        buttons.add(submit);

        add(comboBoxPane, BorderLayout.PAGE_START);
        add(cards, BorderLayout.CENTER);
        add(buttons,BorderLayout.SOUTH);
    }

    /**
     * Invoked when an item has been selected or deselected by the user.
     * The code written for this method performs the operations
     * that need to occur when an item is selected (or deselected).
     *
     * @param e the event to be processed
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)e.getItem());
    }
}
