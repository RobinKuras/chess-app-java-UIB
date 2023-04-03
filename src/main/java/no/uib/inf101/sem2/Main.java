package no.uib.inf101.sem2;

import no.uib.inf101.sem2.view.ChessView;
import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    ChessView view = new ChessView();

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Chess Game");
    frame.setContentPane(view);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
