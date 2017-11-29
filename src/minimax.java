import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by antho on 11/24/2017.
 */
public class minimax {

    public minimax(int t){
    }

    long time;
    double maxTime = 3;
    /**
    String boardToString(char[][] board){
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
     **/
    public char checkWin(char[][] board, int r, int c){
        char result = 'N';
        char symbol = board[r][c];
        int count = 0;
        for (int i=0;i<board.length;i++)
        {
            if (board[r][i]==symbol)
                count++;
            else
                count=0;

            if (count>=4)
                return symbol;
        }

        for (int i=0;i<board[0].length;i++)
        {
            if (board[i][c]==symbol)
                count++;
            else
                count=0;

            if (count>=4)
                return symbol;
        }
        return result;
    } //check who wins

    public int utility(char[][] board, int r, int c, char symbol){
        int count = 0;
        int maxCount = 0;
        for (int i=0;i<board.length;i++)
        {
            if (board[r][i]==symbol)
                count++;
            else
                count=0;
            if (count > maxCount)
                maxCount = count;
            if (count>=4)
                return 1000;
        }

        for (int i=0;i<board[0].length;i++)
        {
            if (board[i][c]==symbol)
                count++;
            else
                count=0;
            if (count > maxCount)
                maxCount = count;
            if (count>=4)
                return 1000;
        }
        return maxCount;
    } //not sure how to calculate utility

    public int utility2(char[][] board, int r, int c, char symbol){
        int count = 0;
        int result = 0;
        for (int i=c;i<board.length && i < c + 4;i++)
        {
            if (board[r][i]==symbol) {
                count++;
                if (count>=4) {
                    return 10000000;
                }
            }
            else if (board[r][i] == '-'){
                if (count == 1) {
                    if (i < 6 &&  board[r][i + 1] == symbol) {
                        result -= 100;
                        count++;
                        if (board[r][i + 2] == symbol)
                            count++;
                    }
                } else if (count == 2){
                    if (i < 7 && board[r][i + 1] == symbol){
                        result -= 100;
                        count++;
                    }
                }
                if (count != 0)
                    result += (int) Math.pow(10, count);
                count = 0;
            } else
                count = 0;

        }
        count = 0;
        for (int i=r;i<board.length && i < r + 4;i++)
        {
            if (board[i][c]==symbol) {
                count++;
                if (count == 4) {
                    return result;
                }
            }
            else if (board[i][c] == '-'){
                if (count == 1) {
                    if (i < 6 &&  board[i + 1][c] == symbol) {
                        result -= 100;
                        count++;
                        if (board[i + 2][c] == symbol)
                            count++;
                    }
                } else if (count == 2){
                    if (i < 7 && board[i + 1][c] == symbol){
                        result -= 100;
                        count++;
                    }
                }
                if (count != 0)
                    result += (int) Math.pow(10, count);
                count = 0;
            } else
                count = 0;
        }
        return result;
    }

    public int calcUtil2(char[][] b, char playerSymbol, char oppSymbol){
        int result = 0;
        int temp = 0;
        for (int i = 0; i < b.length; i++){
            for (int j = 0; j < b[0].length; j++){
                if (b[i][j] == playerSymbol) {
                    temp = utility2(b, i, j, playerSymbol);
                    //if (temp >= 10000000)
                    //return 10000000;
                    result += temp;
                }
                if (b[i][j] == oppSymbol) {
                    temp = utility2(b, i, j, playerSymbol);
                    //if (temp >= 10000000)
                        //return -10000000;
                    result -= temp;
                }
            }
        }
        return result;
    }

    double evaluate(char[][] board, char symbol, char enemy) {
        int threeRow = numRows(board, 1,symbol);
        int twoRow = numRows(board, 2,symbol);
        int twoCol = numCols(board, 2,symbol);
        int threeCol = numCols(board, 1,symbol);
        int fourRow = numRows(board,0,symbol);
        int fourCol = numCols(board,0,symbol);
        double score = Integer.max(fourCol*10000+threeCol*100+twoCol*5,fourRow*10000+threeRow*100+twoRow*5);
        /*threeRow = numRows(board, 1,enemy);
        twoRow = numRows(board, 2,enemy);
        twoCol = numCols(board, 2,enemy);
        threeCol = numCols(board, 1,enemy);
        fourRow = numRows(board,0,enemy);
        fourCol = numCols(board,0,enemy);
        score -= Integer.max(fourCol*10000+threeCol*100+twoCol*5,fourRow*10000+threeRow*100+twoRow*5);*/
        return score;
    }


    private int numRows(char[][] board , int away, char symbol) {
        int count = 0;
        int length = 4 - away;
        String match1 = strMatch(symbol, length);
        String match2 = '-' + match1;
        match1 += '-';
        for (int i = 0; i < 8; i++) {
            String row = new String(board[i]);
            if (row.contains(match1)) {
                int x = row.indexOf(match1);
                while (x >= 0) {
                    count++;
                    x = row.indexOf(match1, match1.length() + x);
                }
            }
            if (row.contains(match2)) {
                int x = row.indexOf(match2);
                while (x >= 0) {
                    count++;
                    x = row.indexOf(match2, match2.length() + x);
                }
            }
        }
        return count;
    }

