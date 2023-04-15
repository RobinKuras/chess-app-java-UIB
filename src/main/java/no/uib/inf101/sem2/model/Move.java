package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.pieces.IChessPiece;

public record Move(int deltaRow, int deltaCol, IChessPiece capturedPiece) {

    public Move(int deltaRow,int deltaCol){
        this(deltaRow,deltaCol,null);
    }


}
