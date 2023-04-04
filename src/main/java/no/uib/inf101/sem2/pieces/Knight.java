package no.uib.inf101.sem2.pieces;

import no.uib.inf101.sem2.ChessAlliance;
import no.uib.inf101.sem2.model.TilePosition;

public class Knight extends ChessPiece {
    private final TilePosition[] possibleLegalCoordinates = {
            new TilePosition(-1,-2),
            new TilePosition(-2,-1),
            new TilePosition(-1,2),
            new TilePosition(-2,1),
            new TilePosition(1,-2),
            new TilePosition(2,-1),
            new TilePosition(1,2),
            new TilePosition(2,1)
    };
    Knight(TilePosition pieceCoordinate, ChessAlliance color) {
        super(pieceCoordinate, color);
    }

}
