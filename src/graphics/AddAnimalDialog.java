package graphics;

import animals.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author glebtcivie
 * @Date 18/04/2022
 */
public class AddAnimalDialog extends JDialog implements KeyListener, ItemListener {

    private JPanel panel;

    // BASIC Animal params
    private JLabel image;
    private JTextField name;
    private JTextField x_cord;
    private JTextField y_cord;
    private JTextField size;
    private JTextField v_speed;
    private JTextField h_speed;
    private JTextField color;
    private JTextField weight;

    // ADDITIONAL params
    private JLabel additionalParam;
    private JTextField additionalParamField;

    private String currentCard;
    private int getWeight;

    private StringBuilder sb;

    private static final int PARAMS = 8;


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

        sb = new StringBuilder();

        JPanel comboBoxPane = new JPanel();
        String[] comboBoxItems = {"Bear","Elephant","Giraffe","Lion","Turtle"};
        JComboBox<String> cb = new JComboBox<String>(comboBoxItems);
        cb.addItemListener(this);
        cb.setEditable(false);
        comboBoxPane.add(cb);

        initParams();
        add(comboBoxPane, BorderLayout.PAGE_START);
        setVisible(true);
    }

    private void initParams() {
        name = new JTextField();
        x_cord = new JTextField();
        y_cord = new JTextField();
        size = new JTextField();
        v_speed = new JTextField();
        h_speed = new JTextField();
        color = new JTextField();
        weight = new JTextField();
        weight.setEditable(false);

        JPanel bigOne = new JPanel(new BorderLayout());

        JPanel params = new JPanel(new GridLayout(9,2));
        params.setBorder(BorderFactory.createTitledBorder("Parameters"));
        params.add(new JLabel("Name:"));
        params.add(name);
        params.add(new JLabel("X Coordinate:"));
        params.add(x_cord);
        params.add(new JLabel("Y Coordinate:"));
        params.add(y_cord);
        params.add(new JLabel("Size:"));
        params.add(size);
        params.add(new JLabel("Vertical speed:"));
        params.add(v_speed);
        params.add(new JLabel("Horizontal speed:"));
        params.add(h_speed);
        params.add(new JLabel("Color:"));
        params.add(color);
        bigOne.add(params,BorderLayout.PAGE_START);

        JPanel additional = new JPanel(new GridLayout(1,2));
        additional.setBorder(BorderFactory.createTitledBorder("Additional Parameters"));
        additionalParam = new JLabel("Fur Color");
        additionalParamField = new JTextField();
        additional.add(additionalParam);
        additional.add(additionalParamField);
        bigOne.add(additional,BorderLayout.CENTER);

        JPanel uneditable = new JPanel(new GridLayout(1,2));
        uneditable.setBorder(BorderFactory.createTitledBorder("Uneditable"));
        uneditable.add(new JLabel("Weight:"));
        uneditable.add(weight);
        bigOne.add(uneditable,BorderLayout.PAGE_END);


        add(bigOne,BorderLayout.CENTER);

        // Init image for bear
        image = new JLabel("",createImage(Bear.getPATH()),JLabel.CENTER);
        add(image,BorderLayout.LINE_START);
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Delete non numbers
        char c = e.getKeyChar();
        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE))
            e.consume();

        // display the weight
        sb.append(e.getKeyChar());
        getWeight = Integer.parseInt(sb.toString());
        switch (currentCard) {
            case "Bear" -> weight.setText(Double.toString(1.5 * getWeight));
            case "Elephant" -> weight.setText(Double.toString(10 * getWeight));
            case "Giraffe" -> weight.setText(Double.toString(2.2 * getWeight));
            case "Lion" -> weight.setText(Double.toString(0.8 * getWeight));
            case "Turtle" -> weight.setText(Double.toString(0.5 * getWeight));
        }
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

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

        switch ((String)e.getItem()) {
            case "Bear":
                image.setIcon(createImage(Elephant.getPATH()));

                break;
            case "Elephant":
                image.setIcon(createImage(Elephant.getPATH()));
                break;
            case "Giraffe":
                image.setIcon(createImage(Giraffe.getPATH()));
                break;
            case "Lion":
                image.setIcon(createImage(Lion.getPATH()));
                break;
            case "Turtle":
                image.setIcon(createImage(Turtle.getPATH()));
                break;
        }
    }

    private ImageIcon createImage(String icon) {
        try { // resize the image
            BufferedImage img = ImageIO.read(new File(icon));
            Image dimg = img.getScaledInstance(250, 250,
                    Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
