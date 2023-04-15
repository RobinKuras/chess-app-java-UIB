package no.uib.inf101.sem2.model.pieces;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.Move;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn implements IChessPiece{
    private CellPosition pos;
    private final ChessAlliance pieceColor;
    private ImageIcon imageIcon;
    private final List<Move> legalMoves = new ArrayList<>();
    private boolean hasMoved;
    private List<Move> candidateMoves = new ArrayList<>();
    public Pawn(CellPosition position,ChessAlliance color){
        this.pos = position;
        this.pieceColor = color;
        this.hasMoved = false;

        if(this.pieceColor == ChessAlliance.WHITE){
            this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_White-Pawn.png");
        } else {
            this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_Black-Pawn.png");
        }

        if(pieceColor == ChessAlliance.BLACK){
                legalMoves.add(new Move(2,0));
                legalMoves.add(new Move(1,0));

        } else if(pieceColor == ChessAlliance.WHITE){
                legalMoves.add(new Move(-2,0));
                legalMoves.add(new Move(-1,0));
            }
        }

    @Override
    public void movePiece(Move move) {

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
