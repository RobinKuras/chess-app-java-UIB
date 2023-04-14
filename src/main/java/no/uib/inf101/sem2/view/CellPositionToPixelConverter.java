package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;

import java.awt.geom.Rectangle2D;

public class CellPositionToPixelConverter {
    Rectangle2D box;
    GridDimension gd;
    double margin;

    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
        this.box = box;
        this.gd = gd;
        this.margin = margin;
    }

    public Rectangle2D getBoundsForCell(CellPosition pos) {
        //Calculate how many pixels needed for margins, and how many for cells (width / x)
        double boxWidth = this.box.getWidth();
        double pixelsForMarginW = ((gd.getCols() + 1) * margin);
        double cellWidth = (boxWidth-pixelsForMarginW)/gd.getCols();

        //Calculate how many pixels needed for margins, and how many for cells (height / y)
        double boxHeight = this.box.getHeight();
        double pixelsForMarginH = ((gd.getRows() + 1 ) * margin);
        double cellHeight = (boxHeight-pixelsForMarginH)/gd.getRows();

        //Calculate cellX position
        double cellX = box.getX();
        for (int i = 0; i < pos.col(); i++) {
            cellX += cellWidth+margin;
        }

        cellX += margin;

        //Calculate cellY position
        double cellY = box.getY();
        for (int i = 0; i < pos.row(); i++) {
            cellY += cellHeight+margin;
        }

        cellY += margin;

        return new Rectangle2D.Double(cellX,cellY,cellWidth,cellHeight);
    }


}

