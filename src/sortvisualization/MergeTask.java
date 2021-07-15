package sortvisualization;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Nadav Lubman
 */
public class MergeTask extends Task {

   public MergeTask(GraphicsDemo graphics, AtomicInteger currentRec, Object lock, MutableBoolean isDone){
      
    super(graphics,currentRec, lock, isDone);
   }

    @Override
    public void run() {
      sort(array, 0, arraySize-1);      
    }
    
void merge(int arr[], int l, int m, int r){

        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int[n1];
        int R[] = new int[n2];
 
        for (int i = 0; i < n1; ++i){
            L[i] = arr[l + i];
            graphics.numOfActions++;
        }
        for (int j = 0; j < n2; ++j){
            R[j] = arr[m + 1 + j];
            graphics.numOfActions++;
        }
 
        int i = 0, j = 0;
 
        int k = l;
        while (i < n1 && j < n2) {

            graphics.numOfActions+=2;
            if (L[i] <= R[j]) {
                 try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    Logger.getLogger(InsertionTask.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                 synchronized(lock){
                    arr[k] = L[i];
                    i++;
                    graphics.currentRec.set(k);
                    lock.notify();  
                 }
            }
            
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            graphics.numOfActions++;
            arr[k] = L[i];
            i++;
            k++;
        }
 
        while (j < n2) {
            graphics.numOfActions++;
            arr[k] = R[j];
            j++;
            k++;
        }
    }
 
    void sort(int arr[], int l, int r)
    {
        graphics.pivot = r;
        graphics.numOfActions++;
        if (l < r) {
            
            int m =l+ (r-l)/2;

            sort(arr, l, m);        
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }
 

}



      
    
    

