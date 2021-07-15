package sortvisualization;

/**
 *
 * @author Nadav Lubman
 */

public class MutableBoolean {
    
    public boolean value;
    
    public MutableBoolean(boolean b){
        this.value = b;
    }
    public void turnTrue(){
        this.value = true;
    }
    public void turnFalse(){
        this.value = false;
    }
}
