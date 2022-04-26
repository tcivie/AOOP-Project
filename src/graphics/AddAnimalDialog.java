package graphics;

import animals.Bear;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author glebtcivie
 * @Date 18/04/2022
 */
public class AddAnimalDialog extends JDialog {
    JPanel cards; //a panel that uses CardLayout

    public AddAnimalDialog(JFrame parentWindow, boolean modal) {
        super(parentWindow, "Add animal", modal);
        initComponents();
        setVisible(true);
        setSize(500,500);
    }

    private void initComponents() {
        JPanel comboBoxPane = new JPanel();
        String[] comboBoxItems = {"Bear","Elephant","Giraffe","Lion","Turtle"};
        JComboBox<String> cb = new JComboBox<String>(comboBoxItems);
        cb.addItemListener(e -> {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, (String)e.getItem());
        });

        cb.setEditable(false);
        comboBoxPane.add(cb);


        JPanel card2 = new JPanel();
        card2.add(new JTextField("TextField", 20));

        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(Bear.createCard(), "Bear");
        cards.add(card2, "Elephant");

        super.add(comboBoxPane, BorderLayout.PAGE_START);
        super.add(cards, BorderLayout.CENTER);
    }
}
