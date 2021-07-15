package sortvisualization;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nadav Lubman
 */

public class BubbleTask extends Task {

   public BubbleTask(GraphicsDemo graphics, AtomicInteger currentRec, Object lock, MutableBoolean isDone){    
         super(graphics,currentRec, lock, isDone);
   }
    
    @Override
    public void run() {      
        for (int i = 0; i < arraySize; i++){            
            graphics.pivot = arraySize-i-1;
            
            for (int j = 0; j < arraySize-1 && !isFinish(); j++){
                if (array[j] > array[j+1]){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BubbleTask.class.getName()).log(Level.SEVERE, null, ex);
                    }       
                    synchronized(lock){ 
                        int tmp = array[j];
                        array[j] = array[j+1];
                        array[j+1] = tmp;
                        graphics.currentRec.set(j);
                        lock.notify();                 
                    }
                }   
                graphics.numOfActions++;
            }
        }         
    }
}
      
    
    

