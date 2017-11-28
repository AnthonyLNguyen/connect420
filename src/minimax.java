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
        for (int i=0;i<board.length;i++)
        {
            if (board[r][i]==symbol)
                count++;
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
            } else {
                result += (int)Math.pow(10, count);
                count = 0;
            }
            if (count>=4)
                return 10000000;
        }

        for (int i=0;i<board[0].length;i++)
        {
            if (board[i][c]==symbol)
                count++;
            else if (board[i][c] == '-'){
                if (count == 1) {
                    if (i < 6 &&  board[i + 1][c] == symbol) {
                        count++;
                        if (board[r][i + 2] == symbol)
                            count++;
                    }
                } else if (count == 2){
                    if (i < 7 && board[i + 1][c] == symbol){
                        count++;
                    }
                }
            } else {
                result += (int)Math.pow(10, count);
                count = 0;
            }
            if (count>=4)
                return 10000000;
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

    public int calcUtil2(char[][] b, char playerSymbol){
        int result = 0;
        for (int i = 0; i < b.length; i++){
            for (int j = 0; j < b[0].length; j++){
                if (b[i][j] == playerSymbol)
                    result += utility2(b, i, j, playerSymbol);
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

    int abSearch(char[][] state){ // just copied pseudocode . these dont actually work yetc
        time = System.nanoTime();
        int v = maxValue(state,Integer.MIN_VALUE,Integer.MAX_VALUE);
        return v;
    }

    private int maxValue(char[][] state, int a, int b) { // just copied pseudocode . these dont actually work yetc
        if (terminalTest(state) || System.nanoTime() - time > 5e9)
            return calcUtil(state,'X');
        int v = Integer.MIN_VALUE;
        for (char[][] s:generateSucc(state,'X')) {
            v = Integer.max(v,minValue(s,a,b));
            if (v >= b)
                return v;
            a = Integer.max(a,v);
        }
        return v;
    }

    private int minValue(char[][] state, int a, int b) {// just copied pseudocode . these dont actually work yetc
        if (terminalTest(state) || System.nanoTime() - time > 5e9)
            return calcUtil(state,'O');
        int v = Integer.MIN_VALUE;
        for (char[][] s:generateSucc(state,'0')) {
            v = Integer.max(v,maxValue(s,a,b));
            if (v <= a)
                return v;
            b = Integer.min(b,v);
        }
        return v;
    }

    public ArrayList<char[][]> generateSucc(char[][] state, char sym){
        ArrayList<char[][]> result = new ArrayList<>();
        char[][] temp;
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                temp = deepCopy(state);
                if (temp[i][j] == '-') {
                    temp[i][j] = sym;
                    result.add(temp);
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
