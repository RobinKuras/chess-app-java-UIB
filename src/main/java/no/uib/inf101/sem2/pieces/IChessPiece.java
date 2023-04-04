package no.uib.inf101.sem2.pieces;

import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.Move;
import no.uib.inf101.sem2.model.TilePosition;

import java.util.List;

public interface IChessPiece {

    boolean movePiece(TilePosition currPos, TilePosition deltaPos);
    List<Move> legalMoves(ChessBoard board);
}
