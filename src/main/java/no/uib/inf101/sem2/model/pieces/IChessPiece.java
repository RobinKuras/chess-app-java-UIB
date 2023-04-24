package no.uib.inf101.sem2.model.pieces;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.Move;

import java.util.List;

public interface IChessPiece {

    /**
     * Changes the position of the piece with delta row/col from the move,
     * if the move is part of the candidateMoves-list.
     *
     * @param move the Move-object we test to see if is part of the chess piece candidate moves.
     */
    void movePiece(Move move);

    /**
     * @return the image path as a string
     */
    String getImageFilePath();

    /**
     * @return the chess alliance/color of the chess piece
     */
    ChessAlliance getAlliance();

    /**
     *
     * @return the cell position of the chess piece
     */
    CellPosition getPos();

    /**
     *
     * @return the list ob Move-objects that the chess piece is currently allowed to take, given the state of the board
     */
    List<Move> getCandidateMoves();

    /**
     * Updates the current candidateMoves according to the piece-types legal movement pattern. Also takes into
     * account whether the would place the current chess piece on an already occupied tile, and to not make the move
     * if its an ally, and overtake the piece if the piece is an enemy and won't place the allied king in check.
     */
    void updateCandidateMoves();

    /**
     * see if the given Move would place the allied king in check.
     * **CURRENTLY** only checks if the King already is in check.
     *
     * @param move the Move we want to see if results in a check.
     * @return true if move puts allied king in check, false if not
     */
    boolean resultsInCheck(Move move);

    /**
     * Adds a move to the candidate move list of the chess piece if it doesn't result in check.
     * Or if the piece it tries moving towards is currently attacking the king.
     *
     * @param move the Move we want to add to our list of candidate moves.
     */
    void addCandidateMove(Move move);

    /**
     * redo a move. If we want to check if a move results in self-check we can first try to do the move,
     * and then redo if it did
     *
     * @param move the Move we want to try to make
     */
    void redoMove(Move move);

    /**
     * Checks the list of candidate moves, and looks for a move that would place it in the same tile as the enemy king.
     *
     * @return true if it can attack the opposing king, false if not.
     */
    boolean isAttacking();
}
