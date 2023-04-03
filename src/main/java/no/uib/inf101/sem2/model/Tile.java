package no.uib.inf101.sem2.model;


import no.uib.inf101.sem2.ChessAlliance;
import no.uib.inf101.sem2.pieces.ChessPiece;

public class Tile implements ITile {
    private final TilePosition coordinate;
    private ChessAlliance alliance;

    public Tile(TilePosition coordinate){
        this.coordinate = coordinate;
        this.alliance = null;
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

    public TilePosition getCoordinate() {
        return coordinate;
    }

    public ChessAlliance getAlliance() {
        return alliance;
    }

    public void setAlliance(ChessAlliance color){
        this.alliance = color;
    }

    public ChessAlliance checkAlliance(){
        if(coordinate.rowIndex() % 2 == 0 && coordinate.colIndex() % 2 == 0){
            return ChessAlliance.WHITE;
        } else if(coordinate.rowIndex() % 2 == 0 && coordinate.colIndex() %2 != 0){
            return ChessAlliance.BLACK;
        }
        if(getCoordinate().rowIndex() % 2 != 0 && coordinate.colIndex() % 2 == 0) {
            return ChessAlliance.BLACK;
        } else {
            return ChessAlliance.WHITE;
        }
    }
}


