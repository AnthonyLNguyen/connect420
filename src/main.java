/**
 * Created by antho on 11/24/2017.
 */
public class main {
    public static void main(String args[]) {
        minimax m = new minimax();
        char[][] board = new char[8][8];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '-';
            }
        }

        //m.placeTile(board,'e',3,'O');



        System.out.println(m.terminalTest(board));

        System.out.println(m.boardToString(board));

        System.out.println(m.abSearch(board));



    }
}
