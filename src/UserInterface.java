import java.util.Scanner;

public class UserInterface {

    Scanner s;
    Board b;
    boolean[][] movesPossible;

    public UserInterface() {
        s = new Scanner(System.in);
        b = new Board('O');
        movesPossible = new boolean[8][8];
    }

    public String getNextMove() {
        System.out.print("Choose opponent's next move: ");
        String temp = s.next();
        while (Character.toUpperCase(temp.charAt(0)) < 65 || Character.toUpperCase(temp.charAt(0)) > 72 || temp.charAt(1) < 49 || temp.charAt(1) > 56){
            System.out.println("Move not valid, try again!");
            temp = s.next();
        }
        return temp;
    }

    public void makeMove(String move){
        getBoard().parseMove(move);
        String temp = move;
        while (!getBoard().parseMove(temp)){
            System.out.println("Invalid move assignment. Choose again!");
            temp = s.next();
        }
    }

    public Board getBoard(){
        return b;
    }

    public void printBoard(){
        System.out.println(b.boardToString());
    }

    public boolean getWhoMoves(){
        System.out.println("Welcome to 4-in-a-line");
        System.out.println("Who will move first?\n\t1)Player\n\t2)Opponent");
        int choice = s.nextInt();
        return choice == 1;
    }

    public int startup(){
        System.out.println("How much time for move calculation?");
        int choice = s.nextInt();
        return choice;
    }

    public void printMoves() {
        System.out.println(b.movesToString());
    }
}
