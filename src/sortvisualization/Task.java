package sortvisualization;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nadav Lubman
 */

public class Task implements Runnable {
    protected int[] array;
    protected int arraySize;
    protected AtomicInteger currentRec = new AtomicInteger(-1);
    protected GraphicsDemo graphics;
    protected final Object lock;
    public boolean endProcess = false;
    public MutableBoolean isDone;


    public Task(GraphicsDemo graphics, AtomicInteger currentRec, Object lock, MutableBoolean isDone) {
        this.arraySize = graphics.arraySize;
        this.array = graphics.array;
        this.currentRec = currentRec;
        this.graphics = graphics;
        this.lock = lock;
        this.isDone = isDone;
    }

    public void run() {
 
    }
    
    public boolean isFinish(){
        for(int i=0 ; i<arraySize-1 ; i++){
            if(array[i]>array[i+1])
                return false;
        }
        return true;
    }
    
}
