package sortvisualization;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortVisualization{

    static MutableBoolean isDone = new MutableBoolean(false);
    
    public static void main(String[] args) {

        while(true){
            
            isDone = new MutableBoolean(false);
            MyFrame myFrame = new MyFrame(isDone);
            myFrame.addKeyListener(myFrame);
            Thread mainThread = new Thread(myFrame);
            mainThread.start();
      
            while(!isDone.value){
                try {
                    sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SortVisualization.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }             
    }
}
