package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.TilePosition;


import java.awt.geom.Rectangle2D;

public class CellPositionToPixelConverter {
    Rectangle2D box;
    ChessBoard grid;
    double margin;

    public CellPositionToPixelConverter(Rectangle2D box, ChessBoard grid, double margin) {
        this.box = box;
        this.grid = grid;
        this.margin = margin;
    }

    public Rectangle2D getBoundsForCell(TilePosition pos) {
        //Calculate how many pixels needed for margins, and how many for cells (width / x)
        double boxWidth = this.box.getWidth();
        double pixelsForMarginW = ((grid.getCols() + 1) * margin);
        double cellWidth = (boxWidth-pixelsForMarginW)/grid.getCols();

        //Calculate how many pixels needed for margins, and how many for cells (height / y)
        double boxHeight = this.box.getHeight();
        double pixelsForMarginH = ((grid.getRows() + 1 ) * margin);
        double cellHeight = (boxHeight-pixelsForMarginH)/grid.getRows();

        //Calculate cellX position
        double cellX = box.getX();
        for (int i = 0; i < pos.colIndex(); i++) {
            cellX += cellWidth+margin;
        }

        cellX += margin;

        //Calculate cellY position
        double cellY = box.getY();
        for (int i = 0; i < pos.rowIndex(); i++) {
            cellY += cellHeight+margin;
        }

        cellY += margin;

        return new Rectangle2D.Double(cellX,cellY,cellWidth,cellHeight);
    }


}

