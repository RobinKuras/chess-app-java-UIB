package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class CellPositionToPixelConverter {
    Rectangle2D box;
    GridDimension gd;
    double margin;
    private double cellW;
    private double cellH;

    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
        this.box = box;
        this.gd = gd;
        this.margin = margin;

        //Calculate how many pixels needed for margins, and how many for cells (width / x)
        double boxWidth = this.box.getWidth();
        double pixelsForMarginW = ((gd.getCols() + 1) * margin);
        this.cellW = (boxWidth-pixelsForMarginW)/gd.getCols();

        //Calculate how many pixels needed for margins, and how many for cells (height / y)
        double boxHeight = this.box.getHeight();
        double pixelsForMarginH = ((gd.getRows() + 1 ) * margin);
        this.cellH = (boxHeight-pixelsForMarginH)/gd.getRows();
    }

    public Rectangle2D getBoundsForCell(CellPosition pos) {
        //Calculate cellX position
        double cellX = box.getX();
        for (int i = 0; i < pos.col(); i++) {
            cellX += cellW+margin;
        }

        cellX += margin;

        //Calculate cellY position
        double cellY = box.getY();
        for (int i = 0; i < pos.row(); i++) {
            cellY += cellH+margin;
        }

        cellY += margin;

        return new Rectangle2D.Double(cellX,cellY,cellW,cellH);
    }

    public CellPosition getCellPositionOfPoint(Point2D point) {
        // Same math as getBoundsForCell, but isolate the col/row on one side
        // and replace cellX with point.getX() (cellY with point.getY())
        double col = (point.getX() - box.getX() - margin) / (cellW + margin);
        double row = (point.getY() - box.getY() - margin) / (cellH + margin);

        // When row or col is out of bounds
        if (row < 0 || row >= gd.getRows() || col < 0 || col >= gd.getCols()) return null;

        // Verify that the point is indeed inside the bounds of the cell, and not on
        // the margin border
        CellPosition pos = new CellPosition((int) row, (int) col);
        if (getBoundsForCell(pos).contains(point)) {
            return pos;
        } else {
            return null;
        }
    }



}

