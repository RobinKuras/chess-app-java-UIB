package no.uib.inf101.sem2.model.pieces;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.ChessModel;
import no.uib.inf101.sem2.model.Move;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn implements IChessPiece{
    private final ChessBoard board;
    private CellPosition pos;
    private final ChessAlliance pieceColor;
    private ImageIcon imageIcon;
    private List<Move> candidateMoves = new ArrayList<>();

    public Pawn(ChessModel model, CellPosition position,ChessAlliance color){
        this.pos = position;
        this.pieceColor = color;
        this.board = model.getBoard();

        if(this.pieceColor == ChessAlliance.WHITE){
            this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_White-Pawn.png");
        } else this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_Black-Pawn.png");
    }

    @Override
    public void movePiece(Move move) {
        if(candidateMoves.contains(move)){
            this.pos = new CellPosition(this.pos.row()+move.deltaPos().row(),this.pos.col()+move.deltaPos().col());
        }
    }

    @Override
    public void updateCandidateMoves(){
       this.candidateMoves.clear();

       if(pieceColor == ChessAlliance.WHITE){
           updateWhiteMoves();
       } else {
           updateBlackMoves();
       }
    }

    private void updateBlackMoves() {

        //Checks if pawn has moved, and can do pawn jump (2 tiles)
        if(pos.row() == 1 && !board.isOccupied(new CellPosition(pos.row()+2, pos.col())) && !board.isOccupied(new CellPosition(pos.row()+1, pos.col()))){
            candidateMoves.add(new Move(new CellPosition(2,0)));
        }

        //Checks if pawn can do a regular move (1 tile)
        if(!board.isOccupied(new CellPosition(pos.row()+1, pos.col()))){
            candidateMoves.add(new Move(new CellPosition(1,0)));
        }

        //Checks if diagonal move to the right is legal
        if(pos.col() != 7){
            if(board.isOccupied(new CellPosition(pos.row()+1,pos.col()+1))) {
                if (board.get(new CellPosition(pos.row()+1, pos.col()+1)).getPiece().getAlliance() == ChessAlliance.WHITE) {
                    candidateMoves.add(new Move(new CellPosition(1, 1)));
                }
            }
        }

        //Checks if diagonal move to the left is legal
        if(pos.col() != 0){
            if(board.isOccupied(new CellPosition(pos.row()+1,pos.col()-1))) {
                if (board.get(new CellPosition(pos.row()+1, pos.col()-1)).getPiece().getAlliance() == ChessAlliance.WHITE) {
                    candidateMoves.add(new Move(new CellPosition(1, -1)));
                }
            }
        }
    }


    private void updateWhiteMoves() {
        //Checks if pawn has moved, and can do pawn jump (2 tiles)
        if(pos.row() == 6 && !board.isOccupied(new CellPosition(pos.row()-2, pos.col())) && !board.isOccupied(new CellPosition(pos.row()-1, pos.col()))){
            candidateMoves.add(new Move(new CellPosition(-2,0)));
        }

        //Checks if pawn can do a regular move (1 tile)
        if(!board.isOccupied(new CellPosition(pos.row()-1, pos.col()))){
            candidateMoves.add(new Move(new CellPosition(-1,0)));
        }

        //Checks if diagonal move to the right is legal
        if(pos.col() != 7){
            if(board.isOccupied(new CellPosition(pos.row()-1,pos.col()+1))) {
                if (board.get(new CellPosition(pos.row() - 1, pos.col() + 1)).getPiece().getAlliance() == ChessAlliance.BLACK) {
                    candidateMoves.add(new Move(new CellPosition(-1, 1)));
                }
            }
        }

        //Checks if diagonal move to the left is legal
        if(pos.col() != 0){
            if(board.isOccupied(new CellPosition(pos.row()-1,pos.col()-1))) {
                if (board.get(new CellPosition(pos.row() - 1, pos.col() - 1)).getPiece().getAlliance() == ChessAlliance.BLACK) {
                    candidateMoves.add(new Move(new CellPosition(-1, -1)));
                }
            }
        }
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
        return candidateMoves;
    }
}
