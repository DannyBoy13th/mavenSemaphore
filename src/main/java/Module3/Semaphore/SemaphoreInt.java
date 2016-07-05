package Module3.Semaphore;

/**
 * Created by Daniel Solo on 15.06.2016.
 */
public interface SemaphoreInt {

    public void acquire() throws InterruptedException;

    public void acquire(int permits) throws InterruptedException;

    public void release();

    public void release(int permits);

    public int getAvailablePermits();
}
