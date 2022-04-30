package graphics;

import animals.Animal;
import mobility.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author glebtcivie
 * @Date 30/04/2022
 */
public class MoveAnimalDialog extends JDialog implements ItemListener {

    private JTextField oldX;
    private JTextField oldY;
    private JTextField newX;
    private JTextField newY;

    private JComboBox<Animal> animals;
    private Animal currentCard;

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
    public MoveAnimalDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        getAllAnimals();

        JLabel animal = new JLabel("Animal in the zoo: ");
        JLabel oldCord = new JLabel("Old Coordination's: ");
        JLabel newCord = new JLabel("New Coordination: ");

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.add(animal,BorderLayout.LINE_START);
        topPanel.add(animals,BorderLayout.LINE_END);
        mainPanel.add(topPanel,BorderLayout.PAGE_START);

        JPanel oldData = new JPanel(new FlowLayout());
        oldData.add(oldCord);
        oldX = new JTextField();
        oldX.setEditable(false);
        oldData.add(oldX);
        oldY = new JTextField();
        oldY.setEditable(false);
        oldData.add(oldY);

        JPanel centerBlock = new JPanel(new GridLayout(2,1));
        centerBlock.add(oldData);

        JPanel newData = new JPanel(new FlowLayout());
        newData.add(newCord);
        newX = new JTextField();
        newData.add(newX);
        newY = new JTextField();
        newData.add(newY);

        centerBlock.add(newData);

        mainPanel.add(centerBlock,BorderLayout.CENTER);
        add(mainPanel,BorderLayout.CENTER);

        JPanel buttons = new JPanel(new BorderLayout());
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttons.add(cancel,BorderLayout.LINE_START);

        JButton accept = new JButton("Accept");
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttons.add(accept,BorderLayout.LINE_END);

        add(buttons,BorderLayout.PAGE_END);

        setVisible(true);
    }

    /**
     * Gets all the initiated animals in the zoo and displays them in the dropdown menu
     */
    private void getAllAnimals() {
        animals = new JComboBox<Animal>();
        for (int i = 0; i < ZooFrame.AnimalsInZooNow; i++) {
            animals.addItem(ZooFrame.AnimalsInZoo[i]);
        }
        animals.addItemListener(this);
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
        currentCard = (Animal) e.getItem();
        Point location = currentCard.getLocation();
        oldX.setText(String.valueOf(location.getX()));
        oldY.setText(String.valueOf(location.getY()));
    }
}
