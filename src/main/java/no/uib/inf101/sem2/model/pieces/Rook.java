package no.uib.inf101.sem2.model.pieces;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.Move;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Rook implements IChessPiece{
    private CellPosition pos;
    private final ChessAlliance pieceColor;
    private final ImageIcon imageIcon;
    private final List<Move> legalMoves = new ArrayList<>();
    public Rook(CellPosition position,ChessAlliance color){
        this.pos = position;
        this.pieceColor = color;

        if(this.pieceColor == ChessAlliance.WHITE){
            this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_White-Rook.png");
        } else {
            this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_Black-Rook.png");
        }

        if(pieceColor == ChessAlliance.BLACK){
            for (int i = 1; i < 8; i++) {
                legalMoves.add(new Move(i,0));
                legalMoves.add(new Move(0,i));
            }
        } else if(pieceColor == ChessAlliance.WHITE){
            for (int i = -1; i > -8; i--) {
                legalMoves.add(new Move(i,0));
                legalMoves.add(new Move(0,i));
            }
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
