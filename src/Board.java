public class Board {

    private char[][] board;
    private char moveMarker;

    public Board (char mm) {
        moveMarker = mm;
        board = new char[8][8];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void placeTile(int r, int c, char t){
        board[r][c] = t;
    }

    public void parceMove(String move){
        board[Character.toUpperCase(move.charAt(0)) - 65][Character.getNumericValue(move.charAt(1)) - 1] = moveMarker;
    }

    String boardToString(){
        String result = "\t1 2 3 4 5 6 7 8\n";
        int r = 65;
        for (int i = 0; i < board.length; i++){
            result += (char)r + "\t";
            r++;
            for (int j = 0; j < board[0].length; j++){
                result += board[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }

    public char[][] getArray(){
        return board;
    }
}
