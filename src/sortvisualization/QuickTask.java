package sortvisualization;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nadav Lubman
 */

public class QuickTask extends Task {

   public QuickTask(GraphicsDemo graphics, AtomicInteger currentRec, Object lock, MutableBoolean isDone){
      
    super(graphics,currentRec, lock, isDone);
       
   }
    
    @Override
    public void run() {
        sort(array, 0, arraySize-1);
    }
    
    public int partition(int arr[], int low, int high) 
    { 
        int pivot = arr[high]; 
        graphics.pivot = high;
        int i = (low-1); 
        for (int j=low; j<high; j++) {   
            graphics.numOfActions++;
            graphics.currentRec.set(i);

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(QuickTask.class.getName()).log(Level.SEVERE, null, ex);
            }
                graphics.numOfActions++;
                if (arr[j] <= pivot){ 
                    try {
                        Thread.sleep(35);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(QuickTask.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    i++; 
                    int temp = arr[i]; 
                    arr[i] = arr[j];
                    arr[j] = temp; 
                } 
         } 
            int temp = arr[i+1]; 
            arr[i+1] = arr[high]; 
            arr[high] = temp; 
            return i+1; 
    } 

    void sort(int arr[], int low, int high){           
            graphics.numOfActions++;
            if (low < high){ 
                    int pi = partition(arr, low, high); 
                    sort(arr, low, pi-1); 
                    sort(arr, pi+1, high); 
            } 
    } 



}


    

