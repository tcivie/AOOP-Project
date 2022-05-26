package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author glebtcivie
 * @Date 26/05/2022
 */

/**
 * Handles rejections of max wait in the threads in the ThreadPool.java class
 */
class RejectionHandler extends Component implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        JOptionPane.showMessageDialog(this,"You cannot add more than " + ZooPanel.MAX_ANIMALS + " animals to the zoo", "Animal creation Error" ,JOptionPane.ERROR_MESSAGE);
    }
}
