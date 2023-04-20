package no.uib.inf101.sem2.model.pieces;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.Move;

import javax.swing.*;
import java.util.List;

public class Knight implements IChessPiece{
    private CellPosition pos;
    private final ChessAlliance pieceColor;
    private ImageIcon imageIcon;
    public Knight(CellPosition position,ChessAlliance color){
        this.pos = position;
        this.pieceColor = color;

        if(this.pieceColor == ChessAlliance.WHITE){
            this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_White-Knight.png");
        } else {
            this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_Black-Knight.png");
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

    @Override
    public List<Move> getCandidateMoves() {
        return null;
    }

    @Override
    public List<Move> getLegalMoves() {
        return null;
    }

    @Override
    public void updateCandidateMoves() {

    }
}
