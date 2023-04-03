package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.ChessAlliance;
import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.Tile;
import no.uib.inf101.sem2.model.TilePosition;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

public class ChessView extends JPanel {
    private static final double MARGIN = 2;

    public ChessView(){
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(1000, 850));

    }

    public void drawBoard(Graphics2D canvas) {
        ChessBoard board = new ChessBoard();
        double width = this.getWidth() - 2 * MARGIN;
        double height = this.getHeight() - 2 * MARGIN;
        Rectangle2D gameCanvas = new Rectangle2D.Double(MARGIN, MARGIN, width, height);

        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(gameCanvas, board, MARGIN);
        for (Iterator<Tile> it = board.iterator(); it.hasNext(); ) {
            Tile cell = it.next();
            Rectangle2D rectangle = converter.getBoundsForCell(cell.getCoordinate());
            if(cell.checkAlliance() == ChessAlliance.BLACK){
                canvas.setColor(Color.BLACK);
            } else {
                canvas.setColor(Color.WHITE);
            }
            canvas.fill(rectangle);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawBoard(g2);

    }
}
