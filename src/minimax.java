import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by antho on 11/24/2017.
 */
public class minimax {
    double time;
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


    public int[] utility2(char[][] board, int r, int c, char symbol){
        int count = 0;
        int [] ret = new int[3];
        for (int i=c;i<board.length && i < c + 4;i++)
        {
            if (board[r][i]==symbol) {
                count++;
                ret[0] = i;
                if (count>=4) {
                    ret[2] = 10000000;
                    return ret;
                }
            }
            else if (board[r][i] == '-'){
                if (count == 1) {
                    if (i < 6 &&  board[r][i + 1] == symbol) {
                        ret[2] -= 100;
                        count++;
                        if (board[r][i + 2] == symbol)
                            count++;
                    }
                } else if (count == 2){
                    if (i < 7 && board[r][i + 1] == symbol){
                        ret[2] -= 100;
                        count++;
                    }
                }
                if (count != 0)
                    ret[2] += (int) Math.pow(10, count);
                count = 0;
            } else
                count = 0;

        }
        count = 0;
        for (int i=r;i<board.length && i < r + 4;i++)
        {
            if (board[i][c]==symbol) {
                ret[1] = i;
                count++;
                if (count == 4) {
                    ret[2] = 10000000;
                    return ret;
                }
            }
            else if (board[i][c] == '-'){
                if (count == 1) {
                    if (i < 6 &&  board[i + 1][c] == symbol) {
                        ret[2] -= 100;
                        count++;
                        if (board[i + 2][c] == symbol)
                            count++;
                    }
                } else if (count == 2){
                    if (i < 7 && board[i + 1][c] == symbol){
                        ret[2] -= 100;
                        count++;
                    }
                }
                if (count != 0)
                    ret[2] += (int) Math.pow(10, count);
                count = 0;
            } else
                count = 0;
        }
        return ret;
    }

    public int calcUtil2(char[][] b, char playerSymbol, char oppSymbol){
        int result = 0;
        int[] temp = null;
        for (int i = 0; i < b.length; i++){
            for (int j = 0; j < b[0].length; j++){
                if (b[i][j] == playerSymbol) {
                    temp = utility2(b, i, j, playerSymbol);
                    //if (temp >= 10000000)
                    //return 10000000;
                    result += temp[2];
                    j = temp[0];
                    i = temp[1];
                }
                /*if (b[i][j] == oppSymbol) {
                    temp = utility2(b, i, j, playerSymbol);
                    //if (temp >= 10000000)
                        //return -10000000;
                    result -= temp[2];
                }*/
            }
        }
        return result;
    }

    double evaluate(char[][] board, char symbol, char enemy) {
        int threeRow = nearWinRows(board, 1,symbol);
        int twoRow = nearWinRows(board, 2,symbol);
        int twoCol = nearWinCols(board, 2,symbol);
        int threeCol = nearWinCols(board, 1,symbol);
        int fourRow = nearWinRows(board,0,symbol);
        int fourCol = nearWinCols(board,0,symbol);
        double score = Integer.max(fourCol*10000+threeCol*100+twoCol*5,fourRow*10000+threeRow*100+twoRow*5);
        threeRow = nearWinRows(board, 1,enemy);
        twoRow = nearWinRows(board, 2,enemy);
        twoCol = nearWinCols(board, 2,enemy);
        threeCol = nearWinCols(board, 1,enemy);
        fourRow = nearWinRows(board,0,enemy);
        fourCol = nearWinCols(board,0,enemy);
        score -= Integer.max(fourCol*10000+threeCol*100+twoCol*5,fourRow*10000+threeRow*100+twoRow*5);
        return score;
    }


    private int nearWinRows(char[][] board , int away,char symbol) {
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

    private int nearWinCols(char[][] board, int away, char symbol) {
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
        ArrayList<Node> succ = generateSucc(new Node(null,b.getArray()),'X');
        int max = -1;
        int val;
        for (Node n:succ) {
            val = alphabeta(b,n,depth,Integer.MIN_VALUE,Integer.MAX_VALUE,true);
            if (val > max)
                max = val;
            n.setValue(val);
        }
        for (Node n:succ) {
            if (n.getValue() == max) {
                if(xWin(n.getBoard())){
                    b.setBoard(n.getBoard());
                    break;
                }
                b.setBoard(n.getBoard());
            }
        }
    }


    public ArrayList<Node> generateSucc(Node state, char sym){
        ArrayList<Node> result = new ArrayList<>();
        char[][] temp;
        for (int i = 0; i < state.getBoard().length; i++) {
            for (int j = 0; j < state.getBoard().length; j++) {
                if (state.getBoard()[i][j] == '-') {
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
