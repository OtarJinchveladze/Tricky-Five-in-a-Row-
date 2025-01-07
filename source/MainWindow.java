package assignment2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/**
 * The MainWindow class represents the main window of the application, allowing users 
 * to select different board sizes for the game. It extends BaseWindow and contains 
 * options for different game configurations.
 * 
 * @author Otar Jintchveladze
 */
public class MainWindow extends BaseWindow {

    /** List of open game windows */
    private List<Window> gameWindows = new ArrayList<>();

    /**
     * Constructs the MainWindow with three button options: 6x6, 10x10, and 14x14.
     * Each button, when clicked, opens a new game window with the specified board size.
     */
    public MainWindow() {
        JButton small = new JButton();
        small.setText("6 x 6");
        small.addActionListener(getActionListener(6));

        JButton medium = new JButton();
        medium.setText("10 x 10");
        medium.addActionListener(getActionListener(10));

        JButton big = new JButton();
        big.setText("14 x 14");
        big.addActionListener(getActionListener(10));

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(small);
        getContentPane().add(medium);
        getContentPane().add(big);
    }

    /**
     * Creates an ActionListener that opens a new game window with the specified board size.
     * 
     * @param size the size of the game board to be created when the button is clicked
     * @return an ActionListener that opens a new Window instance with the specified board size
     */
    private ActionListener getActionListener(final int size) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = new Window(size, MainWindow.this);
                window.setVisible(true);
            }
        };
    }

    /**
     * Retrieves the list of game windows currently open.
     * 
     * @return a list of Window instances representing open game windows
     */
    public List<Window> getWindowList() {
        return gameWindows;
    }

    /**
     * Exits the application when the main window is closed.
     * Overrides the method from BaseWindow.
     */
    @Override
    protected void doUponExit() {
        System.exit(0);
    }
}
