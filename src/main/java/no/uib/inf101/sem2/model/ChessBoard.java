package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.Grid;
import no.uib.inf101.sem2.model.pieces.*;

public class ChessBoard extends Grid<Tile> {
    public ChessBoard(int rows, int cols) {
        super(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.set(new CellPosition(i,j),new Tile(new CellPosition(i,j)));
            }
        }
    }

    public IChessPiece getPieceAt(CellPosition pos){
        return this.get(pos).getPiece();
    }

    public boolean isOccupied(CellPosition pos){
        return this.get(pos).getIsOccupied();
    }

    public String prettyString(){
        String output = "";

        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {

                IChessPiece temp = this.get(new CellPosition(i, j)).getPiece();
                if(temp instanceof Rook){
                    output += "Rook ";
                } else if(temp instanceof Knight){
                    output += "Knight ";
                } else if(temp instanceof Bishop){
                    output += "Bishop ";
                } else if(temp instanceof Queen){
                    output += "Queen ";
                } else if(temp instanceof King){
                    output += "King ";
                } else if(temp instanceof Pawn){
                    output += "Pawn  ";
                } else {
                    output += "----- ";
                }

            }
                output += "\n";
        }

        return output;
    }

}
