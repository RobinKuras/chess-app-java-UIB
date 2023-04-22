package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.pieces.ChessAlliance;
import no.uib.inf101.sem2.model.pieces.IChessPiece;

public class Tile {
    private final CellPosition posOnGrid;
    private boolean isOccupied;
    private IChessPiece piece;
    private final ChessAlliance color;
    public Tile(CellPosition pos, boolean isOccupied, IChessPiece piece){
        this.posOnGrid = pos;
        this.isOccupied = isOccupied;
        this.piece = piece;
        this.color = this.checkAlliance();
    }


    public CellPosition getPosOnGrid(){
        return this.getPosOnGrid();
    }
    public IChessPiece getPiece(){
        return this.piece;
    }

    public Tile(CellPosition pos){
        this.posOnGrid = pos;
        this.isOccupied = false;
        this.piece = null;
        this.color = this.checkAlliance();
    }

    private void changeOccupiedStatus(){
        this.isOccupied = !this.isOccupied;
    }

    public void setPiece(IChessPiece newPiece){
        if(this.piece == null && newPiece != null){
            this.changeOccupiedStatus();
        } else if(this.piece != null && newPiece == null){
            this.changeOccupiedStatus();}
        this.piece = newPiece;
    }

    public ChessAlliance checkAlliance(){
        if(posOnGrid.row() % 2 == 0 && posOnGrid.col() % 2 == 0){
            return ChessAlliance.WHITE;
        } else if(posOnGrid.row() % 2 == 0 && posOnGrid.col() %2 != 0){
            return ChessAlliance.BLACK;
        }
        if(posOnGrid.row() % 2 != 0 && posOnGrid.col() % 2 == 0) {
            return ChessAlliance.BLACK;
        } else {
            return ChessAlliance.WHITE;
        }
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    @Override
    public String toString(){
        return "Occupied: "+this.isOccupied+"\n"+this.piece+this.piece.getAlliance();
    }
}
