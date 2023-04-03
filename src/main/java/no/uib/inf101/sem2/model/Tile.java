package no.uib.inf101.sem2.model;


import no.uib.inf101.sem2.pieces.ChessPiece;

public class Tile implements ITile {
    private final TilePosition coordinate;

    public Tile(TilePosition coordinate){
        this.coordinate = coordinate;
    }

    @Override
    public boolean isTileOccupied() {
        return false;
    }

    @Override
    public ChessPiece getPiece() {
        return null;
    }

    @Override
    public String toString(){
        return coordinate.rowIndex()+","+coordinate.colIndex();
    }
}


