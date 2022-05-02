package graphics;

import animals.Animal;

import javax.swing.*;
import java.awt.*;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 02/05/2022
 */
public class AnimalData extends JDialog {

    private String[][] data;
    private int eatCounter;

    public int getEatCounter() {
        return eatCounter;
    }

    public void setEatCounter(int count) {
        this.eatCounter = count;
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
    public AnimalData(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        data = new String[ZooPanel.AnimalsInZoo.size() + 1][6];
        setEatCounter(0);
        for (int i = 0; i < data.length - 1; i++) {
            Animal animal = ZooPanel.AnimalsInZoo.get(i);
            data[i] = new String[]{animal.getName(),animal.getColor(), Double.toString(animal.getWeight()),Integer.toString(animal.getHorSpeed()),Integer.toString(animal.getVerSpeed()),Integer.toString(animal.getEatCount())};
            setEatCounter(getEatCounter() + animal.getEatCount());
        }
        data[data.length - 1] = new String[]{"Total eats:",null,null,null,null,Integer.toString(getEatCounter())};
        String column[] = {"Animal","Color","Weight","Hor.speed","Ver.speed","Eat counter"};

        JTable jt = new JTable(data,column);
        jt.setBounds(30,40,400,300);
        JScrollPane sp = new JScrollPane(jt);
        add(sp);
        setSize(500,400);
        setVisible(true);
    }
}
