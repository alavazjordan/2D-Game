package main;

import java.awt.Container;

import javax.swing.JFrame;

public class Main extends JFrame{
 

    public Main(){
        Container pane = getContentPane();
        MyPanel mp = new MyPanel();
        pane.add(mp);

        pack();
        setVisible(true);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);//doesn't allow for window to be resized
        setLocationRelativeTo(null);// sets window in the center of screen


        mp.startGameThread();
    }

    public static void main(String[] args){
        new Main();
    }
}
