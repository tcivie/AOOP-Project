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

    private final int maxConcurrentThreads;
    private final int maxWaitQueue;
    public RejectionHandler(int maxConcurrentThreads, int maxWaitQueue) {
        this.maxConcurrentThreads = maxConcurrentThreads;
        this.maxWaitQueue = maxWaitQueue;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        JOptionPane.showMessageDialog(this,"You cannot add more than " + maxConcurrentThreads + " animals to the zoo and " + maxWaitQueue, "Animal creation Error" ,JOptionPane.ERROR_MESSAGE);
    }
}
