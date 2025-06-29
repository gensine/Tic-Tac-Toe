import java.util.Scanner;

public class CommandLineTicTacToe {
    private char[][] board;
    private char currentPlayer;
    private Scanner scanner;
    private int xWins, oWins, ties;
    private boolean gameActive;
    
    public CommandLineTicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        scanner = new Scanner(System.in);
        xWins = oWins = ties = 0;
        gameActive = true;
        initializeBoard();
    }
    
    private void initializeBoard() {
        char num = '1';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = num++;
            }
        }
    }
    
    private void displayWelcome() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("🎮 WELCOME TO TIC TAC TOE CHAMPIONSHIP 🎮");
        System.out.println("=".repeat(40));
        System.out.println("📋 HOW TO PLAY:");
        System.out.println("   • Enter numbers 1-9 to make your move");
        System.out.println("   • Player X goes first");
        System.out.println("   • Get 3 in a row to win!");
        System.out.println("=".repeat(40));
    }
    
    private void displayBoard() {
        System.out.println("\n📊 CURRENT BOARD:");
        System.out.println("┌─────┬─────┬─────┐");
        for (int i = 0; i < 3; i++) {
            System.out.print("│");
            for (int j = 0; j < 3; j++) {
                char cell = board[i][j];
                String cellDisplay;
                
                if (cell == 'X') {
                    cellDisplay = " ❌ ";
                } else if (cell == 'O') {
                    cellDisplay = " ⭕ ";
                } else {
                    cellDisplay = " " + cell + " ";
                }
                
                System.out.print(cellDisplay + "│");
            }
            System.out.println();
            if (i < 2) {
                System.out.println("├─────┼─────┼─────┤");
            }
        }
        System.out.println("└─────┴─────┴─────┘");
    }
    
    private void displayScore() {
        System.out.println("\n🏆 SCOREBOARD:");
        System.out.println("┌──────────────────────────────┐");
        System.out.printf("│ Player X: %-3d | Player O: %-3d │%n", xWins, oWins);
        System.out.printf("│        Ties: %-3d            │%n", ties);
        System.out.println("└──────────────────────────────┘");
    }
    
    private void displayPlayerTurn() {
        String playerSymbol = (currentPlayer == 'X') ? "❌" : "⭕";
        System.out.println("\n🎯 Player " + currentPlayer + " " + playerSymbol + "'s turn!");
        System.out.print("Enter your move (1-9): ");
    }
    
    private boolean makeMove(int position) {
        if (position < 1 || position > 9) {
            System.out.println("❌ Invalid position! Please enter 1-9.");
            return false;
        }
        
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        
        if (board[row][col] == 'X' || board[row][col] == 'O') {
            System.out.println("❌ Position already taken! Choose another.");
            return false;
        }
        
        board[row][col] = currentPlayer;
        return true;
    }
    
    private boolean checkWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        
        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        
        return false;
    }
    
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    
    private void displayWinner() {
        String playerSymbol = (currentPlayer == 'X') ? "❌" : "⭕";
        System.out.println("\n🎉 CONGRATULATIONS! 🎉");
        System.out.println("🏆 Player " + currentPlayer + " " + playerSymbol + " WINS!");
        System.out.println("✨ " + "★".repeat(25) + " ✨");
        
        if (currentPlayer == 'X') {
            xWins++;
        } else {
            oWins++;
        }
    }
    
    private void displayTie() {
        System.out.println("\n🤝 IT'S A TIE!");
        System.out.println("🎯 Great game, both players!");
        System.out.println("💫 " + "~".repeat(25) + " 💫");
        ties++;
    }
    
    private boolean askPlayAgain() {
        System.out.print("\n🔄 Would you like to play again? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }
    
    private void resetGame() {
        initializeBoard();
        currentPlayer = 'X';
        gameActive = true;
    }
    
    private void displayGoodbye() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("👋 THANKS FOR PLAYING TIC TAC TOE!");
        System.out.println("🎮 Final Results:");
        System.out.printf("   Player X won %d games%n", xWins);
        System.out.printf("   Player O won %d games%n", oWins);
        System.out.printf("   %d games ended in ties%n", ties);
        System.out.println("=".repeat(40));
        System.out.println("🌟 Come back soon for more fun!");
    }
    
    public void playGame() {
        displayWelcome();
        
        do {
            displayScore();
            
            while (gameActive) {
                displayBoard();
                displayPlayerTurn();
                
                try {
                    int position = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    
                    if (makeMove(position)) {
                        if (checkWinner()) {
                            displayBoard();
                            displayWinner();
                            gameActive = false;
                        } else if (isBoardFull()) {
                            displayBoard();
                            displayTie();
                            gameActive = false;
                        } else {
                            switchPlayer();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("❌ Invalid input! Please enter a number.");
                    scanner.nextLine(); // Clear invalid input
                }
            }
            
            if (askPlayAgain()) {
                resetGame();
                System.out.println("\n🚀 Starting new game...");
            } else {
                break;
            }
            
        } while (true);
        
        displayGoodbye();
        scanner.close();
    }
    
    public static void main(String[] args) {
        CommandLineTicTacToe game = new CommandLineTicTacToe();
        game.playGame();
    }
}
