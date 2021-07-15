package sortvisualization;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nadav Lubman
 */

public class InsertionTask extends Task {

   public InsertionTask(GraphicsDemo graphics, AtomicInteger currentRec, Object lock, MutableBoolean isDone){
       super(graphics,currentRec, lock, isDone);     
   }
    
    @Override
    public void run() {

        for (int i = 1; i < arraySize  && !isDone.value  ; ++i) {
            int key = array[i];
            int j = i - 1;
            graphics.pivot = i;

            while (j >= 0 && array[j] > key && !isDone.value ) {
                graphics.numOfActions+=2;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(InsertionTask.class.getName()).log(Level.SEVERE, null, ex);
                }
                synchronized(lock){
                    array[j + 1] = array[j];
                    j = j - 1;
                    graphics.currentRec.set(j);
                       lock.notify();
                  
                  }
            }        
            array[j + 1] = key;        
        }
      }    
    }
    

