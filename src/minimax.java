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


    public int utility2(char[][] board, int r, int c, char symbol){
        int count = 0;
        int result = 0;
        for (int i=c;i<board.length;i++)
        {
            if (board[r][i]==symbol) {
                count++;
                if (count>=4)
                    return 10000000;
            }
            else if (board[r][i] == '-'){
                if (count == 1) {
                    if (i < 6 &&  board[r][i + 1] == symbol) {
                        count++;
                        if (board[r][i + 2] == symbol)
                            count++;
                    }
                } else if (count == 2){
                    if (i < 7 && board[r][i + 1] == symbol){
                        count++;
                    }
                }
                if (count != 0)
                    result += (int) Math.pow(10, count);
            } else
                count = 0;

        }
        count = 0;
        for (int i=r;i<board.length;i++)
        {
            if (board[i][c]==symbol) {
                count++;
                if (count == 4) {
                   // System.out.println(r + " " + c);
                    return 10000000;
                }
            }
            else if (board[i][c] == '-'){
                if (count == 1) {
                    if (i < 6 &&  board[i + 1][c] == symbol) {
                        count++;
                        if (board[i + 2][c] == symbol)
                            count++;
                    }
                } else if (count == 2){
                    if (i < 7 && board[i + 1][c] == symbol){
                        count++;
                    }
                }
                if (count != 0)
                    result += (int) Math.pow(10, count);
            } else
                count = 0;
        }
        return result;
    }

    public int calcUtil(char[][] b, char symbol){
        int result = 0;
        for (int i = 0; i < b.length; i++){
            for (int j = 0; j < b[0].length; j++){
                if (b[i][j] == symbol)
                    result += (int)Math.pow(10,utility(b, i, j, symbol));
            }
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
                    if (temp >= 10000000)
                        return 10000000;
                    result += temp;
                }
                if (b[i][j] == oppSymbol) {
                    temp = utility2(b, i, j, playerSymbol);
                    if (temp >= 10000000)
                        return -10000000;
                    result -= temp;
                }
            }
        }
        return result;
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

    public void placeTile(char[][] b, int r, int c, char t){
        b[r][c] = t;
    }
    public boolean placeTile(char[][] b, char r, int c, char t){
        if (r < 97)
            r += 32;
        r -= 97;
        c--;
        if(b[r][c] != '-')
            return false;
        b[r][c] = t;
        return true;
    }

    int alphabeta(Board b,Node n, int depth, int alpha, int beta, boolean maxPlayer){
        if (depth == 0 || terminalTest(n.getBoard())){
            return calcUtil(n.getBoard(),'X');
        }
        if(maxPlayer){
            int v = Integer.MIN_VALUE;
            ArrayList<Node> succ = generateSucc(n,'X');
            for (Node child: succ) {
                v = Integer.max(v,alphabeta(b,child,depth-1,alpha,beta,false));
                alpha = Integer.max(alpha,v);
                if (beta <= alpha)
                    break;
            }
            return v;
        }
        else{
            int v = Integer.MAX_VALUE;
            ArrayList<Node> succ = generateSucc(n,'O');
            for (Node child: succ) {
                v = Integer.min(v,alphabeta(b,child,depth-1,alpha,beta,true));
                beta = Integer.min(beta,v);
                if (beta <= alpha)
                    break;
            }
            return v;
        }
    }


    void makeMove(Board b){
        ArrayList<Node> succ = generateSucc(new Node(null,b.getArray()),'X');
        int max = -1;
        int val;
        for (Node n:succ) {
            val = alphabeta(b,n,4,Integer.MIN_VALUE,Integer.MAX_VALUE,true);
            if (val > max)
                max = val;
            n.setValue(val);
        }
        for (Node n:succ) {
            if (n.getValue() == max) {
                b.setBoard(n.getBoard());
                break;
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
