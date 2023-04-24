package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.model.Move;

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


}
