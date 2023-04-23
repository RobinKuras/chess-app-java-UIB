package no.uib.inf101.sem2.model.pieces;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.ChessModel;
import no.uib.inf101.sem2.model.Move;
import no.uib.inf101.sem2.model.Tile;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Rook implements IChessPiece{
    private ChessModel model;
    private final ChessBoard board;
    private CellPosition pos;
    private final ChessAlliance pieceColor;
    private final ImageIcon imageIcon;
    private List<Move> candidateMoves = new ArrayList<>();

    public Rook(ChessModel model, CellPosition position, ChessAlliance color){
        this.pos = position;
        this.pieceColor = color;
        this.model = model;
        this.board = model.getBoard();

        if(this.pieceColor == ChessAlliance.WHITE){
            this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_White-Rook.png");
        } else this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_Black-Rook.png");
    }

    public void addCandidateMove(Move move){
        if(!resultsInCheck(move)){
            candidateMoves.add(move);
        }
    }

    public boolean resultsInCheck(Move move){
        ChessAlliance alliance = model.getCurrentPlayersTurn();
        ChessAlliance oppAlliance;

        if (alliance == ChessAlliance.WHITE) {
            oppAlliance = ChessAlliance.BLACK;
        } else {
            oppAlliance = ChessAlliance.WHITE;
        }

        for(GridCell<Tile> cell : model.getTilesOnBoard()){
            Tile tile = cell.value();
            if (tile.getPiece() != null) {
                if (tile.getPiece().getAlliance() == oppAlliance) {
                    for(Move candMove : tile.getPiece().getCandidateMoves()){
                        if(candMove.getDestination().equals(move.getDestination())) {
                            return true;
                        }
                    }
                }
            }
        } return false;
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

        int row = pos.row();
        int col = pos.col();

        //sets up candidate moves to the right of the rook
        for(int i = col+1; i < board.getCols(); i++){
            CellPosition nextPos = new CellPosition(row,i);
            if(board.isOccupied(nextPos)){
                if(board.get(nextPos).getPiece().getAlliance() != pieceColor){
                    candidateMoves.add(new Move(this,new CellPosition(nextPos.row()-row, nextPos.col()-col)));
                } break;
            } else candidateMoves.add(new Move(this,new CellPosition(nextPos.row()-row, nextPos.col()-col)));
        }

        //sets up candidate moves to the left of the rook
        for(int i = col-1; i >= 0; i--){
            CellPosition nextPos = new CellPosition(row,i);
            if(board.isOccupied(nextPos)){
                if(board.get(nextPos).getPiece().getAlliance() != pieceColor){
                    candidateMoves.add(new Move(this,new CellPosition(nextPos.row()-row, nextPos.col()-col)));
                } break;
            } else candidateMoves.add(new Move(this,new CellPosition(nextPos.row()-row, nextPos.col()-col)));
        }

        //sets up candidate moves of the rook, upwards toward black start
        for(int i = row-1; i >= 0; i--){
            CellPosition nextPos = new CellPosition(i,col);
            if(board.isOccupied(nextPos)){
                if(board.get(nextPos).getPiece().getAlliance() != pieceColor){
                    candidateMoves.add(new Move(this,new CellPosition(nextPos.row()-row, nextPos.col()-col)));
                } break;
            } else candidateMoves.add(new Move(this,new CellPosition(nextPos.row()-row, nextPos.col()-col)));
        }

        //sets up candidate moves of the rook, backwards toward white start
        for(int i = row+1; i < board.getRows(); i++){
            CellPosition nextPos = new CellPosition(i,col);
            if(board.isOccupied(nextPos)){
                if(board.get(nextPos).getPiece().getAlliance() != pieceColor){
                    candidateMoves.add(new Move(this,new CellPosition(nextPos.row()-row, nextPos.col()-col)));
                } break;
            } else candidateMoves.add(new Move(this,new CellPosition(nextPos.row()-row, nextPos.col()-col)));
        }
    }

    @Override
    public boolean isAttacking() {
        return false;
    }

    @Override
    public void redoMove(Move move) {

    }
}
