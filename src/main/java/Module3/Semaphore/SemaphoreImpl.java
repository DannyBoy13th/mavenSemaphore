package Module3.Semaphore;

/**
 * Created by Daniel Solo on 15.06.2016.
 */
public class SemaphoreImpl implements SemaphoreInt {

    private int maxPermits;
    private final Object lock = new Object();
    private int permits;

    public SemaphoreImpl (int permits){
        this.permits = permits;
    }



    @Override
    public void acquire() throws InterruptedException {
        synchronized (lock) {
            if (permits > 0) {
                permits--;
            } else {
                lock.wait();
            }
        }
    }

    @Override
    public void acquire(int permits) throws InterruptedException {
        synchronized (lock){
            if (this.permits - permits>0){
                this.permits-=permits;
            }else {
                if (this.permits>0){
                    lock.notify();
                }
            }lock.wait();
        }

    }

    @Override
    public void release() {
        synchronized (lock){
            permits++;
            lock.notify();
        }
    }

    @Override
    public void release(int permits) {
        synchronized (lock){

            this.permits = ((this.permits + permits)<maxPermits) ? this.permits : maxPermits;
            lock.notify();
        }
    }

    @Override
    public int getAvailablePermits() {
        synchronized (lock){

            return permits;
        }
    }

    public static void main(String[] args) {
        SemaphoreImpl semaphore = new SemaphoreImpl(50);
        synchronized (semaphore){
            int permits  = semaphore.getAvailablePermits();
            semaphore.release(15-permits);
            System.out.println("Process finished without errors.");
        }
    }
}