    private int numCols(char[][] board, int away, char symbol) {
        int count = 0;
        int length = 4 - away;
        String match1 = strMatch(symbol, length);
        String match2 = '-' + match1;
        match1 += '-';
        for (int j = 0; j < 8; j++) {
            String column = "";
            for (int i = 0; i < 8; i++) {
                column += board[i][j];
            }
            if (column.contains(match1)) {
                int x = column.indexOf(match1);
                while (x >= 0) {
                    count++;
                    x = column.indexOf(match1, match1.length() + x);
                }
            }
            if (column.contains(match2)) {
                int x = column.indexOf(match2);
                while (x >= 0) {
                    count++;
                    x = column.indexOf(match2, match2.length() + x);
                }
            }
        }

        return count;
    }

    private String strMatch(char p, int length) {
        String match = "";
        for (int i = 0; i < length; i++) {
            match += Character.toString(p);
        }
        return match;
    }


    public boolean terminalTest(char[][] b){ // check if this is a winning state
        char winner;
        for (int i = 0; i < b.length; i++){
            for (int j = 0; j < b[0].length; j++){
                winner = checkWin(b,i,j);
                if (winner == 'X' || winner == 'O')
                    return true;
            }
        }
        return false;
    }

    public boolean xWin(char[][] b){ // check if this is a winning state
        char winner;
        for (int i = 0; i < b.length; i++){
            for (int j = 0; j < b[0].length; j++){
                winner = checkWin(b,i,j);
                if (winner == 'X')
                    return true;
            }
        }
        return false;
    }

    public boolean oWin(char[][] b){ // check if this is a winning state
        char winner;
        for (int i = 0; i < b.length; i++){
            for (int j = 0; j < b[0].length; j++){
                winner = checkWin(b,i,j);
                if (winner == 'O')
                    return true;
            }
        }
        return false;
    }


    int alphabeta(Board b,Node n, int depth, int alpha, int beta, boolean maxPlayer){
        if (depth == 0 || terminalTest(n.getBoard())){
            return (int)evaluate(n.getBoard(),'X','O');
        }
        if(maxPlayer){
            int v = Integer.MIN_VALUE;
            ArrayList<Node> succ = generateSucc(n,'X');
            for (Node child: succ) {
                v = Integer.max(v,alphabeta(b,child,depth-1,alpha,beta,false));
                alpha = Integer.max(alpha,v);
                if (beta <= alpha) {
                    break;
                }
            }
            return v;
        }
        else{
            int v = Integer.MAX_VALUE;
            ArrayList<Node> succ = generateSucc(n,'O');
            for (Node child: succ) {
                v = Integer.min(v,alphabeta(b,child,depth-1,alpha,beta,true));
                beta = Integer.min(beta,v);
                if (beta <= alpha) {
                    break;
                }
            }
            return v;
        }
    }


    void makeMove(Board b, int depth){
        time = System.currentTimeMillis();
        ArrayList<Node> succ = generateSucc(new Node(null,b.getArray()),'X');
        int max = -1;
        int val;
        for (Node n:succ) {
            if (System.currentTimeMillis()-time < maxTime*1000) {
            val = alphabeta(b,n,depth,Integer.MIN_VALUE,Integer.MAX_VALUE,true);
            if (val > max)
                max = val;
            n.setValue(val);
            }
        }
        for (Node n:succ) {
            if (n.getValue() == max) {
                if(xWin(n.getBoard())){
                    b.myMove(n.getBoard());
                    b.setBoard(n.getBoard());
                    break;
                }
                b.myMove(n.getBoard());
                b.setBoard(n.getBoard());
                break;
            }
        }
    }


    public int checkAdjEdges(Node state,int i, int j){
        int result = 0;
        if (i > 0)
            if(state.getBoard()[i-1][j] == 'X' || state.getBoard()[i-1][j] == 'O')
            result++;
        if (i < 7)
            if(state.getBoard()[i+1][j] == 'X' || state.getBoard()[i+1][j] == 'O')
            result++;
        if (j > 0)
            if(state.getBoard()[i][j-1] == 'X' || state.getBoard()[i][j-1] == 'O')
            result++;
        if (j < 7)
            if(state.getBoard()[i][j+1] == 'X' || state.getBoard()[i][j+1] == 'O')
            result++;
        return result;
    }

    public ArrayList<Node> generateSucc(Node state, char sym){
        ArrayList<Node> result = new ArrayList<>();
        char[][] temp;
        for (int i = 0; i < state.getBoard().length; i++) {
            for (int j = 0; j < state.getBoard().length; j++) {
                if (state.getBoard()[i][j] == '-' && checkAdjEdges(state,i,j) > 0) {
                    temp = deepCopy(state.getBoard());
                    temp[i][j] = sym;
                    result.add(new Node(state,temp));
                }
            }
        }
        return result;
    }

    public static char[][] deepCopy(char[][] original) {
        if (original == null) {
            return null;
        }
        final char[][] result = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }





}
