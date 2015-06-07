/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubsetSum;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Albert
 */
public class ResultMessage extends Thread{
    Window w;
    StringBuilder results;
    public ResultMessage(Window w,StringBuilder results){
        this.w=w;
        this.results=results;
    }
    
    
    public void run(){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame ("Solution results");
        frame.setSize(600, 100);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLocation((int) ((dimension.getWidth() - frame.getWidth()) / 2),0);
        String s=results.toString();
        JLabel label = new JLabel(s);
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Container contentPane = frame.getContentPane();
        contentPane.add(label);
        
        frame.setVisible(true);
        

       
    }
}
