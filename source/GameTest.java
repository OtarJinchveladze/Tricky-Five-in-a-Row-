/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.util.ArrayList;
import javax.swing.JButton;

/**
 * This class contains various test methods to verify the behavior of the 
 * game logic in the "Tricky Five-in-a-row" game. It tests different game 
 * mechanics such as player turns, win detection, and board state updates.
 * 
 * The tests ensure the game behaves correctly and consistently in different
 * scenarios.
 * 
 * @author acer
 */
public class GameTest {
    
    public static void main(String[] args){
        
        testclickSameButtonTest();
        testdeletedThreeConsecutives();
        testdeletedFourConsecutives();
        testPlayerTurns();
        testPlayerAlternates();
        testBoardInitialization();
        testWinnerDetection();
        testDrawDetection();
        testDifferentBoardSizes();
        testEmptyBoard();
        testFullBoardNoWinner();
        testImmediateVictory();
        testDiagonalWin();
        testVerticalWin();
    }
    
    public static void testclickSameButtonTest(){
        
        Model gameModel = new Model(6);

        int row = 2, col = 2;
        gameModel.step(row, col);        
        Player initialValue = gameModel.getTable()[row][col];
        
        gameModel.step(row, col);
        Player newValue = gameModel.getTable()[row][col];
        
        if (initialValue == newValue && newValue != Player.NOBODY) {
            System.out.println("Test Passed: Clicking the same button does not change it.");
        } else {
            System.out.println("Test Failed: Clicking the same button changed its value.");
        }    
    }
    
