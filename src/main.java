/**
 * Created by antho on 11/24/2017.
 */
public class main {
    public static void main(String args[]) {
        minimax m = new minimax();
        UserInterface ui = new UserInterface();
        if (!ui.startup());
            ui.makeMove(ui.getNextMove());
        ui.printBoard();


        //m.placeTile(board,'e',3,'O');



        System.out.println(m.terminalTest(ui.getBoard().getArray()));

        ui.printBoard();

        //System.out.println(m.abSearch(board));



    }
}
