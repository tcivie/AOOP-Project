package graphics;

import animals.*;
import mobility.Point;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 18/04/2022
 */
public class AddAnimalDialog extends JDialog implements ItemListener, DocumentListener {

    private JPanel panel;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    // BASIC Animal params
    private JLabel image;

    public JPanel getPanel() {
        return panel;
    }

    public String getAnimalName() {
        return name.getText();
    }

    public int getX_cord() {
        try {
            return Integer.parseInt(x_cord.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getY_cord() {
        try {
            return Integer.parseInt(y_cord.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getAnimalSize() {
        try {
            return Integer.parseInt(size.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getV_speed() {
        try {
            return Integer.parseInt(v_speed.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getH_speed() {
        try {
            return Integer.parseInt(h_speed.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getColor() {
        return (String) color.getSelectedItem();
    }

    public double getWeight() {
        try {
            return Double.parseDouble(weight.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getAdditionalParamField() {
        return additionalParamField.getText();
    }

    public String getCurrentCard() {
        return currentCard;
    }

    private JTextField name;
    private JTextField x_cord;
    private JTextField y_cord;
    private JTextField size;
    private JTextField v_speed;
    private JTextField h_speed;
    private JComboBox<String> color;
    private JTextField weight;

    // ADDITIONAL params
    private JLabel additionalParam;
    private JTextField additionalParamField;

    private String currentCard;

    private static final int PARAMS = 8;

    private ZooPanel zooPanel;


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
     * @param zooPanel
     * @param owner    the {@code Frame} from which the dialog is displayed
     * @param title    the {@code String} to display in the dialog's
     *                 title bar
     * @param modal    specifies whether dialog blocks user input to other top-level
     *                 windows when shown. If {@code true}, the modality type property is set to
     *                 {@code DEFAULT_MODALITY_TYPE} otherwise the dialog is modeless
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
    public AddAnimalDialog(ZooPanel zooPanel, Frame owner, String title, boolean modal, String[] animalTypes) {
        super(owner, title, modal);
        setSize(500,460);
        this.zooPanel = zooPanel;
        JPanel comboBoxPane = new JPanel();
        String[] comboBoxItems = animalTypes;
        JComboBox<String> cb = new JComboBox<>(comboBoxItems);
        cb.addItemListener(this);
        cb.setEditable(false);
        comboBoxPane.add(cb);

        initParams(animalTypes);
        add(comboBoxPane, BorderLayout.PAGE_START);
        cb.setSelectedIndex(0);
        setVisible(true);
    }

    /**
     * Creates the basic view of the animal creation
     */
    private void initParams(String[] animalTypes) {
        name = new JTextField();
        x_cord = new JTextField();
        y_cord = new JTextField();
        size = new JTextField();
        size.getDocument().addDocumentListener(this);
        v_speed = new JTextField();
        h_speed = new JTextField();
        String[] animalColors = {"Normal","Red","Blue"};
        color = new JComboBox<>(animalColors);
        weight = new JTextField();
        weight.setEditable(false);

        JPanel bigOne = new JPanel(new BorderLayout()); // create a big panel

        // add the animal creation parameters to the top
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

        // Add the middle optional animal parameters
        JPanel additional = new JPanel(new GridLayout(1,2));
        additional.setBorder(BorderFactory.createTitledBorder("Additional Parameters"));
        currentCard = animalTypes[0]; // Set default card
        additionalParam = new JLabel();
        switch (animalTypes[0]) {
            case "Bear" -> additionalParam = new JLabel("Fur Color");
            case "Elephant" -> additionalParam = new JLabel("Trunk Length");
            case "Giraffe" -> additionalParam = new JLabel("Neck Length");
            case "Lion" -> additionalParam = new JLabel("");
            case "Turtle" -> additionalParam = new JLabel("Age");
        }
        additionalParamField = new JTextField();
        additional.add(additionalParam);
        additional.add(additionalParamField);
        bigOne.add(additional,BorderLayout.CENTER);

        // Add the calculated values
        JPanel uneditable = new JPanel(new GridLayout(1,2));
        uneditable.setBorder(BorderFactory.createTitledBorder("Uneditable"));
        uneditable.add(new JLabel("Weight:"));
        uneditable.add(weight);
        bigOne.add(uneditable,BorderLayout.PAGE_END);

        add(bigOne,BorderLayout.CENTER); // Adds the parameters to the window

        JPanel buttons = new JPanel(new BorderLayout());
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            dispose(); // close window
        });
        buttons.add(cancelButton,BorderLayout.LINE_START);
        JButton acceptButton = new JButton("Accept");
        acceptButton.addActionListener(e -> {

            // Get all elements
            String name = getAnimalName();
            int x_cord = getX_cord();
            int y_cord = getY_cord();
            int size = getAnimalSize();
            int v_speed = getV_speed();
            int h_speed = getH_speed();
            String color = getColor();
            double weight = getWeight();
            String addParam = getAdditionalParamField();

            // Test the data
            if (checkName(name) && checkXCoordinates(x_cord) && checkYCoordinates(y_cord) && checkSize(size) && checkVerSpeed(v_speed) && checkHorSpeed(h_speed) && checkColor(color)) {
                boolean isSuccess = false;
                try {
                    switch(getCurrentCard()) {
                        case "Bear" -> isSuccess = this.zooPanel.addAnimalToZoo(new Bear(name,x_cord,y_cord,size,color,h_speed,v_speed,weight,addParam));
                        case "Elephant" -> isSuccess = this.zooPanel.addAnimalToZoo(new Elephant(name,x_cord,y_cord,size,color,h_speed,v_speed,weight,Double.parseDouble(addParam)));
                        case "Giraffe" -> isSuccess = this.zooPanel.addAnimalToZoo(new Giraffe(name,x_cord,y_cord,size,color,h_speed,v_speed,weight,Double.parseDouble(addParam)));
                        case "Lion" -> isSuccess = this.zooPanel.addAnimalToZoo(new Lion(name,x_cord,y_cord,size,color,h_speed,v_speed,weight));
                        case "Turtle" -> isSuccess = this.zooPanel.addAnimalToZoo(new Turtle(name,x_cord,y_cord,size,color,h_speed,v_speed,weight,Integer.parseInt(addParam)));
                    }
                    if (isSuccess) {
                        //Successfully created animal
                        JOptionPane.showMessageDialog(AddAnimalDialog.super.getFocusOwner(), name + " Has been added to the zoo, his ID is: " + ZooPanel.AnimalsInZoo.size(), "Animal creation", JOptionPane.INFORMATION_MESSAGE);
                    }
                    dispose();
                } catch (IOException ex) { // If image path is not right throw exception
                    ex.printStackTrace();
                }
            }
            dispose();
        });
        buttons.add(acceptButton,BorderLayout.LINE_END);
        add(buttons,BorderLayout.PAGE_END);



        // Init image for bear
//        image = new JLabel("",createImage(Bear.getPATH()),JLabel.CENTER);
        switch (animalTypes[0]) {
            case "Bear" -> image = new JLabel("",createImage(Animal.getPicturePath("bea")),JLabel.CENTER);
            case "Elephant" -> image = new JLabel("",createImage(Animal.getPicturePath("elf")),JLabel.CENTER);
            case "Giraffe" -> image = new JLabel("",createImage(Animal.getPicturePath("grf")),JLabel.CENTER);
            case "Lion" -> image = new JLabel("",createImage(Animal.getPicturePath("lio")),JLabel.CENTER);
            case "Turtle" -> image = new JLabel("",createImage(Animal.getPicturePath("trt")),JLabel.CENTER);
        }

        add(image,BorderLayout.LINE_START);
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
        currentCard = (String) e.getItem();
        additionalParamField.setVisible(true);
        switch ((String) e.getItem()) {
            case "Bear" -> {
                image.setIcon(createImage(Animal.getPicturePath("bea")));
                additionalParam.setText("Fur Color");
            }
            case "Elephant" -> {
                image.setIcon(createImage(Animal.getPicturePath("elf")));
                additionalParam.setText("Trunk Length");
            }
            case "Giraffe" -> {
                image.setIcon(createImage(Animal.getPicturePath("grf")));
                additionalParam.setText("Neck Length");
            }
            case "Lion" -> {
                image.setIcon(createImage(Animal.getPicturePath("lio")));
                additionalParam.setText("");
                additionalParamField.setVisible(false);
            }
            case "Turtle" -> {
                image.setIcon(createImage(Animal.getPicturePath("trt")));
                additionalParam.setText("Set Age");
            }
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

    /**
     * Checks if the name has numbers
     * @param name
     * @return True if passes the test / False otherwise
     */
    private boolean checkName(String name) {
        if (name.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(this,"Please name should include only letters", "Animal creation Error" ,JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Checks if the x coordinates matches
     * @param x
     * @return True if passes the test / False otherwise
     */
    private boolean checkXCoordinates(int x) {
        if (x < Point.MIN_X || x > Point.MAX_X) {
            JOptionPane.showMessageDialog(this,"The x coordinates should be between " + Point.MIN_X + " and " + Point.MAX_X, "Animal creation Error" ,JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Checks if the y coordinates matches
     * @param y
     * @return True if passes the test / False otherwise
     */
    private boolean checkYCoordinates(int y) {
        if (y < Point.MIN_Y || y > Point.MAX_Y) {
            JOptionPane.showMessageDialog(this,"The y coordinates should be between " + Point.MIN_Y + " and " + Point.MAX_Y, "Animal creation Error" ,JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Checks if the size matches
     * @param size
     * @return True if passes the test / False otherwise
     */
    private boolean checkSize(double size) {
        if (size < 50 || size > 300) {
            JOptionPane.showMessageDialog(this,"Please input size between 50 and 300!", "Animal creation Error" ,JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Checks if the speed matches
     * @param speed
     * @return True if passes the test / False otherwise
     */
    private boolean checkHorSpeed(double speed) {
        if (speed < 1 || speed > 10) {
            JOptionPane.showMessageDialog(this,"Please input Horizontal Speed between 1 and 10!", "Animal creation Error" ,JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Checks if the speed matches
     * @param speed
     * @return True if passes the test / False otherwise
     */
    private boolean checkVerSpeed(double speed) {
        if (speed < 1 || speed > 10) {
            JOptionPane.showMessageDialog(this,"Please input Vertical Speed between 1 and 10!", "Animal creation Error" ,JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Checks if the color matches
     * @param color
     * @return True if passes the test / False otherwise
     */
    private boolean checkColor(String color) {
        color = color.toUpperCase();
        for (EColors c : EColors.values()) {
            if (c.name().equals(color)) {
                return true;
            }
        }
        JOptionPane.showMessageDialog(this,"Please enter one of the following colors: " + Arrays.toString(EColors.values()), "Animal creation Error" ,JOptionPane.ERROR_MESSAGE);
        return false;
    }

    private boolean checkNumOfAnimalsInZoo() {
        if (ZooPanel.AnimalsInZoo.size() < ZooPanel.MAX_ANIMALS)
            return true;
        JOptionPane.showMessageDialog(this,"You cannot add more than " + ZooPanel.MAX_ANIMALS + " animals to the zoo", "Animal creation Error" ,JOptionPane.ERROR_MESSAGE);
        return false;
    }

    /**
     * Gives notification that there was an insert into the document.  The
     * range given by the DocumentEvent bounds the freshly inserted region.
     *
     * @param e the document event
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        int getSize = getAnimalSize();
        switch (currentCard) {
            case "Bear" -> weight.setText(df.format(1.5 * getSize));
            case "Elephant" -> weight.setText(df.format(10L * getSize));
            case "Giraffe" -> weight.setText(df.format(2.2 * getSize));
            case "Lion" -> weight.setText(df.format(0.8 * getSize));
            case "Turtle" -> weight.setText(df.format(0.5 * getSize));
        }
    }

    /**
     * Gives notification that a portion of the document has been
     * removed.  The range is given in terms of what the view last
     * saw (that is, before updating sticky positions).
     *
     * @param e the document event
     */
    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    /**
     * Gives notification that an attribute or set of attributes changed.
     *
     * @param e the document event
     */
    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
