import java.util.Scanner;

public class UserInterface {

    Scanner s;
    Board b;

    public UserInterface() {
        s = new Scanner(System.in);
        b = new Board('X');
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
        getBoard().parceMove(move);
    }

    public Board getBoard(){
        return b;
    }

    public void printBoard(){
        System.out.println(b.boardToString());
    }

    public boolean startup(){
        System.out.println("Welcome to 4-in-a-line");
        System.out.println("Who will move first?\n\t1)Player\n\t2)Opponent");
        int choice = s.nextInt();
        if (choice == 1)
            return true;
        return false;
    }


}
