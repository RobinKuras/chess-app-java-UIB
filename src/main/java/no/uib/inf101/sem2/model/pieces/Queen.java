package no.uib.inf101.sem2.model.pieces;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.ChessModel;
import no.uib.inf101.sem2.model.Move;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Queen implements IChessPiece{
    private ChessBoard board;
    private CellPosition pos;
    private final ChessAlliance pieceColor;
    private ImageIcon imageIcon;
    private List<Move> candidateMoves = new ArrayList<>();
    public Queen(ChessModel model, CellPosition position, ChessAlliance color){
        this.pos = position;
        this.pieceColor = color;
        this.board = model.getBoard();

        if(this.pieceColor == ChessAlliance.WHITE){
            this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_White-Queen.png");
        } else this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_Black-Queen.png");
    }


    @Override
    public void movePiece(Move move) {
        if(candidateMoves.contains(move)){
            this.pos = new CellPosition(this.pos.row()+move.deltaPos().row(),this.pos.col()+move.deltaPos().col());
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

        int row = pos.row();
        int col = pos.col();

        // Sets up candidate moves to the right of the queen
        for (int i = col + 1; i < board.getCols(); i++) {
            CellPosition nextPos = new CellPosition(row, i);
            if (board.isOccupied(nextPos)) {
                if (board.get(nextPos).getPiece().getAlliance() != pieceColor) {
                    candidateMoves.add(new Move(new CellPosition(nextPos.row() - row, nextPos.col() - col)));
                }
                break;
            } else {
                candidateMoves.add(new Move(new CellPosition(nextPos.row() - row, nextPos.col() - col)));
            }
        }

        // Sets up candidate moves to the left of the queen
        for (int i = col - 1; i >= 0; i--) {
            CellPosition nextPos = new CellPosition(row, i);
            if (board.isOccupied(nextPos)) {
                if (board.get(nextPos).getPiece().getAlliance() != pieceColor) {
                    candidateMoves.add(new Move(new CellPosition(nextPos.row() - row, nextPos.col() - col)));
                }
                break;
            } else {
                candidateMoves.add(new Move(new CellPosition(nextPos.row() - row, nextPos.col() - col)));
            }
        }

        //sets up candidate moves of the queen, upwards toward black start
        for(int i = row-1; i >= 0; i--){
            CellPosition nextPos = new CellPosition(i,col);
            if(board.isOccupied(nextPos)){
                if(board.get(nextPos).getPiece().getAlliance() != pieceColor){
                    candidateMoves.add(new Move(new CellPosition(nextPos.row()-row, nextPos.col()-col)));
                } break;
            } else candidateMoves.add(new Move(new CellPosition(nextPos.row()-row, nextPos.col()-col)));
        }

        //sets up candidate moves of the queen, backwards toward white start
        for(int i = row+1; i < board.getRows(); i++){
            CellPosition nextPos = new CellPosition(i,col);
            if(board.isOccupied(nextPos)){
                if(board.get(nextPos).getPiece().getAlliance() != pieceColor){
                    candidateMoves.add(new Move(new CellPosition(nextPos.row()-row, nextPos.col()-col)));
                } break;
            } else candidateMoves.add(new Move(new CellPosition(nextPos.row()-row, nextPos.col()-col)));
        }

        //sets up candidate moves to the top-right diagonal of the queen
        for (int i = 1; row - i >= 0 && col + i < board.getCols(); i++) {
            CellPosition nextPos = new CellPosition(row - i, col + i);
            if (board.isOccupied(nextPos)) {
                if (board.get(nextPos).getPiece().getAlliance() != pieceColor) {
                    candidateMoves.add(new Move(new CellPosition(nextPos.row() - row, nextPos.col() - col)));
                }
                break;
            } else {
                candidateMoves.add(new Move(new CellPosition(nextPos.row() - row, nextPos.col() - col)));
            }
        }

        //sets up candidate moves to the top-left diagonal of the queen
        for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
            CellPosition nextPos = new CellPosition(row - i, col - i);
            if (board.isOccupied(nextPos)) {
                if (board.get(nextPos).getPiece().getAlliance() != pieceColor) {
                    candidateMoves.add(new Move(new CellPosition(nextPos.row() - row, nextPos.col() - col)));
                }
                break;
            } else {
                candidateMoves.add(new Move(new CellPosition(nextPos.row() - row, nextPos.col() - col)));
            }
        }

        // sets up candidate moves to the bottom-left diagonal of the queen
        for (int i = 1; row + i < board.getRows() && col - i >= 0; i++) {
            CellPosition nextPos = new CellPosition(row + i, col - i);
            if (board.isOccupied(nextPos)) {
                if (board.get(nextPos).getPiece().getAlliance() != pieceColor) {
                    candidateMoves.add(new Move(new CellPosition(nextPos.row() - row, nextPos.col() - col)));
                }
                break;
            } else {
                candidateMoves.add(new Move(new CellPosition(nextPos.row() - row, nextPos.col() - col)));
            }
        }

        // sets up candidate moves to the bottom-right diagonal of the queen
        for (int i = 1; row + i < board.getRows() && col + i < board.getCols(); i++) {
            CellPosition nextPos = new CellPosition(row + i, col + i);
            if (board.isOccupied(nextPos)) {
                if (board.get(nextPos).getPiece().getAlliance() != pieceColor) {
                    candidateMoves.add(new Move(new CellPosition(nextPos.row() - row, nextPos.col() - col)));
                }
                break;
            } else {
                candidateMoves.add(new Move(new CellPosition(nextPos.row() - row, nextPos.col() - col)));
            }
        }
    }
}
