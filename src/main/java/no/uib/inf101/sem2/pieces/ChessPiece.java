package no.uib.inf101.sem2.pieces;

import no.uib.inf101.sem2.model.TilePosition;

public class ChessPiece {

    private final TilePosition pieceCoordinate;
    private final PieceColor color;

    protected ChessPiece(TilePosition pieceCoordinate, PieceColor color){
        this.pieceCoordinate = pieceCoordinate;
        this.color = color;
    }
}
