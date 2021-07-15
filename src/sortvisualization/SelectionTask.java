package sortvisualization;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nadav Lubman
 */

public class SelectionTask extends Task {

   public SelectionTask(GraphicsDemo graphics, AtomicInteger currentRec, Object lock, MutableBoolean isDone){
      
    super(graphics,currentRec, lock, isDone);
       
   }

    @Override
    public void run() {

            for (int i = 0; i < arraySize-1; i++)
            {   
                graphics.pivot = i;
                int min_idx = i;
                for (int j = i+1; j < arraySize; j++){
                    
                    graphics.currentRec.set(j);
                    graphics.numOfActions++;
                        if (array[j] < array[min_idx]){
                            try {
                                Thread.sleep(75);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(InsertionTask.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            synchronized(lock){            
                            min_idx = j;

                            lock.notify();
                            }           
                   }                       
                }           
                int temp = array[min_idx];
                array[min_idx] = array[i];
                array[i] = temp;
            }
        }  
    }
    

