package sortvisualization;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.Thread.sleep;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Nadav Lubman
 */

public class MyFrame extends JFrame implements ActionListener, KeyListener, Runnable {
    
        GraphicsDemo graphics ;  
        GraphicsDemo lastGraphics = null;
        int[] array;
        int arraySize;
        AtomicInteger currentRec = new AtomicInteger(-1);
        Object lock;
        JButton bubbleButton;
        JButton insertionButton;
        JButton selectionButton;
        JButton mergeButton;
        JButton quickButton;
        JButton heapButton;
        JButton resetButton;
        Task t;
  
        MutableBoolean isDone;
               
    public MyFrame(MutableBoolean isDone) {
        this.isDone = isDone;
    }
        @Override
    public void run() {   
        initVars();
        initAction();         
    }
    
    public void initVars(){

        graphics  = new GraphicsDemo();
        array     = graphics.array;
        arraySize = graphics.arraySize;
        lock      = new Object();
        t         = null;
        currentRec.set(-1);
    }
    
    public void initAction(){
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.white);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.addKeyListener(this);
        this.setContentPane(graphics);
        this.setLayout(null);

        createButtons();

        while(!graphics.isSortSelected.value){
            try {
                sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }     
        
        beginTask(t);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {

        JButton button = (JButton)ae.getSource();
        if(button == bubbleButton){          
            bubbleButton.setIcon(new ImageIcon("C:\\Asasha3\\SortVisualization\\buttons-pics\\bubble-on.png"));
            graphics.isSortSelected.turnTrue();
            t = new BubbleTask(graphics, currentRec, lock, isDone);  
            
        }
        else if(button == insertionButton){         
            insertionButton.setIcon(new ImageIcon("C:\\Asasha3\\SortVisualization\\buttons-pics\\insertion-on.png"));
            graphics.isSortSelected.turnTrue();    
            t = new InsertionTask(graphics, currentRec, lock, isDone);
        }
        else if(button == selectionButton){ 
            selectionButton.setIcon(new ImageIcon("C:\\Asasha3\\SortVisualization\\buttons-pics\\selection-on.png"));
            graphics.isSortSelected.turnTrue();
            t = new SelectionTask(graphics, currentRec, lock, isDone);
        }
        else if(button == mergeButton){  
            mergeButton.setIcon(new ImageIcon("C:\\Asasha3\\SortVisualization\\buttons-pics\\merge-on.png"));
            graphics.isSortSelected.turnTrue();   
            t = new MergeTask(graphics, currentRec, lock, isDone);
        }
        else if(button == quickButton){  
            quickButton.setIcon(new ImageIcon("C:\\Asasha3\\SortVisualization\\buttons-pics\\quick-on.png"));
            graphics.isSortSelected.turnTrue();   
            t = new QuickTask(graphics, currentRec, lock, isDone);
        }
        else if(button == heapButton){  
            heapButton.setIcon(new ImageIcon("C:\\Asasha3\\SortVisualization\\buttons-pics\\heap-on.png"));
            graphics.isSortSelected.turnTrue();    
            t = new HeapTask(graphics, currentRec, lock, isDone);
        }
        else if(button == resetButton){  
            resetButton.setIcon(new ImageIcon("C:\\Asasha3\\SortVisualization\\buttons-pics\\reset-on.png"));
            isDone.turnTrue();
            setVisible(false);       
        }
    }
    
    public void createButtons(){
        
          Icon iconBubble = new ImageIcon("C:\\Asasha3\\SortVisualization\\buttons-pics\\bubble-off.png");     
          bubbleButton = new JButton(iconBubble);
          bubbleButton.addActionListener(this);
          bubbleButton.setBounds(30, 80, 278,80);   
          bubbleButton.setAlignmentY(5);
          bubbleButton.setText("Bubble Sort");
          bubbleButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));


