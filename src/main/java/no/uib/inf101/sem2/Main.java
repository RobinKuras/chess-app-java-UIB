package no.uib.inf101.sem2;

import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.ChessModel;
import no.uib.inf101.sem2.view.ChessView;
import javax.swing.*;

public class Main {
  public static void main(String[] args) {

    ChessBoard board = new ChessBoard(8,8);
    ChessModel model = new ChessModel(board);
    ChessView view = new ChessView(model);
    System.out.println(board.prettyString());

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Chess Game");
    frame.setContentPane(view);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}