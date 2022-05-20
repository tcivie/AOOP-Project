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
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 30/04/2022
 */
public class MoveAnimalDialog extends JDialog implements ItemListener {

    private JTextField oldX;
    private JTextField oldY;
    private JTextField newX;

    public int getNewX() {
        try {
            return Integer.parseInt(newX.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getNewY() {
        try {
            return Integer.parseInt(newY.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public Animal getCurrentCard() {
        return currentCard;
    }

    private JTextField newY;

    private JComboBox<Animal> animals;
    private Animal currentCard;

    public void setCurrentCard(Animal currentCard) {
        this.currentCard = currentCard;
    }

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
    @Deprecated
    public MoveAnimalDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        getAllAnimals();

        setPreferredSize(new Dimension(500,150));

        JLabel animal = new JLabel("Animal in the zoo: ");
        JLabel oldCord = new JLabel("Old Coordination's: ");
        JLabel newCord = new JLabel("New Coordination: ");

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(1,2));

        topPanel.add(animal);
        topPanel.add(animals);
        mainPanel.add(topPanel,BorderLayout.PAGE_START);

        oldX = new JTextField();
        oldX.setEditable(false);
        oldY = new JTextField();
        oldY.setEditable(false);
        newY = new JTextField();
        newX = new JTextField();

        JPanel centerBlock = new JPanel(new GridLayout(3,3));
        centerBlock.add(new Label(""));
        centerBlock.add(new Label("X",Label.CENTER));
        centerBlock.add(new Label("Y",Label.CENTER));
        centerBlock.add(oldCord);
        centerBlock.add(oldX);
        centerBlock.add(oldY);
        centerBlock.add(newCord);
        centerBlock.add(newX);
        centerBlock.add(newY);

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
                Point newPoint = new Point(getNewX(),getNewY());
                if (Point.checkBounderies(newPoint)) {
                    getCurrentCard().move(new Point(getNewX(), getNewY())); // moves animal to new location
                    dispose();
                } else
                    JOptionPane.showMessageDialog(getRootPane(),"Please chose valid location", "Animal movement Error" ,JOptionPane.ERROR_MESSAGE);
            }
        });
        buttons.add(accept,BorderLayout.LINE_END);

        add(buttons,BorderLayout.PAGE_END);

        // Sets the current animal to the relevant points
        if (ZooPanel.AnimalsInZoo.size() > 0) {
            Point location = ZooPanel.AnimalsInZoo.get(0).getLocation();
            oldX.setText(String.valueOf(location.getX()));
            oldY.setText(String.valueOf(location.getY()));
            setCurrentCard(ZooPanel.AnimalsInZoo.get(0));
        }

        pack();
        setVisible(true);
    }

    /**
     * Gets all the initiated animals in the zoo and displays them in the dropdown menu
     */
    private void getAllAnimals() {
        animals = new JComboBox<Animal>();
        for (int i = 0; i < ZooPanel.AnimalsInZoo.size(); i++) {
            animals.addItem(ZooPanel.AnimalsInZoo.get(i));
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
