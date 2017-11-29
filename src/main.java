/**
 * Created by antho on 11/24/2017.
 */
public class main {
    public static void main(String args[]) {
        UserInterface ui = new UserInterface();

        minimax m = new minimax(ui.startup());
        boolean playFirst = ui.getWhoMoves();
        if (!playFirst) {
            ui.makeMove(ui.getNextMove());
            ui.printBoard();
        }
        while (!m.xWin(ui.getBoard().getArray()) && !m.oWin(ui.getBoard().getArray())){
            m.makeMove(ui.getBoard(), 6);
            ui.printBoard();
            ui.printMoves(playFirst);
            ui.makeMove(ui.getNextMove());
            ui.printBoard();

        }

        if (m.xWin(ui.getBoard().getArray()))
            System.out.println("X won!");
        else
            System.out.println("Y won!");

        /*//ui.getBoard().placeTile('e',5,'O');
        //ui.getBoard().placeTile('e',4,'X');

        //ui.getBoard().placeTile('c',5,'X');
        //ui.getBoard().placeTile('c',6,'X');
        //ui.getBoard().placeTile('c',7,'X');
        ui.getBoard().placeTile('e',4,'O');
        ui.getBoard().placeTile('d',4,'O');
        ui.getBoard().placeTile('b',4,'O');

        //ui.getBoard().placeTile('c',7,'X');
        //ui.getBoard().placeTile('e',4,'X');
        //ui.getBoard().placeTile('e',2,'X');
        //ui.getBoard().placeTile('e',1,'O');
        ui.printBoard();
        System.out.println(m.terminalTest(ui.getBoard().getArray()));

        System.out.println(m.evaluate(ui.getBoard().getArray(),'X','O'));



        m.makeMove(ui.getBoard(),4);
        //System.out.println(m.alphabeta(ui.getBoard(),new Node(null,ui.getBoard().getArray()), 4,Integer.MIN_VALUE,Integer.MAX_VALUE,true));
        //System.out.println(m.abSearch(ui.getBoard()));
        ui.printBoard();*/


    }
}
