package sortvisualization;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nadav Lubman
 */

public class HeapTask extends Task {

   public HeapTask(GraphicsDemo graphics, AtomicInteger currentRec, Object lock, MutableBoolean isDone){
      
    super(graphics,currentRec, lock, isDone);
       
   }
    
    @Override
    public void run() {
        sort(array);    
    }
	public void sort(int arr[]){
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0 && !isDone.value  ; i--){
            heapify(arr, n, i);
            graphics.numOfActions++;
        }

        for (int i = n - 1; i > 0 && !isDone.value ; i--) {
            graphics.numOfActions++;
            
            try {
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;
                
                graphics.currentRec.set(i);
                Thread.sleep(50);
                
                // call max heapify on the reduced heap
                heapify(arr, i, 0);
            } catch (InterruptedException ex) {
                Logger.getLogger(HeapTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void heapify(int arr[], int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        if (l < n && arr[l] > arr[largest]){
            largest = l;
        }
          graphics.numOfActions++;

        if (r < n && arr[r] > arr[largest]){
            largest = r;          
        }
        graphics.numOfActions++;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }
}
    

      
    
    

