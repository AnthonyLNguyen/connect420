/**
 * Created by Anthony on 11/28/2017.
 */
public class Node {
    Node parent;
    char[][] board;
    int value = 0;

    public Node(Node parent, char[][] state) {
        this.parent = parent;
        this.board = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
