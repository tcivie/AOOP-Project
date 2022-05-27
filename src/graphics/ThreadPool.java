package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;

/**
 * @author glebtcivie
 * @Date 25/05/2022
 */
public class ThreadPool extends Component {
    private int maxConcurrentThreads;
    private int maxWaitQueue;
    private volatile BlockingQueue<Runnable> linkedBlockingDeque;
    private ExecutorService executorService;

    public ThreadPool(int maxConcurrentThreads, int maxWaitQueue) {
        this.maxConcurrentThreads = maxConcurrentThreads;
        this.maxWaitQueue = maxWaitQueue;
        this.linkedBlockingDeque = new LinkedBlockingDeque<Runnable>(maxWaitQueue);
        this.executorService = new ThreadPoolExecutor(maxConcurrentThreads, maxConcurrentThreads, 30, TimeUnit.SECONDS, linkedBlockingDeque);
    }

    /**
     * Adds new object to the thread pool
     * if there was and error (The blocking queue has reached max) then the object is not added to the pool
     * @param object The runnable object to add to the pool
     * @return True if the object is added, False otherwise
     */
    public boolean addToThreadPool(Runnable object) {
        try {
            this.executorService.execute(object);
            return true;
        } catch (RejectedExecutionException e) { // If you cannot add the animal to the zoo
            JOptionPane.showMessageDialog(this,"You cannot add more than " + maxConcurrentThreads + " animals to the zoo and " + maxWaitQueue, "Animal creation Error" ,JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Kills all the running threads now
     */
    public void killAll() {
        this.executorService.shutdown();
    }

    public boolean checkIfObjectBlocked(Object object) {
        return this.linkedBlockingDeque.contains(object);
    }

//    /**
//     * Tries to pull new animals from the queue and add them to the pool
//     */
//    public void pollQueue() {
//        this.executorService.submit( () -> {
//            try {
//                this.linkedBlockingDeque.take();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
}
