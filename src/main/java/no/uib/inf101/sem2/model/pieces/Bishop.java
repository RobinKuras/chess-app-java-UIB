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

public class Bishop implements IChessPiece{
    private final ChessModel model;
    private final ChessBoard board;
    private CellPosition pos;
    private final ChessAlliance pieceColor;
    private final ImageIcon imageIcon;
    private boolean isAttacking;
    private final List<Move> candidateMoves = new ArrayList<>();

    /**
     * The constructor of the chess piece representing bishops.
     * @param model the model the piece is a part of, to gain access to all other pieces positions.
     * @param position the current position on the chess board.
     * @param color the chess alliance of the piece. WHITE/BLACK
     */
    public Bishop(ChessModel model, CellPosition position, ChessAlliance color){
        this.pos = position;
        this.pieceColor = color;
        this.model = model;
        this.board = model.getBoard();


        if(this.pieceColor == ChessAlliance.WHITE){
            this.imageIcon = new ImageIcon("src/main/resources/Chess_White-Bishop.png");
        } else this.imageIcon = new ImageIcon("src/main/resources/Chess_Black-Bishop.png");
    }

    @Override
    public void addCandidateMove(Move move){
        if(!resultsInCheck(move)){
            candidateMoves.add(move);
        } else if(board.get(move.getDestination()).getPiece() != null && board.get(move.getDestination()).getPiece().isAttacking()){
            candidateMoves.add(move);
        }
    }

    @Override
    public boolean resultsInCheck(Move move){
        ChessAlliance alliance = model.getCurrentPlayersTurn();
        CellPosition kingPos = model.getKingPosition(alliance);
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
                        if(candMove.getDestination().equals(kingPos)) {
                            return true;
                        }
                    }
                }
            }
        } return false;
    }

    @Override
    public void movePiece(Move move) {
        if(candidateMoves.contains(move)){
            this.pos = new CellPosition(this.pos.row()+move.deltaPos().row(),this.pos.col()+move.deltaPos().col());
        }
    }



    @Override
    public void updateCandidateMoves() {
        this.candidateMoves.clear();

        int row = pos.row();
        int col = pos.col();

        //sets up candidate moves to the top-right diagonal of the queen
        for (int i = 1; row - i >= 0 && col + i < board.getCols(); i++) {
            CellPosition nextPos = new CellPosition(row - i, col + i);
            if (board.isOccupied(nextPos)) {
                if (board.get(nextPos).getPiece().getAlliance() != pieceColor) {
                    addCandidateMove(new Move(this,new CellPosition(nextPos.row() - row, nextPos.col() - col)));
                }
                break;
            } else {
                addCandidateMove(new Move(this,new CellPosition(nextPos.row() - row, nextPos.col() - col)));
            }
        }

        //sets up candidate moves to the top-left diagonal of the queen
        for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
            CellPosition nextPos = new CellPosition(row - i, col - i);
            if (board.isOccupied(nextPos)) {
                if (board.get(nextPos).getPiece().getAlliance() != pieceColor) {
                    addCandidateMove(new Move(this,new CellPosition(nextPos.row() - row, nextPos.col() - col)));
                }
                break;
            } else {
                addCandidateMove(new Move(this,new CellPosition(nextPos.row() - row, nextPos.col() - col)));
            }
        }

        // sets up candidate moves to the bottom-left diagonal of the queen
        for (int i = 1; row + i < board.getRows() && col - i >= 0; i++) {
            CellPosition nextPos = new CellPosition(row + i, col - i);
            if (board.isOccupied(nextPos)) {
                if (board.get(nextPos).getPiece().getAlliance() != pieceColor) {
                    addCandidateMove(new Move(this,new CellPosition(nextPos.row() - row, nextPos.col() - col)));
                }
                break;
            } else {
                addCandidateMove(new Move(this,new CellPosition(nextPos.row() - row, nextPos.col() - col)));
            }
        }

        // sets up candidate moves to the bottom-right diagonal of the queen
        for (int i = 1; row + i < board.getRows() && col + i < board.getCols(); i++) {
            CellPosition nextPos = new CellPosition(row + i, col + i);
            if (board.isOccupied(nextPos)) {
                if (board.get(nextPos).getPiece().getAlliance() != pieceColor) {
                    addCandidateMove(new Move(this,new CellPosition(nextPos.row() - row, nextPos.col() - col)));
                }
                break;
            } else {
                addCandidateMove(new Move(this,new CellPosition(nextPos.row() - row, nextPos.col() - col)));
            }
        }
    }

    @Override
    public boolean isAttacking() {
        ChessAlliance oppAlliance;

        if (pieceColor == ChessAlliance.WHITE) {
            oppAlliance = ChessAlliance.BLACK;
        } else {
            oppAlliance = ChessAlliance.WHITE;
        }

        for(Move move : getCandidateMoves()){
            if(move.getDestination().equals(model.getKingPosition(oppAlliance))){
                this.isAttacking = true;
            } else this.isAttacking = false;
        }
        return this.isAttacking;
    }

    @Override
    public void redoMove(Move move) {}

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
