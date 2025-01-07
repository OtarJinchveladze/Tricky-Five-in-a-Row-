/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * BaseWindow class extends JFrame and provides the main window behavior
 * for the "Tricky Five-in-a-row" game. It includes window setup, custom
 * close behavior with an exit confirmation dialog, and a method to handle
 * the actions upon exiting the window.
 * 
 * This class is the foundation for other windows in the application.
 * 
 * @author acer
 */
public class BaseWindow extends JFrame {
    
    /**
     * Constructor that sets up the base window with a title, size, and custom
     * window close behavior.
     * 
     * It configures the window to show a confirmation dialog when the user
     * attempts to close the window.
     */
    public BaseWindow(){
        setTitle("Tricky Five-in-a-row");
        setSize(400, 450);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showExitConfirmation();
            }

        });
    }
    
    /**
     * Displays a confirmation dialog when the user attempts to close the window.
     * If the user confirms, the exit operation is performed.
     */
    private void showExitConfirmation() {
        int n = JOptionPane.showConfirmDialog(this, "Do you really want to quit?",
                "Really?", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            doUponExit();
        }
    }
    
    /**
     * This method is called when the user confirms the exit action.
     * It disposes of the current window to close the application.
     */
     protected void doUponExit() {
        this.dispose();
    }
}
