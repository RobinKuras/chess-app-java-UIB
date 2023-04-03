package no.uib.inf101.sem2.pieces;

import no.uib.inf101.sem2.ChessAlliance;
import no.uib.inf101.sem2.model.TilePosition;

public class ChessPiece {

    private final TilePosition pieceCoordinate;
    private final ChessAlliance color;

    protected ChessPiece(TilePosition pieceCoordinate, ChessAlliance color){
        this.pieceCoordinate = pieceCoordinate;
        this.color = color;
    }
}
