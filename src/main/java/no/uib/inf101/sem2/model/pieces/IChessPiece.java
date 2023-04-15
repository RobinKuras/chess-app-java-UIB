package no.uib.inf101.sem2.model.pieces;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.Move;

public interface IChessPiece {

    void movePiece(Move move);
    String getImageFilePath();
    ChessAlliance getAlliance();
    CellPosition getPos();
}