    public static void testdeletedThreeConsecutives(){
        
    Model model = new Model(6);
   
    model.step(0, 0); // X
    model.step(1, 0); // O
    model.step(0, 1); // X
    model.step(1, 1); // O
    model.step(0, 2); // X -- Player X has 3 in a row

    model.findWinner();  
    
    Player[][] board = model.getTable();
    int countX = 0;
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
            if (board[i][j] == Player.X) {
                countX++;
            }
        }
    }
    if(countX == 2){
        System.out.println("Test Passed: Deleted one player after 3 consecutive clicks");
    }
  }
    
    public static void testdeletedFourConsecutives(){
        
    Model model = new Model(6);
   
    model.step(0, 0); // X
    model.step(1, 0); // O
    model.step(0, 1); // X
    model.step(1, 1); // O
    model.step(0, 3); // X 
    model.step(1, 4); // O 
    model.step(0, 2); // X -- 4th

    model.findWinner();  
    
    Player[][] board = model.getTable();
    int countX = 0;
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
            if (board[i][j] == Player.X) {
                countX++;
            }
        }
    }
    if(countX == 2){
        System.out.println("Test Passed: Deleted two players after 4 consecutive clicks");
    } 
  }
    
    public static void testPlayerTurns() {
        Model model = new Model(6); // Use 6x6 as an example board size
        Player initialPlayer = model.getActualPlayer();

        // Simulate two moves and check turns
        model.step(0, 0);  // Player X's move
        Player playerAfterFirstMove = model.getActualPlayer();
        model.step(1, 1);  // Player O's move
        Player playerAfterSecondMove = model.getActualPlayer();

        // Expected: Player alternates turn
        if (initialPlayer == Player.X && playerAfterFirstMove == Player.O && playerAfterSecondMove == Player.X) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }
    
    public static void testBoardInitialization() {
    Model model = new Model(6);
    Player[][] board = model.getTable();
    boolean isEmpty = true;

    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
            if (board[i][j] != Player.NOBODY) {
                isEmpty = false;
                break;
            }
        }
    }

    if (isEmpty) {
        System.out.println("TEST PASSED");
    } else {
        System.out.println("TEST FAILED");
    }
}
    public static void testWinnerDetection() {
    Model model = new Model(6);
    
    // Manually place five Xs in a row to simulate a win condition
    model.step(0, 0); // X
    model.step(1, 1); // O
    model.step(1, 0); // X
    model.step(2, 1); // O
    model.step(2, 0); // X
    model.step(3, 1); // O
    model.step(3, 0); // X
    model.step(4, 1); // O
    model.step(4, 0); // X -- Player X should now win

    if (model.findWinner() == Player.X) {
        System.out.println("TEST PASSED");
    } else {
        System.out.println("TEST PASSED");
    }
}
    public static void testDrawDetection() {
    Model model = new Model(6);
    
    for (int i = 0; i < 6; i++) {
    model.step(i, 0); // Step at row i, column 0
    }

    // Traverse the second column (1st column) from bottom to top
    for (int i = 5; i >= 0; i--) {
    model.step(i, 1); // Step at row i, column 1
    }

    // Traverse the third column (2nd column) from bottom to top
    for (int i = 5; i >= 0; i--) {
    model.step(i, 2); // Step at row i, column 2
    }

    // Traverse the fourth column (3rd column) from top to bottom
    for (int i = 0; i < 6; i++) {
    model.step(i, 3); // Step at row i, column 3
    }

    // Traverse the fifth column (4th column) from top to bottom
    for (int i = 0; i < 6; i++) {
    model.step(i, 4); // Step at row i, column 4
    }

    // Traverse the sixth column (5th column) from bottom to top
    for (int i = 5; i >= 0; i--) {
    model.step(i, 5); // Step at row i, column 5
    }
    
    model.findWinner(); 
    
    if (model.findWinner() == Player.NOBODY) {
        System.out.println("TEST PASSED");
    } else {
        System.out.println("TEST FAILED!");
    }
}
 
    public static void testPlayerAlternates() {
    Model model = new Model(6);
    
    Player initialPlayer = model.getActualPlayer();
    model.step(0, 0);  // Player X's move
    Player afterFirstMove = model.getActualPlayer();
    model.step(1, 1);  // Player O's move
    Player afterSecondMove = model.getActualPlayer();
    
    if (initialPlayer == Player.X && afterFirstMove == Player.O && afterSecondMove == Player.X) {
        System.out.println("TEST PASSED");
    } else {
        System.out.println("TEST FAILED");
    }
}
    public static void testDifferentBoardSizes() {
    int[] sizes = {6, 10, 14};
    
    for (int size : sizes) {
        Model model = new Model(size);
        if (model.getTable().length == size && model.getTable()[0].length == size) {
            System.out.println("TEST PASSED: for " + size + "x" + size);
        } else {
            System.out.println("TEST FAILED: for " + size + "x" + size);
        }
    }
}
    public static void testEmptyBoard() {
    
    Model model = new Model(6);
    Player[][] board = model.getTable();
    boolean isEmpty = true; 
    
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
            if (board[i][j] != Player.NOBODY) {
                isEmpty = false;
                break;
            }
        }
    }
    if (isEmpty) {
        System.out.println("TEST PASSED");
    } else {
        System.out.println("TEST FAILED!");
    }
}   
    
    public static void testFullBoardNoWinner() {
    Model model = new Model(6);

    // Assuming the model.step() takes care of alternating players.
    for (int i = 0; i < 6; i++) {
    model.step(i, 0); // Step at row i, column 0
}

// Traverse the second column (1st column) from bottom to top
for (int i = 5; i >= 0; i--) {
    model.step(i, 1); // Step at row i, column 1
}

// Traverse the third column (2nd column) from bottom to top
for (int i = 5; i >= 0; i--) {
    model.step(i, 2); // Step at row i, column 2
}

// Traverse the fourth column (3rd column) from top to bottom
for (int i = 0; i < 6; i++) {
    model.step(i, 3); // Step at row i, column 3
}

// Traverse the fifth column (4th column) from top to bottom
for (int i = 0; i < 6; i++) {
    model.step(i, 4); // Step at row i, column 4
}

// Traverse the sixth column (5th column) from bottom to top
for (int i = 5; i >= 0; i--) {
    model.step(i, 5); // Step at row i, column 5
}

    // After filling the board, check for the winner.
    if (model.findWinner() == Player.NOBODY) {
        System.out.println("TEST PASSED");
    } else {
        System.out.println("TEST FAILED!");
    }
}
    public static void testImmediateVictory() {
    Model model = new Model(6);
    
    // Player X makes the first move
    model.step(0, 0); 
    
    // Check if there is any winner after just one move
    if (model.findWinner() == Player.NOBODY) {
        System.out.println("TEST PASSED");
    } else {
        System.out.println("TEST FAILED");
    }
}
    public static void testDiagonalWin() {
    Model model = new Model(6);
    
    // Player X places 5 signs diagonally
    model.step(0, 0); // X 
    model.step(0, 1); // O
    model.step(1, 1);
    model.step(0, 2);
    model.step(3, 3);
    model.step(0, 4);
    model.step(4, 4);
    model.step(0, 5);
    model.step(2, 2);
    
    if (model.findWinner() == Player.X) {
        System.out.println("TEST PASSED");
    } else {
        System.out.println("TEST FAILED");
    }
}
    public static void testVerticalWin() {
    Model model = new Model(6);
    
    model.step(0,0);
    model.step(0,1);
    model.step(1,0);
    model.step(0,2);
    model.step(3,0);
    model.step(1,3);
    model.step(4,0);
    model.step(1,4);
    model.step(2,0);
    
    if (model.findWinner() == Player.X) {
        System.out.println("TEST PASSED");
    } else {
        System.out.println("TEST FAILED");
    }
}
 
}
