package no.uib.inf101.sem2.model.pieces;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.Move;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn implements IChessPiece{
    private final ChessBoard board;
    private CellPosition pos;
    private final ChessAlliance pieceColor;
    private ImageIcon imageIcon;
    private final List<Move> legalMoves = new ArrayList<>();
    //Tracks amount of moves, so it can only move 2 tiles if it is its first move
    private boolean hasMoved;
    private List<Move> candidateMoves = new ArrayList<>();
    public Pawn(ChessBoard board, CellPosition position,ChessAlliance color){
        this.pos = position;
        this.pieceColor = color;
        this.hasMoved = false;
        this.board = board;

        if(this.pieceColor == ChessAlliance.WHITE){
            this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_White-Pawn.png");
        } else {
            this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_Black-Pawn.png");
        }

        if(pieceColor == ChessAlliance.BLACK){
            Move firstMove = new Move(new CellPosition(2,0));
            legalMoves.add(firstMove);
            legalMoves.add(new Move(new CellPosition(1,0)));

        } else if(pieceColor == ChessAlliance.WHITE){
            Move firstMove = new Move(new CellPosition(-2,0));
            legalMoves.add(firstMove);
            legalMoves.add(new Move(new CellPosition(-1,0)));
            }
        }

    @Override
    public void movePiece(Move move) {
        this.pos = new CellPosition(this.pos.row()+move.deltaPos().row(),this.pos.col()+move.deltaPos().col());
    }

    @Override
    public String getImageFilePath() {
        return imageIcon.toString();
    }

    @Override
    public ChessAlliance getAlliance() {
        return pieceColor;
    }

    @Override
    public CellPosition getPos() {
        return pos;
    }
}
