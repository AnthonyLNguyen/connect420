public class Board {

    private char[][] board;
    private char moveMarker;
    private int moveCount = 0;
    private String moves = "";

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

    public boolean placeTile(char r, int c, char t){
        if (r < 97)
            r += 32;
        r -= 97;
        c--;
        if(board[r][c] != '-')
            return false;
        board[r][c] = t;
        return true;
    }

    public boolean parseMove(String move) {
        if (board[Character.toUpperCase(move.charAt(0)) - 65][Character.getNumericValue(move.charAt(1)) - 1] == '-') {
            moveCount++;
            moves += "\n" + moveCount + ". " + move + " ";
            board[Character.toUpperCase(move.charAt(0)) - 65][Character.getNumericValue(move.charAt(1)) - 1] = moveMarker;
            return true;
        }
        return false;
    }

    public void myMove(char[][] child){
        char letter = '\0';
        int num = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (child[i][j] != board[i][j]) {
                    letter = (char) (i + 97);
                    num = j;
                }

            }
        }
        moves += letter + "" + (num+1);
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

    String movesToString(){
        return moves;
    }


    public char[][] getArray(){
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
}
