/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Model class represents the game logic, including managing the game board, 
 * handling player moves, checking for winners, and managing the game state (such as 
 * drawing or switching players).
 * 
 * @author acer
 */
public class Model {
    
    private int size;
    private Player actualPlayer;
    private Player[][] table;
    private Window window; 

    
     /**
     * Constructs a Model with the given board size and initializes the game board.
     * Sets the initial player to Player.X and fills the board with Player.NOBODY.
     * 
     * @param size the size of the game board (e.g., 6, 10, 14)
     */
    public Model(int size){
        this.size = size;
        actualPlayer = Player.X;
        
        table = new Player[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                table[i][j] = Player.NOBODY;
            }
        }
    }
    
     /**
     * Sets the window associated with this game model.
     * 
     * @param window the window to associate with this model
     */
    public void setWindow(Window window) {
        this.window = window;
    }
    
     /**
     * Retrieves the current game board.
     * 
     * @return a 2D array representing the current state of the game board
     */
    public Player[][] getTable(){
        return this.table;
    }
    
    /**
     * Makes a move on the board for the current player at the specified row and column.
     * If the spot is empty, the move is made, and the player is switched.
     * If a player wins, the game ends and the winner is returned.
     * 
     * @param row the row of the board where the move is made
     * @param column the column of the board where the move is made
     * @return the Player who made the move, or Player.NOBODY if it's a draw
     */
     public Player step(int row, int column) {
        
        if (table[row][column] != Player.NOBODY) {
            return table[row][column];
        }

        table[row][column] = actualPlayer;

        if (actualPlayer == Player.X) {
            actualPlayer = Player.O;
        } else {
            actualPlayer = Player.X;
        }
        
        if (isDraw()) {
        return Player.NOBODY; // Indicate a draw
    }
        
        return table[row][column];
     }
     
    /**
     * Checks if there is a winner on the board by looking for a row, column, or diagonal
     * with 5 consecutive signs. If a player has 3 or 4 consecutive signs, some random signs 
     * are removed to introduce a strategy element.
     * 
     * @return the Player who won, or Player.NOBODY if there is no winner yet
     */ 
    public Player findWinner() {
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (table[i][j] != Player.NOBODY) {  
                Player currentPlayer = table[i][j];

                // Check horizontal
                if (j <= size - 5) {  
                    int count = 1;
                    for (int k = 1; k < 5; k++) {
                        if (table[i][j + k] == currentPlayer) {
                            count++;
                        } else {
                            break;
                        }
                    }
                    if (count == 5) return currentPlayer;
                    else if (count == 4) removeRandomSigns(currentPlayer, 2);
                    else if (count == 3) removeRandomSigns(currentPlayer, 1);
                }

                // Check vertical
                if (i <= size - 5) {  
                    int count = 1;
                    for (int k = 1; k < 5; k++) {
                        if (table[i + k][j] == currentPlayer) {
                            count++;
                        } else {
                            break;
                        }
                    }
                    if (count == 5) return currentPlayer;
                    else if (count == 4) removeRandomSigns(currentPlayer, 2);
                    else if (count == 3) removeRandomSigns(currentPlayer, 1);
                }

                // Check diagonal (top-left to bottom-right)
                if (i <= size - 5 && j <= size - 5) {  
                    int count = 1;
                    for (int k = 1; k < 5; k++) {
                        if (table[i + k][j + k] == currentPlayer) {
                            count++;
                        } else {
                            break;
                        }
                    }
                    if (count == 5) return currentPlayer;
                    else if (count == 4) removeRandomSigns(currentPlayer, 2);
                    else if (count == 3) removeRandomSigns(currentPlayer, 1);
                }

                // Check diagonal (top-right to bottom-left)
                if (i <= size - 5 && j >= 4) {  
                    int count = 1;
                    for (int k = 1; k < 5; k++) {
                        if (table[i + k][j - k] == currentPlayer) {
                            count++;
                        } else {
                            break;
                        }
                    }
                    if (count == 5) return currentPlayer;
                    else if (count == 4) removeRandomSigns(currentPlayer, 2);
                    else if (count == 3) removeRandomSigns(currentPlayer, 1);
                }
            }
        }
    }

    return Player.NOBODY;
}
    /**
     * Checks if the game ended in a draw (i.e., the board is full and no winner).
     * 
     * @return true if the game is a draw, false otherwise
     */
    private boolean isDraw() {
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (table[i][j] == Player.NOBODY) {
                return false; // Found an empty space, so not a draw
            }
        }
    }
    return true; // No empty spaces, it's a draw
}
    
     /**
     * Retrieves the current player who is about to make a move.
     * 
     * @return the current player (either Player.X or Player.O)
     */
    public Player getActualPlayer() {
        return actualPlayer;
    }
    
    /**
     * Removes a specified number of random signs from the board of the given player.
     * This is called when the player has 3 or 4 consecutive signs to introduce a random element.
     * 
     * @param player the player whose signs are to be removed
     * @param count the number of signs to remove
     */
    private void removeRandomSigns(Player player, int count) {
    
    List<int[]> playerPositions = new ArrayList<>();

    // Collect all positions of the current player's signs
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (table[i][j] == player) {
                playerPositions.add(new int[]{i, j});
            }
        }
    }

    Random rand = new Random();

    for (int k = 0; k < count && !playerPositions.isEmpty(); k++) {
        
        int[] position = playerPositions.remove(rand.nextInt(playerPositions.size()));
        table[position[0]][position[1]] = Player.NOBODY;  // Clear the sign
    }
    
    if (window != null) {
        window.updateBoardDisplay();
    }
}
       
}   

    
 
