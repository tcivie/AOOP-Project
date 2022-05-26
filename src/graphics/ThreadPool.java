package graphics;

import java.util.concurrent.*;

/**
 * @author glebtcivie
 * @Date 25/05/2022
 */
public class ThreadPool {
    private static final int MAX_CONCURRENT_THREADS = 10;
    private static final int MAX_WAIT_QUEUE = 5;
    private final BlockingQueue<Runnable> linkedBlockingDeque;
    private ExecutorService executorService;

    public ThreadPool(BlockingQueue<Runnable> linkedBlockingDeque) {
        this.linkedBlockingDeque = new LinkedBlockingDeque<Runnable>(MAX_WAIT_QUEUE);
        this.executorService = new ThreadPoolExecutor(1, MAX_CONCURRENT_THREADS, 30, TimeUnit.SECONDS, linkedBlockingDeque, new RejectionHandler(MAX_CONCURRENT_THREADS,MAX_WAIT_QUEUE));
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
            return false;
        }
    }

    /**
     * Kills all the running threads now
     */
    public void killAll() {
        this.executorService.shutdown();
    }
}
