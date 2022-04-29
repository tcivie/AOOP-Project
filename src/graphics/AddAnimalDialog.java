package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author glebtcivie
 * @Date 18/04/2022
 */
public class AddAnimalDialog extends JDialog {

    private JPanel cards;

    /**
     * Creates a dialog with the specified title, owner {@code Frame}
     * and modality. If {@code owner} is {@code null},
     * a shared, hidden frame will be set as the owner of this dialog.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by {@code JComponent.getDefaultLocale}.
     * <p>
     * NOTE: Any popup components ({@code JComboBox},
     * {@code JPopupMenu}, {@code JMenuBar})
     * created within a modal dialog will be forced to be lightweight.
     * <p>
     * NOTE: This constructor does not allow you to create an unowned
     * {@code JDialog}. To create an unowned {@code JDialog}
     * you must use either the {@code JDialog(Window)} or
     * {@code JDialog(Dialog)} constructor with an argument of
     * {@code null}.
     *
     * @param owner the {@code Frame} from which the dialog is displayed
     * @param title the {@code String} to display in the dialog's
     *              title bar
     * @param modal specifies whether dialog blocks user input to other top-level
     *              windows when shown. If {@code true}, the modality type property is set to
     *              {@code DEFAULT_MODALITY_TYPE} otherwise the dialog is modeless
     * @throws HeadlessException if {@code GraphicsEnvironment.isHeadless()}
     *                           returns {@code true}.
     * @see ModalityType
     * @see ModalityType#MODELESS
     * @see Dialog#DEFAULT_MODALITY_TYPE
     * @see Dialog#setModal
     * @see Dialog#setModalityType
     * @see GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public AddAnimalDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        setSize(500,500);
        cards = new JPanel(new CardLayout());

        JPanel comboBoxPane = new JPanel();
        String[] comboBoxItems = {"Bear","Elephant","Giraffe","Lion","Turtle"};
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, (String)e.getItem());
            }
        });
        cb.setEditable(false);
        comboBoxPane.add(cb);

        //Create the "cards".
        JPanel card1 = new JPanel();
        card1.add(new JButton("Button 1"));
        card1.add(new JButton("Button 2"));
        card1.add(new JButton("Button 3"));

        JPanel card2 = new JPanel();
        card2.add(new JTextField("TextField", 20));

        //Create the panel that contains the "cards".
        cards.add(card1, "Bear");
        cards.add(card2, "Elephant");

        add(comboBoxPane, BorderLayout.PAGE_START);
        add(cards, BorderLayout.CENTER);
        setVisible(true);
    }
}
