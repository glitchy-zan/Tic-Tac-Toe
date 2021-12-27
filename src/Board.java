import java.util.Scanner;

public class Board {
    Tile[][] board;
    private char turn;
    // int arrays for recognizing the winner
    private int[] rows1, cols1, diag1, oppDiag1;
    private int[] rows, cols, diag, oppDiag;
    // int for recognizing if board is full
    private int full;
    private char winner;
    Scanner scan = new Scanner(System.in);

    Board() {
        this.board = new Tile[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Tile();
            }
        }
        this.turn = 'X';

        this.rows1 = new int[3];
        this.cols1 = new int[3];
        this.diag1 = new int[3];
        this.oppDiag1 = new int[3];

        this.rows = new int[3];
        this.cols = new int[3];
        this.diag = new int[3];
        this.oppDiag = new int[3];

        this.full = 0;
        this.winner = ' ';
    }

    public void play() {
        int row, col;
        printBoard();
        while (!isOver()) {
            System.out.println("It's " + turn + " on move");

            System.out.println("select row: ");
            row = scan.nextInt();
            System.out.println("select column: ");
            col = scan.nextInt();

            while (!validMove(row, col)) {
                System.out.println("invalid move!");
                System.out.println("select new row: ");
                row = scan.nextInt();
                System.out.println("select new column: ");
                col = scan.nextInt();
            }

            makeMove(row, col);
            printBoard();
        }

        if (this.winner == ' ') System.out.println("draw");
        else if (this.winner == 'O') System.out.println("winner is O");
        else System.out.println("winner is X");
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != null) System.out.print(board[i][j].getPlayer() + " | ");
                else System.out.print('x' + " | ");
            }
            System.out.println();
            System.out.println("_____________");
        }
    }

    public boolean validMove(int row, int col) {
        int x = row - 1;
        int y = col - 1;
        if (x < 0 || x > 2) return false;
        else if (y < 0 || y > 2) return false;
        else if (board[x][y] != null && board[x][y].getPlayer() != ' ') return false;
        else return true;
    }

    public void makeMove(int row, int col) {

        int x = row - 1;
        int y = col - 1;

        this.board[x][y].setPlayer(this.turn);

        if (this.turn == 'O') {
            this.rows[x]++;
            this.cols[y]++;

            if (x == y) diag[x] = 1;
            if (x + y == 2) oppDiag[x] = 1;
        } else {
            this.rows1[x]++;
            this.cols1[y]++;

            if (x == y) diag1[x] = 1;
            if (x + y == 2) oppDiag1[x] = 1;
        }
        this.full += 3 * x + y;

        changeTurn();
    }

    public boolean isOver() {
        int dc = 0;
        int opdc = 0;
        int dc1 = 0;
        int opdc1 = 0;
        for (int i = 0; i < 3; i++) {
            dc += diag[i];
            opdc += oppDiag[i];
            dc1 += diag1[i];
            opdc1 += oppDiag1[i];
            if (rows[i] == 3 || cols[i] == 3 || dc == 3 || opdc == 3) {
                this.winner = 'O';
                return true;
            } else if (rows1[i] == 3 || cols1[i] == 3 || dc1 == 3 || opdc1 == 3) {
                this.winner = 'X';
                return true;
            }
        }
        return (full == 36) ? true : false;
    }

    public void changeTurn() {
        if (this.turn == 'X') this.turn = 'O';
        else this.turn = 'X';
    }

    public Tile[][] getBoard() {
        return board;
    }

    public char getTurn() {
        return turn;
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }

    public void setTurn(char turn) {
        this.turn = turn;
    }

    public char getWinner() {
        return winner;
    }

    public void setWinner(char winner) {
        this.winner = winner;
    }
}
