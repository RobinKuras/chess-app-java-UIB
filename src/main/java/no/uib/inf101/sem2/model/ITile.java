package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.pieces.ChessPiece;

public interface ITile {
    boolean isTileOccupied();
    ChessPiece getPiece();
}
