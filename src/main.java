/**
 * Created by antho on 11/24/2017.
 */
public class main {
    public static void main(String args[]) {
        minimax m = new minimax();
        UserInterface ui = new UserInterface();
        /*if (!ui.startup());
            ui.makeMove(ui.getNextMove());*/


        //ui.getBoard().placeTile('e',5,'O');
        //ui.getBoard().placeTile('e',4,'X');
        ui.getBoard().placeTile('A',4,'X');
        ui.getBoard().placeTile('B',4,'X');
        ui.getBoard().placeTile('C',4,'X');
        ui.getBoard().placeTile('D',4,'X');
        ui.getBoard().placeTile('E',4,'O');
        ui.getBoard().placeTile('F',4,'X');
        ui.getBoard().placeTile('G',4,'X');
        ui.getBoard().placeTile('H',4,'X');
        //ui.getBoard().placeTile('A',5,'X');



        //ui.getBoard().placeTile('e',2,'X');
        ///ui.getBoard().placeTile('e',1,'O');
        ui.printBoard();
        System.out.println(m.terminalTest(ui.getBoard().getArray()));

        System.out.println(m.calcUtil2(ui.getBoard().getArray(),'X', 'O'));
        //m.makeMove(ui.getBoard());
        //System.out.println(m.alphabeta(ui.getBoard(),new Node(null,ui.getBoard().getArray()), 4,Integer.MIN_VALUE,Integer.MAX_VALUE,true));
        //System.out.println(m.abSearch(ui.getBoard()));
        //ui.printBoard();



    }
}