          bubbleButton.setToolTipText(
           "<html><center><h2 style='color: white; font-style: Times New Roman;'>Iterates throught the array with </h2>"
            +"<h2 style='color: white; font-style: Times New Roman;'>2 nested loops and swaps each </h2>"
            +"<h2 style='color: white; font-style: Times New Roman;'>two adjacent elements if needed</h2>"
            +"<h1 style='color: white; font-style: Times New Roman;'>Worst case runtime: O(N^2)</h1>"
            +"<b><br/></b></center>"
            +"<br/>");
          this.add(bubbleButton);
 
          
          Icon iconInsertion = new ImageIcon("C:\\Asasha3\\SortVisualization\\buttons-pics\\insertion-off.png");    
          insertionButton = new JButton(iconInsertion);
          insertionButton.addActionListener(this);
          insertionButton.setBackground(Color.BLACK);
          insertionButton.setBounds(30, 180, 276, 79);  
          insertionButton.setText("Insertion Sort");
          insertionButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
          insertionButton.setToolTipText(
           "<html><center><h2 style='color: white; font-style: Times New Roman;'>Iterates throught the array with </h2>"
            +"<h2 style='color: white; font-style: Times New Roman;'>2 nested loops( i , j ) and for each element i </h2>"
            +"<h2 style='color: white; font-style: Times New Roman;'>finds its proper location and swaps elements along the way</h2>"
            +"<h1 style='color: white; font-style: Times New Roman;'>Worst case runtime: O(N^2)</h1>"
            +"<b><br/></b></center>"
            +"<br/>");
          this.add(insertionButton);
      
          
          Icon iconSelection = new ImageIcon("C:\\Asasha3\\SortVisualization\\buttons-pics\\selection-off.png");
          selectionButton = new JButton(iconSelection);
          selectionButton.addActionListener(this);
          selectionButton.setBackground(Color.ORANGE);
          selectionButton.setBounds(30, 279, 276, 79);
          selectionButton.setText("Selection Sort");
          selectionButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
          selectionButton.setToolTipText(
            "<html><center><h2 style='color: white; font-style: Times New Roman;'>Iterates throught the array with </h2>"
            +"<h2 style='color: white; font-style: Times New Roman;'>2 nested loops( i , j ) and for each iteration </h2>"
            +"<h2 style='color: white; font-style: Times New Roman;'>finds the next minimal element and replaces it with i</h2>"
            +"<h1 style='color: white; font-style: Times New Roman;'>Worst case runtime: O(N^2)</h1>"
            +"<b><br/></b></center>"
            +"<br/>");
          this.add(selectionButton);
       
         
          Icon iconMerge = new ImageIcon("C:\\Asasha3\\SortVisualization\\buttons-pics\\merge-off.png");
          mergeButton = new JButton(iconMerge);
          mergeButton.addActionListener(this);
          mergeButton.setBackground(Color.MAGENTA);
          mergeButton.setBounds(30, 378, 278, 77);   
          mergeButton.setText("Merge Sort");         
          mergeButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
          mergeButton.setToolTipText(
           "<html><center><h2 style='color: white; font-style: Times New Roman;'>A recursive algorithm which </h2>"
            +"<h2 style='color: white; font-style: Times New Roman;'>recursively splits the array into two parts </h2>"
            +"<h2 style='color: white; font-style: Times New Roman;'>and sorts each part and then combines them</h2>"
            +"<h1 style='color: white; font-style: Times New Roman;'>Worst case runtime: O(N*Log(N))</h1>"
            +"<b><br/></b></center>"
            +"<br/>");
          this.add(mergeButton);
          
          
          Icon iconQuick = new ImageIcon("C:\\Asasha3\\SortVisualization\\buttons-pics\\quick-off.png");
          quickButton = new JButton(iconQuick);
          quickButton.addActionListener(this);
          quickButton.setBackground(Color.cyan);
          quickButton.setBounds(30, 475, 277, 78);   
          quickButton.setText("Quick Sort");         
          quickButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
          quickButton.setToolTipText(
            "<html><center><h2 style='color: white; font-style: Times New Roman;'>A recursive algorithm which </h2>"
            +"<h2 style='color: white; font-style: Times New Roman;'>uses a pivot and divides the array into two parts-</h2>"
            +"<h2 style='color: white; font-style: Times New Roman;'>elements which are smaller than pivot and elements</h2>"
                    +"<h2 style='color: white; font-style: Times New Roman;'>which are bigger</h2>"
                    +"<h1 style='color: white; font-style: Times New Roman;'>Worst case runtime: O(N^2)</h1>"
            +"<h1 style='color: white; font-style: Times New Roman;'>average case runtime: O(N*Log(N))</h1>"
            +"<b><br/></b></center>"
            +"<br/>");
          this.add(quickButton);
          
          
          Icon iconHeap = new ImageIcon("C:\\Asasha3\\SortVisualization\\buttons-pics\\heap-off.png");
          heapButton = new JButton(iconHeap);
          heapButton.addActionListener(this);
          heapButton.setBackground(Color.PINK);
          heapButton.setBounds(30, 573, 277, 78);   
          
          heapButton.setText("Heap Sort");         
          heapButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
          heapButton.setToolTipText(
            "<html><center><h2 style='color: white; font-style: Times New Roman;'>Uses a heap data structure</h2>"
            +"<h2 style='color: white; font-style: Times New Roman;'>to sort the array</h2>"
            +"<h1 style='color: white; font-style: Times New Roman;'>Worst case runtime: O(N*Log(N))</h1>"
            +"<b><br/></b></center>"
            +"<br/>");
          this.add(heapButton);
          
        
          Icon iconReset = new ImageIcon("C:\\Asasha3\\SortVisualization\\buttons-pics\\reset-off.png");
          resetButton = new JButton(iconReset);
          resetButton.addActionListener(this);
          resetButton.setBackground(Color.PINK);
          resetButton.setBounds(30, 671, 277, 78);   
          
          resetButton.setText("Heap Sort");         
          resetButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
          resetButton.setToolTipText(
            "<html><center>"   
            +"<h1 style='color: white; font-style: Times New Roman;'>Press to reset</h1>"
            +"<b><br/></b></center>"
            +"<br/>");
          this.add(resetButton);
    }
    
    public void beginTask(Task t){
        
        Thread sortThread = new Thread(t);
        sortThread.start();

        while(!isDone.value){
            try {
                Thread.sleep(15);  
            } catch (InterruptedException ex) {        
                System.out.println("error");
            }           
            synchronized(lock){            
                this.setContentPane(graphics);
                this.requestFocus();           
                this.revalidate();
            lock.notify();
           }   
       }
            this.setVisible(false);
    }
  
    @Override
    public void keyTyped(KeyEvent ke) {
     
    }

    @Override
    public void keyReleased(KeyEvent ke) {     
        // resets the screen
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {     
            isDone.turnTrue();       
        }
    }
    

    @Override
    public void keyPressed(KeyEvent ke) {
        
    }
}
    

