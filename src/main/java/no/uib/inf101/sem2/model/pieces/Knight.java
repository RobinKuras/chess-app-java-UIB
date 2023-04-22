package no.uib.inf101.sem2.model.pieces;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.ChessModel;
import no.uib.inf101.sem2.model.Move;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Knight implements IChessPiece{
    private ChessModel model;
    private ChessBoard board;
    private CellPosition pos;
    private final ChessAlliance pieceColor;
    private ImageIcon imageIcon;
    private List<Move> candidateMoves = new ArrayList<>();
    public Knight(ChessModel model, CellPosition position, ChessAlliance color){
        this.pos = position;
        this.pieceColor = color;
        this.model = model;
        this.board = model.getBoard();

        if(this.pieceColor == ChessAlliance.WHITE){
            this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_White-Knight.png");
        } else {
            this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_Black-Knight.png");
        }
    }

    @Override
    public void movePiece(Move move) {
        if (candidateMoves.contains(move)) {
            this.pos = new CellPosition(this.pos.row() + move.deltaPos().row(), this.pos.col() + move.deltaPos().col());
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

    @Override
    public void updateCandidateMoves() {
        this.candidateMoves.clear();

        if(model.isCheck()){
            return;
        }

        int row = pos.row();
        int col = pos.col();
        CellPosition target;

        //checks if L-moves to the top right is legal
        target = new CellPosition(row-2,col+1);
        if(board.positionIsOnGrid(target)){
            if(board.isOccupied(target)){
                if(board.getPieceAt(target).getAlliance() != pieceColor){
                    candidateMoves.add(new Move(new CellPosition(-2,1)));
                }
            } else candidateMoves.add(new Move(new CellPosition(-2,1)));
        }

        target = new CellPosition(row-1,col+2);
        if(board.positionIsOnGrid(target)){
            if(board.isOccupied(target)){
                if(board.getPieceAt(target).getAlliance() != pieceColor){
                    candidateMoves.add(new Move(new CellPosition(-1,2)));
                }
            } else candidateMoves.add(new Move(new CellPosition(-1,2)));
        }

        //checks if L-movement to the top left is legal
        target = new CellPosition(row-2,col-1);
        if(board.positionIsOnGrid(target)){
            if(board.isOccupied(target)){
                if(board.getPieceAt(target).getAlliance() != pieceColor){
                    candidateMoves.add(new Move(new CellPosition(-2,-1)));
                }
            } else candidateMoves.add(new Move(new CellPosition(-2,-1)));
        }

        target = new CellPosition(row-1,col-2);
        if(board.positionIsOnGrid(target)){
            if(board.isOccupied(target)){
                if(board.getPieceAt(target).getAlliance() != pieceColor){
                    candidateMoves.add(new Move(new CellPosition(-1,-2)));
                }
            } else candidateMoves.add(new Move(new CellPosition(-1,-2)));
        }
        //checks if L-moves to the bottom right is legal
        target = new CellPosition(row+2,col+1);
        if(board.positionIsOnGrid(target)){
            if(board.isOccupied(target)){
                if(board.getPieceAt(target).getAlliance() != pieceColor){
                    candidateMoves.add(new Move(new CellPosition(2,1)));
                }
            } else candidateMoves.add(new Move(new CellPosition(2,1)));
        }

        target = new CellPosition(row+1,col+2);
        if(board.positionIsOnGrid(target)){
            if(board.isOccupied(target)){
                if(board.getPieceAt(target).getAlliance() != pieceColor){
                    candidateMoves.add(new Move(new CellPosition(1,2)));
                }
            } else candidateMoves.add(new Move(new CellPosition(1,2)));
        }

        //checks if L-movement to the bottom left is legal
        target = new CellPosition(row+2,col-1);
        if(board.positionIsOnGrid(target)){
            if(board.isOccupied(target)){
                if(board.getPieceAt(target).getAlliance() != pieceColor){
                    candidateMoves.add(new Move(new CellPosition(2,-1)));
                }
            } else candidateMoves.add(new Move(new CellPosition(2,-1)));
        }

        target = new CellPosition(row+1,col-2);
        if(board.positionIsOnGrid(target)){
            if(board.isOccupied(target)){
                if(board.getPieceAt(target).getAlliance() != pieceColor){
                    candidateMoves.add(new Move(new CellPosition(1,-2)));
                }
            } else candidateMoves.add(new Move(new CellPosition(1,-2)));
        }
    }

    @Override
    public void redoMove(Move move) {

    }
}
