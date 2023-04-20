package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.pieces.IChessPiece;

public record Move(CellPosition deltaPos) implements Comparable<Move> {

    @Override
    public int compareTo(Move otherMove) {
        return this.deltaPos.compareTo(otherMove.deltaPos);
    }
}
