package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.ChessModel;
import no.uib.inf101.sem2.model.Tile;
import no.uib.inf101.sem2.model.pieces.ChessAlliance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ChessView extends JPanel {
    ChessModel model;
    private CellPosition selectedTile;
    private static final double MARGIN = 2;

    public ChessView(ChessModel model) {
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(750, 690));
        this.setBackground(Color.BLACK);
        this.model = model;
        this.selectedTile = null;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point2D mouseCoordinate = e.getPoint();
                CellPositionToPixelConverter converter = getCellPositionToPixelConverter();
                if(converter.getCellPositionOfPoint(mouseCoordinate) != null){
                    selectedTile = converter.getCellPositionOfPoint(mouseCoordinate);
                    repaint();
                }
                repaint();
            }
        });

    }

    public void drawGame(Graphics2D canvas) {
        CellPositionToPixelConverter converter = this.getCellPositionToPixelConverter();
        drawCells(canvas, converter, model.getTilesOnBoard());

    }

    public void drawCells(Graphics2D canvas, CellPositionToPixelConverter converter, Iterable<GridCell<Tile>> boardTiles) {
        for (GridCell<Tile> tile : boardTiles) {
            Rectangle2D rectangle = converter.getBoundsForCell(tile.pos());
            if (tile.value().checkAlliance() == ChessAlliance.BLACK) {
                canvas.setColor(new Color(139, 69, 19).darker());
            } else {
                canvas.setColor(Color.WHITE);
            }
/* Fargelegg candidate moves
            if(selectedTile != null){
                Tile selectedTileValue = model.getBoard().get(selectedTile);
            }

 */
            canvas.fill(rectangle);

            if(tile.value().getPiece() != null){
                if(tile.value().getPiece().getImageFilePath() != null) {
                    // Load the appropriate image for the tile
                    ImageIcon imageIcon = new ImageIcon(tile.value().getPiece().getImageFilePath());
                    Image image = imageIcon.getImage();

                    // Calculate the position and size of the image
                    double imageWidth = rectangle.getWidth() * 0.75;
                    double imageHeight = rectangle.getHeight() * 0.75;
                    double imageX = rectangle.getX() + rectangle.getWidth() / 2 - imageWidth / 2;
                    double imageY = rectangle.getY() + rectangle.getHeight() / 2 - imageHeight / 2;

                    // Draw the image in the tile/cell
                    canvas.drawImage(image, (int) imageX, (int) imageY, (int) imageWidth, (int) imageHeight, null);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);

        // Draw the text
        String turn = model.getCurrentPlayersTurn().toString()+" turn!";
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 20));
        FontMetrics fm = g2.getFontMetrics();
        int x = getWidth() / 2 - fm.stringWidth(turn) / 2;
        int y = 25 + fm.getHeight() / 2;
        g2.drawString(turn, x, y-10);
    }

    public CellPositionToPixelConverter getCellPositionToPixelConverter(){
        double width = this.getWidth() - 2 * MARGIN;
        double height = this.getHeight() - 2 * MARGIN-35;
        Rectangle2D gameCanvas = new Rectangle2D.Double(MARGIN, MARGIN+35, width, height);
        GridDimension gridSize = this.model.getDimensions();
        return new CellPositionToPixelConverter(gameCanvas,gridSize,MARGIN);
    }
}
