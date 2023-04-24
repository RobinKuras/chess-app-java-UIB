package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.Move;
import no.uib.inf101.sem2.model.pieces.ChessAlliance;

public interface ControlableChessModel {
    /**
     *
     * @param move we want to check
     * @return true if the move is a legal move, false if not
     */
    boolean isLegalMove(Move move);

    /**
     * change the current players turn to WHITE (if was BLACK) and change it to BLACK (if was WHITE)
     */
    void newTurn();

    /**
     *
     * @return get the chessboard object of the model
     */
    ChessBoard getBoard();

    /**
     *
     * @return get the alliance, which move it is next
     */
    ChessAlliance getCurrentPlayersTurn();

    /**
     *
     * @return if the currently active player has no moves left, return true, else return false
     */
    boolean isCheckMate();


}
