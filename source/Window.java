/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Window class represents the graphical user interface for the game.
 * It handles the display of the game board, interaction with the user,
 * and communication with the Model class to process game logic.
 * 
 * The class also updates the game status, displays the current player, and 
 * shows messages when the game is over (either draw or win).
 * 
 * @author acer
 */
public class Window extends BaseWindow {
    
    private final int size; 
    private final Model model;
    private final JLabel label;
    private final MainWindow mainWindow;
    private JLabel mainPanel;
    private JButton[][] buttons; // Store buttons in a 2D array


    /**
     * Constructor that initializes the Window with the given size.
     * Sets up the game model, buttons, and event listeners.
     * 
     * @param size The size of the board (e.g., 5 for a 5x5 grid).
     * @param mainWindow Reference to the main window that manages this window.
     */
    public Window(int size, MainWindow mainWindow){
        this.size = size; 
        this.mainWindow = mainWindow;
        model = new Model(size);
        model.setWindow(this);
        
        JPanel top = new JPanel();
        
        label = new JLabel();
        updateLabelText();

        
        JButton newGameButton = new JButton();
        newGameButton.setText("New game");
        newGameButton.addActionListener(e -> newGame());
        
        top.add(label);
        top.add(newGameButton);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(size, size));
        
         buttons = new JButton[size][size]; // Initialize button array
        
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                buttons[i][j] = addButton(mainPanel, i, j); // Store button references
            }
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

    }
    
     /**
     * Adds a button to the given panel and sets up the action listener for it.
     * 
     * @param panel The panel to which the button will be added.
     * @param i The row index of the button on the grid.
     * @param j The column index of the button on the grid.
     * @return The created button.
     */
    private JButton addButton(JPanel panel, final int i,
            final int j) {
        
        final JButton button = new JButton();

        button.addActionListener(e -> {
            Player newValue = model.step(i, j);
            updateBoardDisplay(); // Update button text based on model state
            
            updateLabelText();
            
            Player winner = model.findWinner();
            if (winner != Player.NOBODY) {
                showGameOverMessage(winner);
            }else if (newValue == Player.NOBODY) {
                showGameOverMessage2(); // Show draw message
        }
        });
        
        panel.add(button);
        return button;
    }
     
     /**
     * Displays a message indicating the game is over and a player has won.
     * 
     * @param winner The player who won the game (X or O).
     */
    private void showGameOverMessage(Player winner) {
        JOptionPane.showMessageDialog(this,
                "Game is over. Winner: " + winner.name());
        newGame();
    }
    
    /**
     * Displays a message indicating the game ended in a draw.
     */
    private void showGameOverMessage2() {
        JOptionPane.showMessageDialog(this,
                "Draw");
        newGame();
    }
    
    /**
     * Starts a new game by creating a new Window and disposing of the old one.
     */
    public void newGame() {
        Window newWindow = new Window(size, mainWindow);
        newWindow.setVisible(true);
        
        this.dispose();
        mainWindow.getWindowList().remove(this);
    }
    
     /**
     * Updates the label to show the current player.
     */
    private void updateLabelText() {
        label.setText("Current player: "
                + model.getActualPlayer().name());
    }
    
    /**
     * Updates the game board display based on the current state in the Model.
     */
    protected void updateBoardDisplay() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (model.getTable()[i][j] == Player.NOBODY) {
                    buttons[i][j].setText(""); // Clear the button if it's nobody
                } else {
                    buttons[i][j].setText(model.getTable()[i][j].name()); // Set the player's sign (X or O)
                }
            }
        }
    }
}
