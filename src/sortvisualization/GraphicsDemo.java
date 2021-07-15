/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortvisualization;

import java.awt.*;
import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author nadav
 */

public class GraphicsDemo extends JPanel {
    
    public static int arraySize = 75;
    public int[] array = createArray();
    public AtomicInteger currentRec = new AtomicInteger(-1);
    public int pivot = -1;
    public int numOfActions = 0;
    public MutableBoolean isSortSelected = new MutableBoolean(false);
  
    private int counter=1;
    private final int Y_CORD = 745;
    private final int X_CORD = 350;
    private final int width = 5;
    private final Color endingColor = new Color(0,255,200);
    private JLabel textLabel = new JLabel(); 
    private JLabel countLabel = new JLabel();
    private Color changingColor = new Color(255,255,0);

    
    public GraphicsDemo(){
        
        repaint();
    }
    
    public void drawActions(Graphics g){
        
        remove(textLabel);
        remove(countLabel);
        textLabel.setVisible(false);
        countLabel.setVisible(false);

        textLabel = new JLabel("Number of comparisons : ");
        textLabel.setForeground(Color.black);
        textLabel.setFont(new Font("Verdana",50,50));
        textLabel.setBounds(30, 850, 800, 100);
        this.add(textLabel);

        if(numOfActions<3825) changingColor = new Color(numOfActions/15,255-numOfActions/15,0);
        countLabel = new JLabel(""+numOfActions);
        countLabel.setForeground(changingColor);
        countLabel.setFont(new Font("Verdana",50,50));
        countLabel.setBounds(700, 850, 800, 100);
        this.add(countLabel);
        countLabel.setVisible(true);
    }
    
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        this.setBackground(Color.white);     
        synchronized(currentRec){
           drawRects(g2d);
           drawActions(g);
         }
    }
    
    public void drawRects(Graphics2D g2d){
  
        if(!isFinish()){
            
            for(int i=0 ; i<arraySize ; i++){
                g2d.setColor(Color.BLUE);
                g2d.setStroke(new BasicStroke(10)); 
                g2d.drawLine(X_CORD+20*i, Y_CORD, X_CORD+20*i, Y_CORD-array[i]);

                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(5)); 
                g2d.drawLine(X_CORD+width+20*i, Y_CORD, X_CORD+width+20*i, Y_CORD-array[i]);

            synchronized(this){
                
                if(currentRec.intValue()!=-1){              
                    g2d.setColor(Color.RED);
                    g2d.setStroke(new BasicStroke(10)); 
                    g2d.drawLine(X_CORD+20*currentRec.intValue(), Y_CORD, X_CORD+20*currentRec.intValue(), Y_CORD-array[currentRec.intValue()]);

                    g2d.setColor(Color.BLACK);
                    g2d.setStroke(new BasicStroke(5));                       
                    g2d.drawLine(X_CORD+width+20*currentRec.intValue(), Y_CORD, X_CORD+width+20*currentRec.intValue(), Y_CORD-array[currentRec.intValue()]);                           
                }   
            
            if(pivot!=-1){          
                g2d.setColor(Color.green);
                g2d.setStroke(new BasicStroke(10)); 
                g2d.drawLine(X_CORD+20*pivot, Y_CORD, X_CORD+20*pivot, Y_CORD-array[pivot]);

                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(5)); 
                g2d.drawLine(X_CORD+width+20*pivot, Y_CORD, X_CORD+width+20*pivot, Y_CORD-array[pivot]);
            }
       
             }
          }
        }  
        
         else{
            synchronized(this){
                try {
                    Thread.sleep(7);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GraphicsDemo.class.getName()).log(Level.SEVERE, null, ex);
                }
             for(int i=0 ; i<counter ; i++){
                 
                g2d.setColor(endingColor);
                g2d.setStroke(new BasicStroke(10)); 
                g2d.drawLine(X_CORD+20*i, Y_CORD, X_CORD+20*i, Y_CORD-array[i]);

                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(5)); 
                g2d.drawLine(X_CORD+width+20*i, Y_CORD, X_CORD+width+20*i, Y_CORD-array[i]);
         
             }
             
             for(int i=counter ; i<arraySize ; i++){
             
                g2d.setColor(Color.BLUE);
                g2d.setStroke(new BasicStroke(10)); 
                g2d.drawLine(X_CORD+20*i, Y_CORD, X_CORD+20*i, Y_CORD-array[i]);

                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(5)); 
                g2d.drawLine(X_CORD+width+20*i, Y_CORD, X_CORD+width+20*i, Y_CORD-array[i]);
                }
                if(counter !=arraySize) counter++;
            }
        }
    }
    

    public int[] createArray(){    
       int[] arr = new int[arraySize];
       for(int i=0 ; i<arraySize ; i++){
           arr[i] = (int)(Math.random()*650+1);
        }
        return arr;
    }
        
        
    public boolean isFinish(){
        for(int i=0 ; i<arraySize-1 ; i++){
            if(array[i]>array[i+1])
                return false;
        }
        return true;
    }
    
}
