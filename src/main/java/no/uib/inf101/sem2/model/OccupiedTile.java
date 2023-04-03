package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.pieces.ChessPiece;

public class OccupiedTile extends Tile {
    private final ChessPiece piece;
    public OccupiedTile(TilePosition coordinate, ChessPiece piece) {
        super(coordinate);
        this.piece = piece;
    }

    @Override
    public boolean isTileOccupied(){
        return true;
    }

    @Override
    public ChessPiece getPiece() {
        return this.piece;
    }
}
