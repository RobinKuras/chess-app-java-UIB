package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.pieces.IChessPiece;

public record Move(IChessPiece pieceToBeMoved, CellPosition deltaPos) implements Comparable<Move> {

    /**
     *
     * @return the destination of the piece after applying the move
     */
    public CellPosition getDestination(){
        return new CellPosition(pieceToBeMoved.getPos().row()+deltaPos.row(),pieceToBeMoved.getPos().col()+deltaPos.col());
    }

    @Override
    public String toString(){
        return "<"+deltaPos.row()+","+deltaPos.col()+">";
    }
    @Override
    public int compareTo(Move otherMove) {
        return this.deltaPos.compareTo(otherMove.deltaPos);
    }
}
