package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.pieces.ChessPiece;

public class EmptyTile extends Tile{
    public EmptyTile(TilePosition coordinate) {
        super(coordinate);
    }

    @Override
    public boolean isTileOccupied(){
        return false;
    }

    @Override
    public ChessPiece getPiece() {return null;}

}
